package classes;

import java.io.IOException;
import java.util.Date;

public class LiveAlbum extends Album {
    public String PlaceOfRecording;
    public int AlbumID;

    public LiveAlbum(String placeOfRecording, Integer id, String members, String title, Date dateRelease, int albumID) throws IOException {
        //Todo : missing albumID
        super(id, members, title, dateRelease);
        PlaceOfRecording = placeOfRecording;
        AlbumID = albumID;
    }
}
