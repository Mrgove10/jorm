import classes.Choice;
import exports.ExportFile;
import imports.ImportFile;
import logging.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static logger log = new logger();

    public static void main(String[] args) throws IOException {
        log.AddLog(logger.Severity.Debug, "Program Started");

        // get the parameters enter by the user
        Choice choice = GetParameters();

        // check if the user want to export or import
        if (choice != null) {
            switch (choice.TypeChoice) {
                // if the user want to import
                case 1:
                    log.AddLog(logger.Severity.Debug, "Import chosen");
                    System.out.println("Import in db...");
                    ImportFile importFile = new ImportFile();
                    importFile.Import(choice);
                    break;
                // if the user want to export
                case 2:
                    log.AddLog(logger.Severity.Debug, "Export chosen");
                    System.out.println("Export from db...");
                    ExportFile exportFile = new ExportFile();
                    exportFile.Export(choice);
                    break;
                // if the user as not selected 1 ou 2
                default:
                    System.out.println("Wrong option selected");
                    log.AddLog(logger.Severity.Error, "Wrong option selected");
            }
        } else {
            System.out.println("Wrong option selected");
            log.AddLog(logger.Severity.Error, "Wrong option selected");
        }
    }

    public static Choice GetParameters() throws IOException {
        // get if the user want to import or export
        System.out.println("Press 1 to import a file");
        System.out.println("Press 2 to export from database");
        System.out.println("Press 3 import Debug");
        System.out.println("Press 4 export Debug");
        int type = Integer.parseInt(reader.readLine());
        if (type == 1 || type == 2) {
            // get the root of the file where the user want to export the logs
            System.out.println("Root of the logs file :");
            String logsFile = reader.readLine();

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
            // create an object Choice with the entries of the user
            return new Choice(type, rootFile, jdbcUrl, jdbcUser, jdbcPass, logsFile);
        } else if (type == 3) {
            return new Choice(1, "src/main/java/files/testFileExport.ser", "jdbc:mysql://stephan-server-xl.duckdns.org:3306/TestUser?useSSL=false", "TestUser", "X5m^@g8mXqrj", "src/main/java/files/Logs.log");
        } else if (type == 4) {
            return new Choice(2, "src/main/java/files/testFileExport.ser", "jdbc:mysql://stephan-server-xl.duckdns.org:3306/TestUser?useSSL=false", "TestUser", "X5m^@g8mXqrj", "src/main/java/files/Logs.log");
        }
        return null;
    }
}