package classes;

import java.io.IOException;
import java.util.Date;

public class LiveAlbum extends Album {

    public String PlaceOfRecording;

    public LiveAlbum(String placeOfRecording, Integer id, String members, String title, Date dateRelease) throws IOException {
        //Todo : missing albumID
        super(id, members, title, dateRelease);
        PlaceOfRecording = placeOfRecording;
    }
}
