package imports;

import classes.Album;
import logging.logger;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;

public class Import_Album {
    public static void Import_Album(logger log, Connection connection, ObjectInputStream in) throws IOException {
        // Method for deserialization of object
        ArrayList<Album> albums = new ArrayList<Album>();
        try {
            // while no error is catch
            while (true) {
                // read each object in the file
                Album album = (Album) in.readObject();

                // add the object in the list
                albums.add(album);

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
                    String request = "INSERT INTO `Album`(`ID`, `Members`, `Title`, `DateRelease`) VALUES (" +
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
                    if (result != null)
                        result.close();
                    if (state != null)
                        state.close();
                    if (ps != null)
                        ps.close();
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
