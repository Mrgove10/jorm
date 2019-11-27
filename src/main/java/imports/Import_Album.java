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
                }

                System.out.println("Object has been deserialized ");
                System.out.println(album.Title);
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
                    // insert it in the table Album
                    String request = "REPLACE INTO `Album`(`ID`, `Members`, `Title`, `DateRelease`) VALUES (" +
                            album.Id + ",'" +
                            album.Members + "','" +
                            album.Title + "','" +
                            album.DateRelease + "')";
                    log.AddLog(logger.Severity.Debug, request);
                    state.executeUpdate(request);
                }
                log.AddLog(logger.Severity.Debug, "File has been Imported");

            } catch (Exception e) {
                e.printStackTrace();
                log.AddLog(logger.Severity.Error, e.getMessage());
            } finally {
                try {
                    // close all the connection
                    if (state != null)
                        state.close();
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            in.close();
        }
    }
}
