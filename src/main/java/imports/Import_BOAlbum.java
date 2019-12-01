package imports;

import classes.BOAlbum;
import logging.logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Import_BOAlbum {
    public static void Import_BOAlbum(logger log, Connection connection, ObjectInputStream in, ArrayList<BOAlbum> boalbums) throws IOException {
        // Method for deserialization of object
        Statement state = null;
        try {
            state = connection.createStatement();
            // foreach object in the list albums
            for (BOAlbum boalbum : boalbums) {
                String query = "REPLACE INTO `BOAlbum`(`ID`, `AlbumID`, `Film`) VALUES (?,?,?)";
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, boalbum.Id);
                preparedStmt.setInt(2, boalbum.AlbumID);
                preparedStmt.setString(3, boalbum.Film);

                // execute the preparedstatement
                preparedStmt.execute();
                log.AddLog(logger.Severity.Debug, "BOAlbum " + boalbum.Id + " has been uploaded to database");
                System.out.println("BOAlbum " + boalbum.Id + " has been uploaded to database");
            }
            log.AddLog(logger.Severity.Debug, "All BOAlbum have been uploaded to database");
            System.out.println("All BOAlbum have been uploaded to database");
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
