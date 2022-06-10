package org.regminer.miner.api;

import org.regminer.bic.api.BICSearcher;
import org.regminer.ct.api.ProjectBuilder;
import org.regminer.migrate.api.TestMigrator;
import org.regminer.rfc.api.PBFCParser;
import org.regminer.rfc.api.PRFCParser;

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
    private ProjectsProvider projectsProvider;

    public MinerContext(PBFCParser pbfcParser, PRFCParser prfcParser, BICSearcher bicSearcher,
                        TestMigrator testMigrator, ProjectBuilder projectBuilder, ProjectsProvider projectsProvider) {
        this.pbfcParser = pbfcParser;
        this.prfcParser = prfcParser;
        this.bicSearcher = bicSearcher;
        this.testMigrator = testMigrator;
        this.projectBuilder = projectBuilder;
        this.projectsProvider = projectsProvider;
    }

    public void start(){

        return;
    }
}
