import java.util.ArrayList;
public abstract class Video {
private String name;
private String genre;
private float rating;
private boolean hasSeen;
private ArrayList<Integer> releaseDate;

//constructor
public Video(String name, String genre, float rating){

}

//methods
public int getReleaseDate(){
    return releaseDate.get(0);
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

}
