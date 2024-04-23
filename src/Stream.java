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

    //Rikke
    ArrayList<String> listOfMovies = new ArrayList<>();
    String selectedMovie = "movie";

    protected Film film;

    protected Series series;

    public Stream(String name) {
        this.name = name;
        this.ui = new TextUI();
        this.io = new FileIO();

        listOfActions.add("1) Login");
        listOfActions.add("2) Sign up");
        listOfActions.add("3) Quit");
    }

    public void setup() {
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

        ArrayList<String> seriesData = new ArrayList<>(io.readVideoData(seriesDataPath, 100));
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
                    //this.quitProgram();
                    break;
            }
        }
    }

    public User createUser() {
        String newUsername = ui.promptText("Choose a username:");

        // Check if the username already exists
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(newUsername)) {
                System.out.println("Username already in use, please choose a different username:");
                //todo burde return til login eller createUser
                return createUser();
            } else {
                String newPassword = ui.promptText("Choose a password:");
                io.createUserFiles(newUsername, newPassword);
                User newUser = new User(newUsername, newPassword);
                users.add(newUser);
                return newUser;
            }
        }
        return null;
    }


    public User loginUser() {
        String username = ui.promptText("Please write username:");

        // Check if the user exists
        for (User u : users) {
            if (u.getName().equals(username)) {
                String inputPassword = ui.promptText("Please write password:");
                if (u.getPassword().equals(inputPassword)) {
                    System.out.println("Logged in");
                    //todo read UserHistory & Saved and add to lists
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

    //Makes our string objects to film objects - needed in watchedFilm()
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
        listOfMovies.add("1) Play now");
        listOfMovies.add("2) Add to save");
        listOfMovies.add("3) Go back to the search list");
        int choice = 0;
        while (choice != listOfMenu.size()) {// the quit action is the last action
            choice = ui.promptChoice(listOfMenu, "Choose action:");
            switch (choice) {
                case 1:
                    searchForMovie();
                    break;
                case 2:
                    //View saved videos
                    ui.displayMsg("Saved film" + currentUser.getSavedFilm());
                    //the list is not yet added, there is a to do for that.
                    //todo: make a method for choosing a movie and play those, like in the search method
                    break;
                case 3:
                    //View watch history
                    currentUser.getSeenFilm(); //Gets seen films
                    currentUser.getSeenSeries(); //Gets seen series
                    //the list is not yet added, there is a to do for that.
                    //todo: make a method for choosing a movie and play those, like in the search method
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

    public void searchForMovie() {
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
                ArrayList<String> moviesList = search.getMoviesWithGenre();
                String[] movies = moviesList.toArray(new String[0]);
                boolean movieFound = false;

                // Only prompt for movie choice if user didn't go back to menu
                int movieChoice = ui.promptNumeric("Choose a Film from the list above");
                if (movieChoice >= 1 && movieChoice <= search.getMoviesWithGenre().size()) {
                    selectedMovie = search.getMoviesWithGenre().get(movieChoice - 1);
                    playMenu();
                } else {
                    TextUI.displayMsg("Invalid movie choice.");
                }

                break;
            case 2:
                //Search for title
                search.searchName(io.readFilmData(filmDataPath, 100));
                ArrayList<String> moviesList2 = search.getMoviesWithName();
                String[] movies2 = moviesList2.toArray(new String[0]);
                boolean movieFound2 = false;

                // Only prompt for movie choice if user didn't go back to menu
                int movieChoice2 = ui.promptNumeric("Choose a Film from the list above");
                if (movieChoice2 >= 1 && movieChoice2 <= search.getMoviesWithName().size()) {
                    selectedMovie = search.getMoviesWithName().get(movieChoice2 - 1);
                    playMenu();
                } else {
                    TextUI.displayMsg("Invalid movie choice.");
                }
                break;
            case 3:
                //Search for Rating
                search.searchRating(io.readFilmData(filmDataPath, 100));
                ArrayList<String> moviesList3 = search.getMoviesWithName();
                String[] movies3 = moviesList3.toArray(new String[0]);
                boolean movieFound3 = false;

                // Only prompt for movie choice if user didn't go back to menu
                int movieChoice3 = ui.promptNumeric("Choose a Film from the list above");
                if (movieChoice3 >= 1 && movieChoice3 <= search.getMoviesWithRating().size()) {
                    selectedMovie = search.getMoviesWithRating().get(movieChoice3 - 1);
                    playMenu();
                } else {
                    TextUI.displayMsg("Invalid movie choice.");
                }
                break;
            case 4:
                //Search for Releasedate
                search.searchReleaseDate(io.readFilmData(filmDataPath, 100));
                ArrayList<String> moviesList4 = search.getMoviesWithReleaseDate();
                String[] movies4 = moviesList4.toArray(new String[0]);
                boolean movieFound4 = false;

                // Only prompt for movie choice if user didn't go back to menu
                int movieChoice4 = ui.promptNumeric("Choose a Film from the list above");
                if (movieChoice4 >= 1 && movieChoice4 <= search.getMoviesWithReleaseDate().size()) {
                    selectedMovie = search.getMoviesWithReleaseDate().get(movieChoice4 - 1);
                    playMenu();
                } else {
                    TextUI.displayMsg("Invalid movie choice.");
                }
                break;
            case 5:
                runStartMenu();
                break;
        }
    }

    public void playMenu(){
        int choiceForMovie = 0;
        while (choiceForMovie != listOfMovies.size()) { // the quit action is the last action
            choiceForMovie = ui.promptChoice(listOfMovies, "Choose action:");
            switch (choiceForMovie) {
                case 1:
                    ui.displayMsg("Now playing: " + selectedMovie);
                    currentUser.watchedFilm(stringToFilm(selectedMovie));
                    ui.displayMsg("The movie has ended, and you are now being redirected to the Start menu");
                    //todo: Let it stay for a bit until userinput is registered
                    runStartMenu();
                    break;
                case 2:
                    currentUser.addToSaved(stringToFilm(selectedMovie));
                    TextUI.displayMsg("The movie: " + selectedMovie +" has been added to your save list");
                    ui.displayMsg("You have been redirected to the Start menu");
                    runStartMenu();
                    break;
                case 3:
                    //goes back to search menu
                    searchForMovie();
                    break;
            }
        }
    }
}