package classes;

import java.util.Date;

public class BOAlbum extends Album {
    public String Film;

    public BOAlbum(String film, String members, String title, Date dateRelease) {
        super(members, title, dateRelease);
        Film = film;
    }

    public String getFilm() {
        return Film;
    }

    public void setFilm(String film) {
        Film = film;
    }
}
