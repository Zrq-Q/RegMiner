package regminer.utils;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;

/**
 * description:
 *
 * @author Richy
 * create: 2022-07-04 14:45
 **/
public class RepositoryUtil {
    public static Repository getRepoFromLocal(File codeDir) throws Exception {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder
                .readEnvironment()
                .findGitDir(codeDir)
                .build();
    }
}