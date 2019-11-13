import classes.Choice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        Choice choice = GetParameters();

        switch (choice.TypeChoice){
            case 1:
                System.out.println("Import in db...");
                break;
            case 2:
                System.out.println("Export from db...");
                break;
            default:
                System.out.println("Wrong option selected");
        }
    }

    public static Choice GetParameters() throws IOException {
        Choice choiceAction = new Choice();

        System.out.println("Press 1 to import a file");
        System.out.println("Press 2 to export from database");
        int type = Integer.parseInt(reader.readLine());

        System.out.println("Root of the file export/import:");
        String rootFile = reader.readLine();

        System.out.println("URL JDBC :");
        String jdbcUrl = reader.readLine();

        System.out.println("Root of the logs file :");
        String logsFile = reader.readLine();

        choiceAction.TypeChoice = type;
        choiceAction.RootFile = rootFile;
        choiceAction.JdbcUrl = jdbcUrl;
        choiceAction.RootLogsFile = logsFile;

        return choiceAction;
    }
}
