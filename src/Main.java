public class Main {
    public Stream stream = new Stream();
    static FileIO reader = new FileIO();

    public static void main(String[] args) {
        Search sear = new Search();
        //sear.searchGenre(reader.readFilmData("data/film.txt",100));
        sear.searchRating(reader.readFilmData("data/film.txt",100));

    }
}