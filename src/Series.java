class Series extends Video{

    private String season;
    private int episode;

    public Series(String name, String genre, float rating, String releaseDate, String season) {
        super(name, genre, rating, releaseDate);
        this.season = season;
    }


    public String getSeason(){
        return this.season;
    }

    public int getEpisode(){
        return this.episode;
    }


    @Override
    public String toString() {
        return super.toString() + "\nSeason: " + getSeason() + "\nEpisode: " + getEpisode();
    }

}
