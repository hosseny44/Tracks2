package com.example.tracks;

public class F1Track {
    private String trackName;
    private String raceDistance;
    private String numberOfLaps;
    private String firstGrandPrix;
    private String photo;


    // Empty constructor is required for Firestore
    public F1Track() {}
    public F1Track(String trackName, String raceDistance, String numberofLaps, String firstGrandPrix ,
    String imageUrl) {
        this.trackName = trackName;
        this.raceDistance = raceDistance;
        this.numberOfLaps = numberofLaps;
        this.firstGrandPrix = firstGrandPrix;
        this.photo = photo;

    }

    // Getters and setters
    public String getTrackName() { return trackName; }
    public void setTrackName(String trackName) { this.trackName = trackName; }

    public String getRaceDistance() { return raceDistance; }
    public void setRaceDistance(String raceDistance) { this.raceDistance = raceDistance; }

    public String getNumberOfLaps() { return numberOfLaps; }
    public void setNumberOfLaps(String numberOfLaps) { this.numberOfLaps = numberOfLaps; }

    public String getFirstGrandPrix() { return firstGrandPrix; }
    public void setFirstGrandPrix(String firstGrandPrix) { this.firstGrandPrix = firstGrandPrix; }
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
}
