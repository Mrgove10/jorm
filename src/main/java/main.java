import classes.Choice;
import fileReader.ImportFile;
import logging.logger;
import serialise.ExportFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static logger log = new logger();

    public static void main(String[] args) throws IOException {
        System.out.println("- J'ai pas encore mis en place le fichier de log");
        log.AddLog(logger.Severity.Debug, "Example debug log");
        log.AddLog(logger.Severity.Warning, "Example warning log");
        log.AddLog(logger.Severity.Error, "Example error log");
        // get the parameters enter by the user
        Choice choice = GetParameters();

        // check if the user want to export or import
        switch (choice.TypeChoice) {
            // if the user want to import
            case 1:
                System.out.println("Import in db...");
                ImportFile importFile = new ImportFile();
                importFile.Import(choice);
                break;
            // if the user want to export
            case 2:
                System.out.println("Export from db...");
                ExportFile exportFile = new ExportFile();
                exportFile.Export(choice);
                break;
            // if the user as not selected 1 ou 2
            default:
                System.out.println("Wrong option selected");
        }
    }

    public static Choice GetParameters() throws IOException {

        // get if the user want to import or export
        System.out.println("Press 1 to import a file");
        System.out.println("Press 2 to export from database");
        int type = Integer.parseInt(reader.readLine());

        // get the root of the file to import or the root of the file where the user want to export
        System.out.println("Root of the file export/import:");
        String rootFile = reader.readLine();

        // get the jdbc url of the user
        System.out.println("URL JDBC :");
        String jdbcUrl = reader.readLine();

        // get the jdbc user of the user
        System.out.println("Database user:");
        String jdbcUser = reader.readLine();

        // get the jdbc password of the user
        System.out.println("Database password :");
        String jdbcPass = reader.readLine();

        // get the root of the file where the user want to export the logs
        System.out.println("Root of the logs file :");
        String logsFile = reader.readLine();

        // create an object Choice with the entries of the user
        Choice choiceAction = new Choice(type, rootFile, jdbcUrl, jdbcUser, jdbcPass, logsFile);

        return choiceAction;
    }
}
