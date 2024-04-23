import java.util.ArrayList;
public abstract class Video implements IVideo {
private String name;
private String genre;
private float rating;
private boolean hasSeen;
private String releaseDate;

    //constructor
    public Video(String name, String releaseDate, String genre, float rating) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }
    public Video(String name){
        this.name = name;
    }
    public Video(String name, int rating){
        this.name = name;
        this.rating = rating;
    }

//methods
public String getReleaseDate(){
    return this.releaseDate;
}
public String getName(){
    return name;
}
public String getGenre() {
    return genre;
}
public float getRating(){
return rating;
}
public boolean playVideo(Video video) {
        //todo msg skal soutes i TextUI
        String msg = video.getName() + " is now playing...";

        return true;
    }

    @Override
    public String toString() {
        return "Title: " + getName() + ", released in: " + getReleaseDate() + "\nGenre: " + getGenre();
    }
}
