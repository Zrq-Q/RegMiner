package org.regminer.rfc.api;

import org.regminer.rfc.api.core.AbstractPBFCParser;
import org.regminer.rfc.model.PotentialBFC;

import java.util.List;

/**
 * @Author: sxz
 * @Date: 2022/06/09/00:10
 * @Description:
 */
public class PBFCParser extends AbstractPBFCParser {
    private TestSearcher testSearcher;

    @Override
    public List<PotentialBFC> getPBFCs() {
        return null;
    }

    @Override
    public PotentialBFC parser() {
        return null;
    }

    public void setTestSearcher(TestSearcher testSearcher) {
        this.testSearcher = testSearcher;
    }
}
