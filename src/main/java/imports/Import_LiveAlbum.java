package imports;

import classes.Album;
import classes.LiveAlbum;
import logging.logger;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Import_LiveAlbum {
    public static void Import_LiveAlbum(logger log, Connection connection, ObjectInputStream in) throws IOException {
        // Method for deserialization of object
        ArrayList<LiveAlbum> albums = new ArrayList<>();
        try {
            // while no error is catch
            while (true) {
                // read each object in the file
                Album livealbum = (Album) in.readObject();

                if (livealbum instanceof LiveAlbum) {
                    // add the object in the list
                    albums.add((LiveAlbum) livealbum);
                    log.AddLog(logger.Severity.Debug, "LiveAlbum has been deserialized");
                    System.out.println("LiveAlbum has been deserialized");
                }
            }
        } catch (EOFException ex) {
            // the exception is catch when all objects have been already read
            System.out.println("End of the file");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            Statement state = null;
            try {
                state = connection.createStatement();
                // foreach object in the list albums
                for (LiveAlbum livealbum : albums) {
                    String query = "REPLACE INTO `LiveAlbum`(`ID`, `AlbumID`, `PlaceOfRecording`) VALUES (?,?,?)";
                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setInt(1, livealbum.Id);
                    preparedStmt.setInt(2, livealbum.AlbumID );
                    preparedStmt.setString(3,livealbum.PlaceOfRecording);

                    // execute the preparedstatement
                    preparedStmt.execute();
                    log.AddLog(logger.Severity.Debug, query);
                }
                log.AddLog(logger.Severity.Debug, "LiveAlbum has been uploaded to database");
                System.out.println("LiveAlbum has been uploaded to database");

            } catch (Exception e) {
                e.printStackTrace();
                log.AddLog(logger.Severity.Error, e.getMessage());
            } finally {
                try {
                    // close all the connection
                    if (state != null)
                        state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
