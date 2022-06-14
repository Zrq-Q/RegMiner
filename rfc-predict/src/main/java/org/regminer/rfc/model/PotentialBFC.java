package org.regminer.rfc.model;

import org.regminer.common.model.CommitEntity;
import org.regminer.common.model.TestSuiteX;

/**
 * @Author: sxz
 * @Date: 2022/06/09/00:50
 * @Description:
 */
public class PotentialBFC extends CommitEntity {
    private TestSuiteX testSuiteX;
    public PotentialBFC() {
        super();
    }

    public TestSuiteX getTestSuiteX() {
        return testSuiteX;
    }

    public void setTestSuiteX(TestSuiteX testSuiteX) {
        this.testSuiteX = testSuiteX;
    }
}
