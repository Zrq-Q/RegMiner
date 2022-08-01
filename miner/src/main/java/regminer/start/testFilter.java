package regminer.start;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import regminer.constant.Conf;
import regminer.git.provider.Provider;
import regminer.miner.PotentialBFCDetector;
import regminer.model.PotentialRFC;
import regminer.monitor.ProgressMonitor;
import regminer.sql.BugStorage;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * description:
 *
 * @author Richy
 * create: 2022-07-30 23:38
 **/
public class testFilter {
    public static Repository repo = null;
    public static Git git = null;
    public static Set<String> setResult = new HashSet<>();

    public static void main(String[] args) throws Exception {
        long s1 = System.currentTimeMillis();
        ConfigLoader.refresh();//加载配置

        repo = new Provider().create(Provider.EXISITING).get(Conf.LOCAL_PROJECT_GIT);
        git = new Git(repo);
        try {
            // 检测满足条件的BFC
            PotentialBFCDetector pBFCDetector = new PotentialBFCDetector(repo, git);
            pBFCDetector.detectPotentialBFC();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }
}