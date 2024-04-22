import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Series> seenSeries;
    private ArrayList<Film> seenFilm;
    private ArrayList<Video> savedVideo;
    private String newPassword;

    public User(String name, String newPassword) {
        this.name = name;
        this.savedVideo = savedVideo;
        this.seenFilm = seenFilm;
        this.seenSeries = seenSeries;

        this.newPassword = newPassword;
    }

//methods

    //This method is used to save a list of videos that the user has seen.
        public void watchedVideo(Stream stream){    //needs stream parameter for stream object to be visible for the method
            //todo need getter method to get names of films that have been seen
            ArrayList<String> seenFilmNames = new ArrayList<>();
            for (String s : seenFilmNames){
                for (Film f : stream.getFilmList()){
                    if (f.getName().equals(s)){
                        Film film = f;
                        seenFilm.add(f);
                    }
                }
            }
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


    public void watchedVideo(Stream stream){    //needs stream parameter for stream object to be visible for the method
        //todo need getter method to get names of films that have been seen
        ArrayList<String> seenFilmNames = new ArrayList<>();
        for (String s : seenFilmNames){
            for (Film f : stream.getFilmList()){
                if (f.getName().equals(s)){
                    Film film = f;
                    seenFilm.add(f);
                }
            }
        }
    }

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