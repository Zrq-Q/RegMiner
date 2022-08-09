package regminer.start;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import regminer.constant.Conf;
import regminer.git.provider.Provider;
import regminer.miner.PotentialBFCDetector;
import regminer.miner.RelatedTestCaseParser;
import regminer.miner.migrate.BICFinder;
import regminer.miner.migrate.BFCEvaluator;
import regminer.model.PotentialRFC;
import regminer.model.Regression;
import regminer.monitor.ProgressMonitor;
import regminer.sql.BugStorage;
import regminer.sql.PBFCDao;
import regminer.utils.FileUtilx;
import regminer.utils.ThreadPoolUtil;

import regminer.model.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author sxz
 * 方法入口
 */
public class Miner {
    public static Repository repo = null;
    public static Git git = null;
    public static LinkedList<PotentialRFC> pRFCs;
    public static Set<String> setResult = new HashSet<>();
    static BugStorage bugStorage = new BugStorage();

    public static void main(String[] args) throws Exception {
        long s1 = System.currentTimeMillis();
        ConfigLoader.refresh();//加载配置

        repo = new Provider().create(Provider.EXISITING).get(Conf.LOCAL_PROJECT_GIT);
        git = new Git(repo);
        try {
            // 检测满足条件的BFC
            PotentialBFCDetector pBFCDetector = new PotentialBFCDetector(repo, git);
            pRFCs = (LinkedList<PotentialRFC>) pBFCDetector.detectPotentialBFC();
            System.out.println(pRFCs.size());
//            ProgressMonitor.rePlan(pRFCs);
            // 开始每一个bfc所对应的bic，查找任务。
            singleThreadHandle(); // 单线程处理模式
            //mutilThreadHandle();// 多线程模式
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }

    public static void singleThreadHandle() throws Exception {
        // 工具类准备,1)测试方法查找 2)测试用例确定 3)BIC查找
        BFCEvaluator tm = new BFCEvaluator(repo);
        // 声明一些辅助变量
        float i = 0;
        float j = (float) pRFCs.size();
        System.out.println("origin bfc number " + j);
        Iterator<PotentialRFC> iterator = pRFCs.iterator();
        FileUtilx.log("########################Start################################");
        while (iterator.hasNext()) {
            PotentialRFC pRfc = iterator.next();
            tm.evolute(pRfc);
            i++;
            FileUtilx.log(i / j + "%");
            int count = 0;
            // TODO 此处的方法和类之间的affix按照mvn的习惯用"#"连接,没有配置子项目
            if (pRfc.getTestCaseFiles().size() == 0) { // 找不到测试用例直接跳过
                iterator.remove();
            } else {
                count++;
            }
//            ProgressMonitor.addDone(pRfc.getCommit().getName());
        }
        FileUtilx.log("########################END SEARCH################################");
    }

    public static void mutilThreadHandle() {
        int cpuSize = ThreadPoolUtil.cpuIntesivePoolSize();
        for (int i = 0; i <= cpuSize; i++) {
            new SycTaskHandle().start();
        }
    }

    /**
     * @author sxz
     * 此类当前禁用
     * 多线程模式proces无法确定工作目录
     * 此类计划应用于CPU密集任务
     */
    static class SycTaskHandle extends Thread {

        @Override
        public void run() {
            threadCoreTask();
        }

        public void threadCoreTask() {
            RelatedTestCaseParser rTCParser = new RelatedTestCaseParser();
            BFCEvaluator tm = new BFCEvaluator(repo);
            BICFinder finder = new BICFinder();
            while (true) {
                PotentialRFC pRFC;
                synchronized (pRFCs) {
                    if (pRFCs.peek() != null) {
                        pRFC = pRFCs.pop(); // 获取任务
                    } else {
                        // 结束线程
                        break;
                    }
                }
                try {
                    // 确定被解析的方法那些是真实的测试用例
                    // TODO 此处的方法和类之间的affix按照mvn的习惯用"#"连接,也没有配置子项目
                    rTCParser.parseTestCases(pRFC);
                    tm.evolute(pRFC);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                if (pRFC.getTestCaseFiles().size() != 0) { // 找不到测试用例直接跳过
//                    // 确定测试用例之后开始查找bic
//                    String r = finder.searchBIC(pRFC);
//                    String item = pRFC.getCommit().getName() + "," + r;
//                    if (r != null) {
//                        if (!setResult.contains(item)) {
//                            FileUtilx.apendResult(item);
//                        }
//                        setResult.add(item);
//                    }
//                }
            }
        }
    }
}
