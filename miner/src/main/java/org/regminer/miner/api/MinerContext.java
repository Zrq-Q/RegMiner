package org.regminer.miner.api;

import org.regminer.miner.model.Regression;

import java.util.List;

/**
 * @Author: sxz
 * @Date: 2022/06/08/22:32
 * @Description:
 */
public class MinerContext {
    private PBFCParser pbfcParser;
    private PRFCParser prfcParser;
    private BICSearcher bicSearcher;
    private TestMigrator testMigrator;
    private ProjectBuilder projectBuilder;
    private ProjectProvider projectProvider;

    public MinerContext(PBFCParser pbfcParser, PRFCParser prfcParser, BICSearcher bicSearcher,
                        TestMigrator testMigrator, ProjectBuilder projectBuilder, ProjectProvider projectProvider) {
        this.pbfcParser = pbfcParser;
        this.prfcParser = prfcParser;
        this.bicSearcher = bicSearcher;
        this.testMigrator = testMigrator;
        this.projectBuilder = projectBuilder;
        this.projectProvider = projectProvider;
    }

    public void start(){

        return;
    }
}
