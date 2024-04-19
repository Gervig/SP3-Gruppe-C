import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Series> seenSeries;
    private ArrayList<Film> seenFilm;
    private ArrayList<Video> savedVideo;
    private ArrayList<Video> seenVideo;

    public User(String name, ArrayList<Video> savedVideo, ArrayList<Video> seenVideo) {
        this.name = name;
        this.savedVideo = savedVideo;
        this.seenVideo = seenVideo;
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
