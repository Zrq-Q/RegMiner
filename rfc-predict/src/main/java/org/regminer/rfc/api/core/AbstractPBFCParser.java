package org.regminer.rfc.api.core;


import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.regminer.common.model.BranchEntity;
import org.regminer.rfc.model.PotentialBFC;

import java.io.File;
import java.util.List;

/**
 * @Author: sxz
 * @Date: 2022/06/08/23:17
 * @Description:
 */
public abstract class AbstractPBFCParser {
    // Given meta project path, out PotentialBFC List
    public abstract List<PotentialBFC> getPBFCs(String projectPath);

    public abstract List<PotentialBFC> parser(File projectDir, RevCommit commit,List<PotentialBFC> potentialBFCList);
}
