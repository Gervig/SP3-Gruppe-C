import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Series> seenSeries;
    private ArrayList<Film> seenFilm;
    private ArrayList<Video> savedVideo;
    private String newPassword;


    public User(String name, String newPassword) {
        this.name = name;
        this.savedVideo = new ArrayList<>();
        this.seenFilm = new ArrayList<>();
        this.seenSeries = new ArrayList<>();
        this.newPassword = newPassword;
    }

//methods
//vi skal have det over til objekter i stedet for strenge og så få det enkelte objekt derhen
    //This method is used to save a list of videos that the user has seen.
        public void watchedFilm(Film film){    //needs stream parameter for stream object to be visible for the method
        seenFilm.add(film);
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