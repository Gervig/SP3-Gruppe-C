import java.util.ArrayList;

public class Main {
    static FileIO reader = new FileIO();




    public static void main(String[] args) {
        Stream stream = new Stream("toString'n'Chill");
//        Search sear = new Search();
//        //sear.searchGenre(reader.readFilmData("data/film.txt",100));
//        sear.searchName(reader.readFilmData("data/film.txt",100));
        ArrayList<Video> caspersList = new ArrayList<>();
        User user = new User("Casper", "123");

        stream.runDialog();

    }
}
