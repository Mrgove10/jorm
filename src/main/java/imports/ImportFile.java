package imports;

import classes.Choice;
import logging.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ImportFile {
    private static logger log = new logger();

    public void Import(Choice parameters) throws IOException {
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(parameters.RootFile);
            ObjectInputStream in = new ObjectInputStream(file);
            Connection connection = DriverManager.getConnection(parameters.JdbcUrl, parameters.JdbcUser, parameters.JdbcPassword);

            Import_Album.Import_Album(log, connection, in);
            Import_LiveAlbum.Import_LiveAlbum(log, connection, in);
            Import_BOAlbum.Import_BOAlbum(log, connection, in);

            file.close();
            in.close();
            connection.close();
        } catch (IOException ex) {
            System.out.println("IOException is caught");
            System.out.println(ex.getMessage());
            log.AddLog(logger.Severity.Error, ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
