package imports;

import classes.LiveAlbum;
import logging.logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Import_LiveAlbum {
    public static void Import_LiveAlbum(logger log, Connection connection, ObjectInputStream in, ArrayList<LiveAlbum> livealbums) throws IOException {
        // Method for deserialization of object
        Statement state = null;
        try {
            state = connection.createStatement();
            // foreach object in the list albums
            for (LiveAlbum livealbum : livealbums) {
                String query = "REPLACE INTO `LiveAlbum`(`ID`, `AlbumID`, `PlaceOfRecording`) VALUES (?,?,?)";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, livealbum.Id);
                preparedStmt.setInt(2, livealbum.AlbumID);
                preparedStmt.setString(3, livealbum.PlaceOfRecording);

                // execute the preparedstatement
                preparedStmt.execute();
                log.AddLog(logger.Severity.Debug, "LiveAlbum " + livealbum.Id + " has been uploaded to database");
                System.out.println("LiveAlbum " + livealbum.Id + " has been uploaded to database");
            }
            log.AddLog(logger.Severity.Debug, "All LiveAlbum have been uploaded to database");
            System.out.println("All LiveAlbum have been uploaded to database");
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
