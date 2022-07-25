package regminer.start;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import regminer.miner.PotentialBFCDetector;
import regminer.utils.RepositoryUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;

/**
 * description:
 *
 * @author Richy
 * create: 2022-07-04 14:27
 **/
public class GetPBFC {
    private static String workSpace = "E:\\reg-enhance\\data\\meta_projects";

    public static void main(String[] args) throws Exception {

        File projectFile = new File(workSpace + File.separator + "commons-jexl ");
        Repository repo = RepositoryUtil.getRepoFromLocal(projectFile);
        Git git = new Git(repo);
        PotentialBFCDetector pBFCDetector = new PotentialBFCDetector(repo, git);
        pBFCDetector.detectPotentialBFC();
    }
}