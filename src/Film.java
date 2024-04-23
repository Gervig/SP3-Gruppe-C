class Film extends Video{

    public Film(String name, String releaseDate, String genre, float rating) {
        super(name, releaseDate, genre, rating);
    }
    public Film(String name){
        super(name);
    }

    public Film(String name, int rating) {
        super(name, rating);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
