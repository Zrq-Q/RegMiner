package org.regminer.miner;

import org.regminer.bic.api.BICSearcher;
import org.regminer.ct.api.ProjectBuilder;
import org.regminer.migrate.api.TestMigrator;
import org.regminer.miner.api.MinerContext;
import org.regminer.miner.api.ProjectsProvider;
import org.regminer.rfc.api.PBFCParser;
import org.regminer.rfc.api.PRFCParser;

public class Application {

    public static void main(String[] args) {
    MinerContext minerContext =new MinerContext(new PBFCParser(),new PRFCParser(),new BICSearcher(),
            new TestMigrator(),new ProjectBuilder(),new ProjectsProvider());
    minerContext.start();

    }
}
