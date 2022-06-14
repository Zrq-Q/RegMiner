package org.regminer.miner.model;

import org.regminer.common.model.TestSuiteX;
import org.regminer.ct.model.CompileWay;

/**
 * @Author: sxz
 * @Date: 2022/06/09/00:50
 * @Description:
 */
public class Regression {
    String projectName;
    String bugID;
    String bfc; //bug-fixing commit
    String buggy; //bug-fixing parent
    String bic; //bug-inducing commit
    String work; // working commit, note working
    String testSuite; // test suite for target bug
    String compileEnv; //compile env
}
