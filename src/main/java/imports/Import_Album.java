package imports;

import classes.Album;
import classes.BOAlbum;
import classes.LiveAlbum;
import logging.logger;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;

public class Import_Album {
    public static void Import_Album(logger log, Connection connection, ObjectInputStream in) throws IOException {
        // Method for deserialization of object
        ArrayList<Album> albums = new ArrayList<>();
        try {
            // while no error is catch
            while (true) {
                // read each object in the file
                Album album = (Album) in.readObject();

                if (album instanceof BOAlbum || album instanceof LiveAlbum) {
                    //not a "pure album"
                } else {
                    // add the object in the list
                    albums.add(album);
                    log.AddLog(logger.Severity.Debug, "Album has been deserialized");
                    System.out.println("Album has been deserialized");
                }
            }
        } catch (EOFException ex) {
            // the exception is catch when all objects have been already read
            System.out.println("End of the file");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            Statement state = null;
            PreparedStatement ps = null;
            ResultSet result = null;
            try {
                state = connection.createStatement();
                // foreach object in the list albums
                for (Album album : albums) {

                    String query = "REPLACE INTO `Album`(`ID`, `Members`, `Title`, `DateRelease`) VALUES (?,?,?,?)";
                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setInt(1, album.Id);
                    preparedStmt.setString(2, album.Members);
                    preparedStmt.setString(3, album.Title);
                    preparedStmt.setDate(4, (Date) album.DateRelease);

                    // execute the preparedstatement
                    preparedStmt.execute();

                    log.AddLog(logger.Severity.Debug, query);
                }
                log.AddLog(logger.Severity.Debug, "Album has been uploaded to database");
                System.out.println("Album has been uploaded to database");

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
