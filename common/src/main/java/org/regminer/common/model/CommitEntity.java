package org.regminer.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 * @Author: sxz
 * @Date: 2022/06/09/00:48
 * @Description:
 */
public abstract class CommitEntity {
    private String projectName;
    RevCommit revCommit;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public RevCommit getRevCommit() {
        return revCommit;
    }

    public void setRevCommit(RevCommit revCommit) {
        this.revCommit = revCommit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CommitEntity)) return false;

        CommitEntity that = (CommitEntity) o;

        return new EqualsBuilder().append(getProjectName(), that.getProjectName()).append(getRevCommit(), that.getRevCommit()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProjectName()).append(getRevCommit()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("projectName", projectName)
                .append("revCommit", revCommit)
                .toString();
    }
}
