package org.regminer.rfc.api;

import org.junit.Test;

/**
 * @Author: sxz
 * @Date: 2022/06/14/12:32
 * @Description:
 */
public class PBFCParserTest {

    private PBFCParser pbfcParser;

    @Test
    public void testGetPBFCListInProject() {
        //TODO test here
        PBFCParser pbfcParser = new PBFCParser();
        pbfcParser.getPBFCs("E:\\reg\\RegMiner");

    }

    @Test
    public void testGetAllCommitsInBranch(){
        //TODO test here

    }

    @Test
    public void testParsePBFCForCommit(){
        //TODO test here
    }
}