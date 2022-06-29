package org.regminer.rfc.api;

import org.apache.commons.collections4.IteratorUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevCommitList;
import org.regminer.common.model.BranchEntity;
import org.regminer.common.tool.RepositoryProvider;
import org.regminer.common.utils.FileUtil;
import org.regminer.common.utils.GitUtils;
import org.regminer.rfc.api.core.AbstractPBFCParser;
import org.regminer.rfc.model.PotentialBFC;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sxz
 * @Date: 2022/06/09/00:10
 * @Description: PBFC ,Step1 :1) addition test case 2）key word "fix，close" in commit message
 * Step2: search testcase ,if not ,remove
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
        List<PotentialBFC> potentialBFCList = new ArrayList<>();
        //get meta project dir from  projectPath
        File projectDir = new File(projectPath);
        try (Repository repository = RepositoryProvider.getRepoFromLocal(projectDir); Git git = new Git(repository)) {
            Iterable<RevCommit> commits = git.log().all().call();
            for (RevCommit commit : commits) {
                parser(projectDir, commit, potentialBFCList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return potentialBFCList;
    }

/*    public List<RevCommit> getCommitsInBranch(Ref ref, Git git) throws Exception {
        List<RevCommit> commits = new ArrayList<>();
        commits = IteratorUtils.toList(git.log().add(ref.getObjectId()).call().iterator());
        return commits;
    }*/

    @Override
    public List<PotentialBFC> parser(File projectDir, RevCommit commit, List<PotentialBFC> potentialBFCList) {
        /*
         * filter BFC:
         * additonal test
         * (1) file path includes "test" && file path endwith ".java"
         * (2) code contains "junit" or "@Test"
         * */
        Boolean additonalTest = false;
        String commitId = commit.getName();

        GitUtils.checkout(commitId, projectDir);
        File[] fileList = projectDir.listFiles();
        for (File file : fileList) {
            if (additonalTest) {
                continue;
            }
            String filePath = file.getPath().toLowerCase();
            if (filePath.contains("test") && filePath.endsWith(".java")) {
                String code = FileUtil.readContentFromFile(file);
                if (code.contains("junit") || code.contains("@Test")) {
                    additonalTest = true;
                }
            }
        }
        if (additonalTest) {
            PotentialBFC potentialBFC = new PotentialBFC();
            potentialBFC.setRevCommit(commit);
            potentialBFC.setProjectName(projectName);
            potentialBFCList.add(potentialBFC);
        }
        return potentialBFCList;


    }
}
