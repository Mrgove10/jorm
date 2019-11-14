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
}
