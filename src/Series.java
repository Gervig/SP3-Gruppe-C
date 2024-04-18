class Series extends Video{

    private int season;
    private int episode;

    public Series(String [] seriesData){
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
