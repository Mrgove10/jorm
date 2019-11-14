package classes;

import java.util.Date;

public class LiveAlbum extends Album {

    public String PlaceOfRecording;

    public LiveAlbum(String placeOfRecording, Integer id, String members, String title, Date dateRelease) {
        super(id, members, title, dateRelease);
        PlaceOfRecording = placeOfRecording;
    }
}
