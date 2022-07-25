package regminer.miner.migrate;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.revwalk.RevCommit;
import regminer.utils.GitUtil;

import java.io.File;
import java.io.IOException;

public class SourceCodeManager {

    private static String workSpace = "E:\\reg-enhance\\data";

    private final static String metaProjectsDirPath = workSpace + File.separator + "meta_projects";
    private final static String cacheProjectsDirPath = workSpace + File.separator + "transfer_cache";

    public static File getMetaProjectFile(String projectName) {
        return new File(metaProjectsDirPath + File.separator + projectName);
    }

    public static void checkTarget(File projectFile) {
        for (File file : projectFile.listFiles()) {
            if (file.getName() == "target") {
                if (file.delete()) {
                    System.out.println("exist target,delete success");
                } else {
                    System.out.println("exist target,delete failed");
                }
            }
        }
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
        File curFile = new File(cacheProjectsDirPath + File.separator + projectName + File.separator + commit.getName());
        if (curFile.exists()) {
            return curFile;
        }
        //copy source code from meta project dir
        File projectCacheDir = new File(cacheProjectsDirPath + File.separator, projectName);
        if (projectCacheDir.exists() && !projectCacheDir.isDirectory()) {
            projectCacheDir.delete();
        }
        projectCacheDir.mkdirs();

        File commitDir = new File(projectCacheDir, commit.getName());
        try {
            if (commitDir.exists()) {
                FileUtils.forceDelete(commitDir);
            }
            FileUtils.copyDirectoryToDirectory(projectFile, projectCacheDir);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        new File(projectCacheDir, projectName).renameTo(commitDir);
        //git checkout
        if (GitUtil.checkout(commit.getName(), commitDir)) {
            return commitDir;
        }
        return null;
    }
}
