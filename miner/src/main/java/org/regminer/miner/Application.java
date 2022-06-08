package org.regminer.miner;

import org.regminer.miner.api.*;

public class Application {

    public static void main(String[] args) {
    MinerContext minerContext =new MinerContext(new PBFCParser(),new PRFCParser(),new BICSearcher(),
            new TestMigrator(),new ProjectBuilder(),new ProjectsProvider());
    minerContext.start();

    }
}
