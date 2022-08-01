package regminer.model;

/**
 * description:
 *
 * @author Richy
 * create: 2022-07-04 10:40
 **/
public class PBFC {
    private String commitId;
    private String projectName;
    private String commitMessage;

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }
}