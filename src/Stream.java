import java.util.ArrayList;

public class Stream {
    String name;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Film> filmList = new ArrayList<>();
    ArrayList<Series> seriesList = new ArrayList<>();
    TextUI ui;
    FileIO io;
    ArrayList<String> listOfActions = new ArrayList<>();
    String seriesDataPath = "data\\series.txt";
    String filmDataPath = "data\\film.txt";
    private User currentUser = new User("Lukas", "lukas");
    ArrayList<String> selectedVideos = new ArrayList<>();

    protected Film film;

    protected Series series;

    public Stream(String name) {
        this.name = name;
        this.ui = new TextUI();
        this.io = new FileIO();

        listOfActions.add("1) Login");
        listOfActions.add("2) Sign up");
        listOfActions.add("3) Quit");

        //this.setup();
    }

    private void setup() {
        ArrayList<String> filmData = new ArrayList<>(io.readFilmData(filmDataPath, 100));
        for (String s : filmData) {
            String[] values = s.split(";");
            String name = values[0];
            String releaseDate = values[1];
            String genre = values[2];
            float rating = Float.parseFloat(values[3].trim());
            Film film = new Film(name, releaseDate, genre, rating);
            filmList.add(film);
        }

        ArrayList<String> seriesData = new ArrayList<>(io.readSeriesData(seriesDataPath, 100));
        for (String s : seriesData) {
            String[] values = s.split(";");
            String name = values[0];
            String releaseDate = values[1];
            String genre = values[2];
            float rating = Float.parseFloat(values[3].trim());
            String episode = values[4];
            Series series = new Series(name, releaseDate, genre, rating, episode);
            seriesList.add(series);
        }

        ArrayList<String> userList = io.readUsers();
        for (String s : userList) {
            String[] values = s.split(";");
            String name = values[0];
            String password = values[1];
            User user = new User(name, password);
            users.add(user);
        }

    }

    public void runDialog() {
        ui.displayMsg("Welcome to " + this.name);
        int action = 0;
        while (action != listOfActions.size()) {// the quit action is the last action
            action = ui.promptChoice(listOfActions, "Choose action:");

            switch (action) {
                case 1:
                    //Login to existing user
                    this.loginUser();
                    this.runStartMenu();

                    break;
                case 2:
                    //Sign up new user
                    this.createUser();
                    this.runStartMenu();
                    break;
                case 3:
                    //quit program
                    //        this.quitProgram();

                    break;
            }
        }
    }

    public User createUser() {
        String newUsername = ui.promptText("Choose a username:");
        if (!getUserNames().equals(newUsername)) {
            String newPassword = ui.promptText("Choose a password:");
            User newUser = new User(newUsername, newPassword);
            users.add(newUser);
            io.createUserFiles(newUsername, newPassword);
            return newUser;
        } else {
            System.out.println("Username already in use, please choose a different username:");
            return createUser();
        }
    }

    public User loginUser() {
        String username = ui.promptText("Please write username:");

        // Check if the user exists
        for (User u : users) {
            if (u.getName().equals(username)) {
                String inputPassword = ui.promptText("Please write password:");
                if (u.getPassword().equals(inputPassword)) {
                    System.out.println("Logged in");
                    return u;
                } else {
                    System.out.println("Wrong password. Please try again:");
                    return loginUser();
                }
            }
        }

        System.out.println("User does not exist. Please try again:");
        return loginUser();
    }

    public Film stringToFilm(String str) {
        for (Film f : filmList) {
            if (f.getName().equals(str)) {
                return f;
            }
        }
        return null;
    }

