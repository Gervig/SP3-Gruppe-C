public abstract class Video {
private String name;
private String genre;
private float rating;
private boolean hasSeen;
private ArrayList<int> releaseDate;

//constructor
public Video(String name, String genre, float rating){

}

//methods
public int getReleaseDate(){
    return releaseDate;
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
