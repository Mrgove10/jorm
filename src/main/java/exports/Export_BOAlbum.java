package exports;

import classes.BOAlbum;
import logging.logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Export_BOAlbum {
    public static void Export_BOAlbum(Connection connection, FileOutputStream file, ObjectOutputStream out, logger log) throws SQLException, IOException {
        Statement state = null;
        ResultSet result = null;
        try {
            state = connection.createStatement();
            result = state.executeQuery("SELECT * FROM `BOAlbum`,`Album` WHERE `BOAlbum`.`AlbumID` = `Album`.`ID`");
            // while we don't have export all the object of the database
            while (result.next()) {
                // get the info of the album
                int id = result.getInt("ID");
                log.AddLog(logger.Severity.Debug, "Adding LiveAlbum " + id);
                int AlbumID = result.getInt("AlbumID");
                String Film = result.getString("Film");
                String members = result.getString("Members");
                String title = result.getString("Title");
                java.sql.Date dateRelease = result.getDate("DateRelease");

                // send it to the object
                BOAlbum album = new BOAlbum(Film, id, members, title, dateRelease,AlbumID);

                // write the object in the file
                out.writeObject(album);
                log.AddLog(logger.Severity.Debug, "BOAlbum has been serialized");
                System.out.println("BOAlbum has been serialized");
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
