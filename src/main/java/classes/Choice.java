package classes;

public class Choice {
    public int TypeChoice;
    public String RootFile;
    public String JdbcUrl;
    public String RootLogsFile;

    public Choice(int typeChoice, String rootFile, String jdbcUrl, String rootLogsFile) {
        TypeChoice = typeChoice;
        RootFile = rootFile;
        JdbcUrl = jdbcUrl;
        RootLogsFile = rootLogsFile;
    }

    public int getTypeChoice() {
        return TypeChoice;
    }

    public void setTypeChoice(int typeChoice) {
        TypeChoice = typeChoice;
    }

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
