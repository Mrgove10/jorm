package serialise;

import classes.Album;
import classes.Choice;
import config.db_config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

public class ExportFile {
    // = serialization
    public void Export(Choice parameters){
        db_config conf = new db_config();
        String url = conf.url;
        String user = conf.user;
        String mdp = conf.mdp;

        Connection connection = null;
        Statement state = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        try {
            connection = DriverManager.getConnection(url, user, mdp);
            state = connection.createStatement();
            result = state.executeQuery("SELECT * FROM Album");

            // "src/main/java/serialise/testFile.ser"
            FileOutputStream file = new FileOutputStream(parameters.RootFile);
            ObjectOutputStream out = new ObjectOutputStream(file);

            while (result.next()) {
                int id = result.getInt("ID");
                String members = result.getString("Members");
                String title = result.getString("Title");
                Date dateRelease = result.getDate("DateRelease");

                Album album = new Album(id, members, title, dateRelease);

                out.writeObject(album);

                System.out.println("Object has been serialized");
            }
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
