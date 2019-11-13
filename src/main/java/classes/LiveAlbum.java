package classes;

import java.util.Date;

public class LiveAlbum extends Album {

    public String PlaceOfRecording;

    public LiveAlbum(String placeOfRecording,Integer id, String members, String title, Date dateRelease) {
        super(id, members, title, dateRelease);
        PlaceOfRecording = placeOfRecording;
    }

    public String getPlaceOfRecording() {
        return PlaceOfRecording;
    }

    public void setPlaceOfRecording(String placeOfRecording) {
        PlaceOfRecording = placeOfRecording;
    }
}
