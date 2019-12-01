package imports;

import classes.Album;
import classes.BOAlbum;
import classes.LiveAlbum;
import logging.logger;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Import_Album {
    public static void Import_Album(logger log, Connection connection, ObjectInputStream in) throws IOException {
        // Method for deserialization of object
        ArrayList<Album> albums = new ArrayList<>();
        ArrayList<BOAlbum> boalbums = new ArrayList<>();
        ArrayList<LiveAlbum> livealbums = new ArrayList<>();
        try {
            // while no error is catch
            while (true) {
                // read each object in the file
                Album album = (Album) in.readObject();

                if (album instanceof BOAlbum) {
                    boalbums.add((BOAlbum) album);
                    log.AddLog(logger.Severity.Debug, "BOAlbum " + album.Id + " has been deserialized");
                    System.out.println("BOAlbum " + album.Id + " has been deserialized");
                } else if (album instanceof LiveAlbum) {
                    livealbums.add((LiveAlbum) album);
                    log.AddLog(logger.Severity.Debug, "LiveAlbum " + album.Id + " has been deserialized");
                    System.out.println("LiveAlbum " + album.Id + " has been deserialized");
                } else if (album != null) {
                    // add the object in the list
                    albums.add(album);
                    log.AddLog(logger.Severity.Debug, "Album " + album.Id + " has been deserialized");
                    System.out.println("Album " + album.Id + " has been deserialized");
                }
            }
        } catch (EOFException ex) {
            // the exception is catch when all objects have been already read
            System.out.println("End of the file");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
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
                log.AddLog(logger.Severity.Debug, "Album " + album.Id + " has been uploaded to database");
                System.out.println("Album " + album.Id + " has been uploaded to database");
            }
            log.AddLog(logger.Severity.Debug, "All Album have been uploaded to database");
            System.out.println("All Album have been uploaded to database");

            //calls the over functions
            Import_BOAlbum.Import_BOAlbum(log, connection, in, boalbums);
            Import_LiveAlbum.Import_LiveAlbum(log, connection, in, livealbums);
        } catch (Exception e) {
            e.printStackTrace();
            log.AddLog(logger.Severity.Error, e.getMessage());
        }
    }
}
