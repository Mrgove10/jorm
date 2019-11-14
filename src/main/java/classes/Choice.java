package classes;

public class Choice {
    public int TypeChoice;
    public String RootFile;
    public String JdbcUrl;
    public String JdbcUser;
    public String JdbcPassword;
    public String RootLogsFile;

    public Choice(int typeChoice, String rootFile, String jdbcUrl, String jdbcUser, String jdbcPassword, String rootLogsFile) {
        TypeChoice = typeChoice;
        RootFile = rootFile;
        JdbcUrl = jdbcUrl;
        JdbcUser = jdbcUser;
        JdbcPassword = jdbcPassword;
        RootLogsFile = rootLogsFile;
    }

    //Type
    public int getTypeChoice() {
        return TypeChoice;
    }

    public void setTypeChoice(int typeChoice) {
        TypeChoice = typeChoice;
    }

    //Root
    public String getRootFile() {
        return RootFile;
    }

    public void setRootFile(String rootFile) {
        RootFile = rootFile;
    }

    public String getJdbcUrl() {
        return JdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        JdbcUrl = jdbcUrl;
    }

    public String getRootLogsFile() {
        return RootLogsFile;
    }

    public void setRootLogsFile(String rootLogsFile) {
        RootLogsFile = rootLogsFile;
    }
}
