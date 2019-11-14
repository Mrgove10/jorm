package fileReader;

import classes.Album;
import classes.Choice;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;

public class ImportFile {
    // = deserialization
    public void Import(Choice parameters) {
        try {
            // Reading the object from a file
            //"src/main/java/fileReader/testFile.ser"
            FileInputStream file = new FileInputStream(parameters.RootFile);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            ArrayList<Album> albums = new ArrayList<Album>();
            try{
                // while no error is catch
                while(true){
                    // read each object in the file
                    Album album = (Album)in.readObject();

                    // add the object in the list
                    albums.add(album);

                    System.out.println("Object has been deserialized ");
                    System.out.println(album.Title);
                }
            }catch(EOFException ex){
                // the exception is catch when all objects have been already read
                System.out.println("End of the file");
            }finally {

                Connection connection = null;
                Statement state = null;
                PreparedStatement ps = null;
                ResultSet result = null;
                try {
                    connection = DriverManager.getConnection(parameters.JdbcUrl, parameters.JdbcUser, parameters.JdbcPassword);
                    state = connection.createStatement();

                    // foreach object in the list albums
                    for (Album album:albums) {
                        // insert it in the table Album
                        String request = "INSERT INTO `Album`(`ID`, `Members`, `Title`, `DateRelease`) " +
                                "VALUES (" + album.Id + ",'" + album.Members + "','" + album.Title + "','" + album.DateRelease + "')";
                        state.executeUpdate(request);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e);
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
                file.close();
            }
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }
}
