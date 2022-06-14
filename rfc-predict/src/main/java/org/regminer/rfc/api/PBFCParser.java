package org.regminer.rfc.api;

import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.regminer.common.model.BranchEntity;
import org.regminer.common.tool.RepositoryProvider;
import org.regminer.rfc.api.core.AbstractPBFCParser;
import org.regminer.rfc.model.PotentialBFC;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: sxz
 * @Date: 2022/06/09/00:10
 * @Description: PBFC ,Step1 :1) addition test case 2）key word "fix，close" in commit message
 * Step2: search testcase ,if not ,remove
 *
 */
public class PBFCParser extends AbstractPBFCParser {
    private TestSearcher testSearcher;
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setTestSearcher(TestSearcher testSearcher) {
        this.testSearcher = testSearcher;
    }

    @Override
    public List<PotentialBFC> getPBFCs(String projectPath) {
        List<PotentialBFC> potentialBFCS =new ArrayList<>();
        //get meta project dir from  projectPath
        File projectDir = new File(projectPath);
        try (Repository repository = RepositoryProvider.getRepoFromLocal(projectDir)){
            // get all branch , i.e. ref
            Collection<Ref> allRefs = repository.getAllRefs().values();
            // foreach allRefs, get PotentialBFC
            allRefs.parallelStream().forEach(ref -> {
               BranchEntity branch = new BranchEntity();
              //  branch.setCommits(ref.getALLcommits);
                branch.setRef(ref);
                //for commits in ref
                // parser(commit,branch,potentialBFCS )
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void parser(RevCommit commit, BranchEntity branch, List<PotentialBFC> potentialBFCList) {
        //if true PBFC ,Step1 :1) addition test case 2）key word "fix，close" in commit message
        PotentialBFC potentialBFC = new PotentialBFC();
        // potentialBFC.setRevCommit(commit);
        potentialBFC.setBranch(branch);
        potentialBFC.setProjectName(projectName);
        potentialBFCList.add(potentialBFC);
    }
}
