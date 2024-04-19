import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Series> seenSeries;
    private ArrayList<Film> seenFilm;
    private ArrayList<Video> savedVideo;

    public User (String name){
    this.name = name;
    }

    //methods

public boolean watchVideo(Video video){
    if (video instanceof Series && video.playVideo(video)){
        seenSeries.add((Series) video);
    }else if(video instanceof Film){
        seenFilm.add((Film) video);
    }
        return true;
}
public boolean addToSaved(){
        return true;
    }
}
