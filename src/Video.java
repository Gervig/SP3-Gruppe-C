import java.util.ArrayList;
public abstract class Video implements IVideo {
private String name;
private String genre;
private float rating;
private String releaseDate;

    //constructor
    public Video(String name, String releaseDate, String genre, float rating) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
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

    @Override
    public String toString() {
        return "Title: " + getName() + ", released in: " + getReleaseDate() + "\nGenre: " + getGenre();
    }
}
