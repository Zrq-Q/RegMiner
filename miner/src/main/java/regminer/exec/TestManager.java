package regminer.exec;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author Richy
 * create: 2022-07-18 19:24
 **/
public class TestManager {

    final static String REPORT_PATH = "target" + File.separator + "surefire-reports";
    final static String JUNIT_REPORT_PATH = REPORT_PATH + File.separator + "junitreports";
    final static SAXReader reader = new SAXReader();

    /**
     * testResult: map<test signature, status>
     * test signature: testcase + # + classname
     * status: 0--success 1--failure
     */
    HashMap<String, Integer> testResult = new HashMap<>();

    public Map<String, Integer> run(File projectFile) {
        String buildCommand = "mvn clean compile test-compile";
        String testCommand = "mvn test";

        try {
            Executor test = new Executor();
            test.setDirectory(projectFile);
            if (!test.execJudgeFailure(buildCommand, 0)) {
                return null;
            }
            test.exec(testCommand);
            getTestResult(projectFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return testResult;
    }

    public void getTestResult(File codePath) {

        File reportDirectory = new File(codePath, JUNIT_REPORT_PATH);
        if (!reportDirectory.exists()) {
            reportDirectory = new File(codePath, REPORT_PATH);
        }
        if (!reportDirectory.exists() || !reportDirectory.isDirectory()) {
            return;
        }
        for (String file : reportDirectory.list()) {
            if (file.matches("TEST.*\\.xml")) {
                //读取内容，获取testcase及其状态
                getTestcases(new File(reportDirectory, file));
            }
        }
    }

    private void getTestcases(File curFile) {
        try {
            Document doc = reader.read(curFile);
            Element root = doc.getRootElement();
            List<Element> testCases = root.elements("testcase");
            for (Element testCase : testCases) {
                String testIdentify = testCase.attributeValue("name") + "#" + testCase.attributeValue("classname");
                if (testCase.element("error") != null || testCase.element("failure") != null) {
                    testResult.put(testIdentify, 1);
                    System.out.println(testIdentify);
                }else {
                    testResult.put(testIdentify, 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}