class Series extends Video{

    private int season;
    private int episode;

    public Series(String name, String genre, float rating, String releaseDate, int season) {
        super(name, genre, rating, releaseDate);
        this.season = season;
    }


    public int getSeason(){
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
