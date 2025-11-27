package com.example.tracks;

public class TrackItem {
    private String trackName;
    private String raceDistance;
    private String NumberOfLaps;
    private String FirstGrandPrix;
    private String photo;

    public TrackItem() { }

    public TrackItem(String trackName, String raceDistance, String Number0fLaps, String FirstGrandPrix, String photo) {
        this.trackName = trackName;
        this.raceDistance = raceDistance;
        this.NumberOfLaps = NumberOfLaps;
        this.FirstGrandPrix = FirstGrandPrix;
        this.photo = photo;
    }

    public String getTrackName() { return trackName; }
    public String getRaceDistance() { return raceDistance; }
    public String getNumberOfLaps() { return NumberOfLaps; }
    public String getFirstGrandPrix() { return FirstGrandPrix; }
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
