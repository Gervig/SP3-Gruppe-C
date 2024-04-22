import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Series> seenSeries;
    private ArrayList<Film> seenFilm;
    private ArrayList<Video> savedVideo;
    private ArrayList<Video> seenVideo;
    private String newPassword;

    public User(String name, String newPassword) {
        this.name = name;
        this.savedVideo = savedVideo;
        this.seenVideo = seenVideo;
        this.newPassword = newPassword;
    }

//methods

    //This method is used to save a list of videos that the user has seen.
    public boolean watchedVideo(Video video) {
        if (video instanceof Series && video.playVideo(video)) {
            seenSeries.add((Series) video);
        } else if (video instanceof Film) {
            seenFilm.add((Film) video);
        }
        return true;
    }

    //This method is used to add videos to the users list of savedVideos
    public void addToSaved(Video video) {
        //Denne skal kunne gemme fra enten search eller hele listen
            savedVideo.add(video);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return newPassword;
    }

    /*Vi vil lave en funktion der sammenligner search-string med objekterne og derefter gemmer den i historik/saved */


    //Getters so that Stream-class can call the lists
    public ArrayList<Series> getSeenSeries() {
        return seenSeries;
    }

    public ArrayList<Film> getSeenFilm() {
        return seenFilm;
    }

    public ArrayList<Video> getSavedVideo() {
        return savedVideo;
    }

}