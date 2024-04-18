import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Series> seenSeries;
    private ArrayList<Film> seenFilm;
    private ArrayList<Video> savedVideo;

    public User (String name){
    this.name = name;
    }


public boolean watchVideo(){
    return true;
}
public boolean addToSaved(){
    return true;
}
}
