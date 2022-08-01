package regminer.sql;

import regminer.model.PBFC;

import java.util.List;

/**
 * description:
 *
 * @author Richy
 * create: 2022-07-04 10:43
 **/
public class PBFCDao {

    public static void storagePBFC(PBFC Pbfc) {
        String sql = "insert into pbfc_notest (commit_id,project_name,commit_message) VALUES" +
                "('" + Pbfc.getCommitId() + "','" + Pbfc.getProjectName() + "','"
                + Pbfc.getCommitMessage() + "')";
        MysqlManager.executeUpdate(sql);
    }

    public static void setPTest(String commitId, List<String> testcase) {
        String sql = "update pbfc SET testcase = '" + testcase +"' where commit_id = '" + commitId + "'";
        MysqlManager.executeUpdate(sql);
    }

    public static void storageOldPBFC(PBFC Pbfc, Integer searchType) {
        String sql = "insert into pbfc_old (commit_id,project_name,search_type) VALUES" +
                "('" + Pbfc.getCommitId() + "','" + Pbfc.getProjectName() + "','"
                + searchType + "')";
        MysqlManager.executeUpdate(sql);
    }

    public static void storagePBFC95(PBFC Pbfc, Integer searchType,List<String> assumeTest) {
        String sql = "insert into pbfc (commit_id,project_name,search_type,testcase) VALUES" +
                "('" + Pbfc.getCommitId() + "','" + Pbfc.getProjectName() + "','"
                + searchType + "','"+ assumeTest +"')";
        MysqlManager.executeUpdate(sql);
    }
}