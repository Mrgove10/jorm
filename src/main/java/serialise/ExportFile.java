package serialise;

import classes.Album;
import classes.Choice;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.*;

public class ExportFile {
    // = serialization
    public void Export(Choice parameters) {
        Connection connection = null;
        Statement state = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        try {
            connection = DriverManager.getConnection(parameters.JdbcUrl, parameters.JdbcUser, parameters.JdbcPassword);
            state = connection.createStatement();
            result = state.executeQuery("SELECT * FROM Album");

            // open a stream of the file where the user want to export
            FileOutputStream file = new FileOutputStream(parameters.RootFile);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // while we don't have export all the object of the database
            while (result.next()) {
                // get the info of the album
                int id = result.getInt("ID");
                String members = result.getString("Members");
                String title = result.getString("Title");
                Date dateRelease = result.getDate("DateRelease");

                // send it to the object
                Album album = new Album(id, members, title, dateRelease);

                // write the object in the file
                out.writeObject(album);

                System.out.println("Object has been serialized");
            }

            // then close all the connection
            out.flush();
            out.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            try {
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
    }
}