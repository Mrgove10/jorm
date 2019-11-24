package exports;

import classes.Album;
import logging.logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

public class Export_Album {
    public static void Export_Album(Connection connection, FileOutputStream file, ObjectOutputStream out, logger log) throws SQLException, IOException {
        Statement state = null;
        ResultSet result = null;
        try {
            state = connection.createStatement();
            result = state.executeQuery("SELECT * FROM Album");
            // while we don't have export all the object of the database
            while (result.next()) {
                // get the info of the album
                int id = result.getInt("ID");
                log.AddLog(logger.Severity.Debug, "Adding Album " + id);
                String members = result.getString("Members");
                String title = result.getString("Title");
                Date dateRelease = result.getDate("DateRelease");

                // send it to the object
                Album album = new Album(id, members, title, dateRelease);

                // write the object in the file
                out.writeObject(album);
                log.AddLog(logger.Severity.Debug, "Object has been serialized");
                System.out.println("Object has been serialized");
            }
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.AddLog(logger.Severity.Error, e.toString());
        } finally {
            if (result != null)
                result.close();
            if (state != null)
                state.close();
        }
    }
}
