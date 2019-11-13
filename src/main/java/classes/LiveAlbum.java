package classes;

import java.util.Date;

public class LiveAlbum extends Album {

    public String PlaceOfRecording;

    public LiveAlbum(String placeOfRecording, String members, String title, Date dateRelease) {
        super(members, title, dateRelease);
        PlaceOfRecording = placeOfRecording;
    }

    public String getPlaceOfRecording() {
        return PlaceOfRecording;
    }

    public void setPlaceOfRecording(String placeOfRecording) {
        PlaceOfRecording = placeOfRecording;
    }
}
