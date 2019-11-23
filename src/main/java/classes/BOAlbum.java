package classes;

import java.io.IOException;
import java.util.Date;

public class BOAlbum extends Album {
    public String Film;

    public BOAlbum(String film, Integer id, String members, String title, Date dateRelease) throws IOException {
        super(id, members, title, dateRelease);
        Film = film;
    }
}
