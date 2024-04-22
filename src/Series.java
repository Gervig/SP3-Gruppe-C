class Series extends Video{

    private String season;
    private String episode;

    public Series(String name, String releaseDate, String genre, float rating, String episode) {
        super(name, releaseDate, genre, rating);
        this.episode = episode;
    }


    public String getSeason(){
        return this.season;
    }

    public String getEpisode(){
        return this.episode;
    }


    @Override
    public String toString() {
        return super.toString() + "\nSeason: " + getSeason() + "\nEpisode: " + getEpisode();
    }

}
