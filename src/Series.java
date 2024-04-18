class Series extends Video{

    private int season;
    private int episode;

    public Series(String name, String genre, float rating){
    super(name, genre, rating);
    }


    public int getSeason(){
        return 0;
    }

    public int getEpisode(){
        return 0;
    }


    @Override
    public String toString() {
        return super.toString() + "\nSeason: " + getSeason() + getEpisode();
    }

}
