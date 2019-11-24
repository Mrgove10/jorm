package export;

import classes.Choice;
import logging.logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExportFile {
    private static logger log = new logger();

    //serialization
    public void Export(Choice parameters) throws IOException {

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DriverManager.getConnection(parameters.JdbcUrl, parameters.JdbcUser, parameters.JdbcPassword);

            // open a stream of the file where the user want to export
            FileOutputStream file = new FileOutputStream(parameters.RootFile);
            ObjectOutputStream out = new ObjectOutputStream(file);

            //Exporting all the files
            Export_Album.Export_Album(connection, file, out, log);
            Export_BOAlbum.Export_BOAlbum(connection, file, out, log);
            Export_LiveAlbum.Export_LiveAlbum(connection, file, out, log);

            // then close all the connection
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            log.AddLog(logger.Severity.Error, e.toString());
        } finally {
            try {

                if (ps != null)
                    ps.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                log.AddLog(logger.Severity.Error, e.getMessage());
            }
        }
    }
}
