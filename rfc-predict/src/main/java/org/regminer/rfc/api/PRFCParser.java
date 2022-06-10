package org.regminer.rfc.api;

import org.regminer.ct.api.ProjectBuilder;
import org.regminer.rfc.api.core.AbstractPRFCParser;
import org.regminer.rfc.model.PotentialRFC;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author: sxz
 * @Date: 2022/06/09/00:10
 * @Description:
 */
public class PRFCParser extends AbstractPRFCParser {
    private TestReducer testReducer;
    private ProjectBuilder projectBuilder;
    @Override
    public PriorityBlockingQueue<PotentialRFC> getPRFList() {
        return null;
    }

    @Override
    public PotentialRFC parser() {
        return null;
    }

    public void setTestReducer(TestReducer testReducer) {
        this.testReducer = testReducer;
    }
}