    public void runStartMenu() {
        ArrayList<String> listOfMenu = new ArrayList<>();
        listOfMenu.add("1) Search");
        listOfMenu.add("2) Saved");
        listOfMenu.add("3) History");
        listOfMenu.add("4) Logout");
        listOfMenu.add("5) Quit");
        ArrayList<String> listOfMovies = new ArrayList<>();
        listOfMovies.add("1) Play now");
        listOfMovies.add("2) Add to save");
        listOfMovies.add("3) Go back to the search list");
        int choice = 0;
        while (choice != listOfMenu.size()) {// the quit action is the last action
            choice = ui.promptChoice(listOfMenu, "Choose action:");
            switch (choice) {
                case 1:
                    //Search for movie - does not work for series
                    Search search = new Search();
                    ArrayList<String> listOfSearch = new ArrayList<>();
                    listOfSearch.add("1) Genre");
                    listOfSearch.add("2) Title");
                    listOfSearch.add("3) Rating");
                    listOfSearch.add("4) Releasedate");
                    listOfSearch.add("5) Go back to menu");
                    int searchChoice = ui.promptChoice(listOfSearch, "Choose a search option");
                    switch (searchChoice) {
                        case 1:
                            //Search for genre
                            TextUI.displayMsg("Type the genre you would like to search for");
                            search.searchGenre(io.readFilmData(filmDataPath, 100));

                            int choiceOfMovie = ui.promptNumeric("Choose a film from the list above");
                            ArrayList<String> moviesList = search.getMoviesWithGenre();
                            String[] movies = moviesList.toArray(new String[0]);
                            boolean movieFound = false;

                            // Only prompt for movie choice if user didn't go back to menu
                            int movieChoice = ui.promptNumeric("Choose a movie from the list above");
                            if (movieChoice >= 1 && movieChoice <= search.getMoviesWithGenre().size()) {
                                String selectedMovie = search.getMoviesWithGenre().get(movieChoice - 1);

                                int choiceForMovie = 0;
                                while (choiceForMovie != listOfMovies.size()) { // the quit action is the last action
                                    choiceForMovie = ui.promptChoice(listOfMovies, "Choose action:");
                                    switch (choiceForMovie) {
                                        case 1:
                                            ui.displayMsg("Now playing: " + selectedMovie);
//                                                selectedVideo(selectedMovie);

                                            currentUser.watchedFilm(stringToFilm(selectedMovie));
                                            System.out.println(currentUser.getSeenFilm());
//                                                Film addToWatched = new Film(selectedMovie);
//                                               currentUser.watchedVideo(addToWatched); //Add to seenFilm
                                            //Vi kan ikke få den til at samarbejde med watchedVideo

                                            break;
                                        case 2:
                                            //add to listOfSaved
                                            break;
                                        case 3:
                                            //go back
                                            break;
                                    }
                                }
                                //
                                //
                                // Code for playing movie or adding to the save list goes here.
                            } else {
                                TextUI.displayMsg("Invalid movie choice.");
                            }

                            break;
                        case 2:
                            //Search for title
                            search.searchName(io.readFilmData(filmDataPath, 100));
                            break;
                        case 3:
                            //Search for Rating
                            search.searchRating(io.readFilmData(filmDataPath, 100));
                            break;
                        case 4:
                            //Search for Releasedate
                            search.searchReleaseDate(io.readFilmData(filmDataPath, 100));
                            break;
                        case 5:
                            //Go back to menu
                            break;
                    }

                    break;
                case 2:
                    //View saved videos
                    currentUser.getSavedVideo();

                    break;
                case 3:
                    //View watch history
                    currentUser.getSeenFilm(); //Gets seen films
                    currentUser.getSeenSeries(); //Gets seen series
                    break;
                case 4:
                    //logout
                    currentUser = null;
                    this.runDialog();
                    break;
                case 5:
                    //quit
                    //   this.quitProgram();
                    break;
            }
        }
    }
/*
        private void quitProgram () {
            io.saveData(this.currentUser, );
        }*/

    public ArrayList<User> getUserNames() {
        ArrayList<String> namesOfUsers = new ArrayList<>();
        for (User u : users) {
            namesOfUsers.add(u.getName());
        }

        return users;
    }

    public ArrayList<Film> getFilmList() {
        return filmList;
    }

    public void setFilmList(ArrayList<Film> filmList) {
        this.filmList = filmList;
    }
}

