package regminer.miner.migrate;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.revwalk.RevCommit;
import regminer.start.ConfigLoader;
import regminer.utils.GitUtil;

import java.io.File;
import java.io.IOException;

public class SourceCodeManager {

//    private final static String workSpace = System.getProperty("user.home") + File.separator + "miner_space";

    private final static String minerSpace = ConfigLoader.rootDir;


    public static File getMetaProjectFile(String projectName) {
        return new File(minerSpace + File.separator + projectName + File.separator + "meta");
    }

    public static void deleteFile(File file) {
        try {
            if (file.exists()) {
                FileUtils.forceDelete(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File checkout(RevCommit commit, File projectFile, String projectName) {
        File commitFile = new File(minerSpace + File.separator + projectName + File.separator + "commits" + File.separator + commit.getName());
        if (commitFile.exists()) {
            return commitFile;
        }

        File commitsDir = new File(minerSpace + File.separator + projectName + File.separator + "commits");
        if (commitsDir.exists() && !commitsDir.isDirectory()) {
            commitsDir.delete();
        }
        commitsDir.mkdirs();

        File commitDir = new File(commitsDir, commit.getName());
        try {
            if (commitDir.exists()) {
                FileUtils.forceDelete(commitDir);
            }
            FileUtils.copyDirectoryToDirectory(projectFile, commitsDir);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        new File(commitsDir + File.separator + "meta").renameTo(commitDir);
        //git checkout
        if (GitUtil.checkout(commit.getName(), commitDir)) {
            return commitDir;
        }
        return null;
    }
}
