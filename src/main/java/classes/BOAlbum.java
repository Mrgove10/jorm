package classes;

import java.io.IOException;
import java.util.Date;

public class BOAlbum extends Album {
    public String Film;
    public int AlbumID;

    public BOAlbum(String film, Integer id, String members, String title, Date dateRelease, Integer albumID) throws IOException {
        super(id, members, title, dateRelease);
        Film = film;
        AlbumID = albumID;
    }
}
