package fileReader;

import classes.Album;
import classes.Choice;
import config.db_config;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;

public class ImportFile {
    // = deserialization
    public void Import(Choice parameters){
        try
        {
            // Reading the object from a file
            //"src/main/java/fileReader/testFile.ser"
            FileInputStream file = new FileInputStream(parameters.RootFile);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            ArrayList<Album> albums = new ArrayList<Album>();
            try{
                while(true){
                    Album album = (Album)in.readObject();
                    albums.add(album);

                    System.out.println("Object has been deserialized ");
                    System.out.println(album.Title);
                }
            }catch(EOFException ex){
                System.out.println("End of the file");
            }finally {
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
                    for (Album album:albums) {
                        String request = "INSERT INTO `Album`(`ID`, `Members`, `Title`, `DateRelease`) " +
                                        "VALUES ("+album.Id+",'"+album.Members+"','"+album.Title+"','"+album.DateRelease+"')";
                        state.executeUpdate(request);
                    }
                }catch (Exception e) {
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
                in.close();
                file.close();
            }
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    }
}
