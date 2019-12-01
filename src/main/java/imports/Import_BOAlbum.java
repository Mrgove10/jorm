package imports;

import classes.Album;
import classes.BOAlbum;
import logging.logger;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;

public class Import_BOAlbum {
    public static void Import_BOAlbum(logger log, Connection connection, ObjectInputStream in) throws IOException {
        // Method for deserialization of object
        ArrayList<BOAlbum> albums = new ArrayList<>();
        try {
            // while no error is catch
            while (true) {
                // read each object in the file
                Album boalbum = (Album) in.readObject();

                if (boalbum instanceof BOAlbum) {
                    // add the object in the list
                    albums.add((BOAlbum) boalbum);
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
                for (BOAlbum boalbum : albums) {

                    String query = "REPLACE INTO `BOAlbum`(`ID`, `AlbumID`, `Film`) VALUES (?,?,?)";
                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setInt(1, boalbum.Id);
                    preparedStmt.setInt(2, boalbum.AlbumID );
                    preparedStmt.setString(3,boalbum.Film);

                    // execute the preparedstatement
                    preparedStmt.execute();
                    log.AddLog(logger.Severity.Debug, query);
                }
                log.AddLog(logger.Severity.Debug, "BOAlbum has been uploaded to database");
                System.out.println("BOAlbum has been uploaded to database");

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
