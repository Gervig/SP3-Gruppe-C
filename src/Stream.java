import java.util.ArrayList;
import java.util.Scanner;

public class Stream {
    private String name;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Film> filmList = new ArrayList<>();
    private ArrayList<Series> seriesList = new ArrayList<>();
    TextUI TextUI;
    FileIO io;
    String seriesDataPath = "data\\series.txt";
    String filmDataPath = "data\\film.txt";
    private ArrayList<String> listOfActions = new ArrayList<>();
    private User currentUser;
    private ArrayList<String> listOfMovies = new ArrayList<>();
    private ArrayList<String> optionsForSaved = new ArrayList<>();
    private ArrayList<String> listOfHistory = new ArrayList<>();
    private ArrayList<String> listOfMenu = new ArrayList<>();
    private ArrayList<String> listOfSearch = new ArrayList<>();
    private ArrayList<String> searchResultList = new ArrayList<>();
    private String selectedMovie = "movie";

    public Stream(String name) {
        this.name = name;
        this.TextUI = new TextUI();
        this.io = new FileIO();
        listOfActions.add("1) Login");
        listOfActions.add("2) Sign up");
        listOfActions.add("3) Quit");
        listOfMovies.add("1) Play now");
        listOfMovies.add("2) Add to save");
        listOfMovies.add("3) Go back to the search list");
        optionsForSaved.add("1) Watch a saved video");
        optionsForSaved.add("2) View saved videos");
        optionsForSaved.add("3) Remove a video from saved");
        optionsForSaved.add("4) Go back");
        listOfHistory.add("1) View watched history");
        listOfHistory.add("2) Watch a video from history");
        listOfHistory.add("3) Go back");
        listOfMenu.add("1) Search");
        listOfMenu.add("2) Saved");
        listOfMenu.add("3) History");
        listOfMenu.add("4) Logout");
        listOfMenu.add("5) Quit");
        listOfSearch.add("1) Genre");
        listOfSearch.add("2) Title");
        listOfSearch.add("3) Rating");
        listOfSearch.add("4) Releasedate");
        listOfSearch.add("5) Go back to menu");
    }

    public void setup() {
        ArrayList<String> filmData = new ArrayList<>(io.readVideoData(filmDataPath, 100));
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
        TextUI.displayMsg("Welcome to " + this.name);
        int action = 0;
        while (action != listOfActions.size()) {// the quit action is the last action
            action = TextUI.promptChoice(listOfActions, "Choose action:");

            switch (action) {
                case 1://Login to existing user
                    currentUser = this.loginUser();
                    this.runStartMenu();
                    break;
                case 2:
                    //Sign up new user
                    currentUser = this.createUser();
                    this.runStartMenu();
                    break;
                case 3: //quit program
                    this.quitProgram();
                    break;
            }
        }
    }

    public User createUser() {
        String newUsername = TextUI.promptText("Choose a username:");

        String pattern = "^[a-zA-Z0-9æøåÆØÅ]+$";
        if(newUsername.matches(pattern)) {
            // Check if the username already exists
            for (User user : users) {
                if (user.getName().equalsIgnoreCase(newUsername)) {
                    TextUI.displayMsg("Username already in use, please choose a different username:");
                    return createUser();
                }
            }
            pattern = "^[a-zA-Z0-9æøåÆØÅ!#¤%&/()=?`@,.$£€]+$";
            String newPassword = TextUI.promptText("Choose a password:");
            if (newPassword.matches(pattern)) {
                io.createUserFiles(newUsername, newPassword);
                User newUser = new User(newUsername, newPassword);
                users.add(newUser);
                return newUser;
            } else {
                TextUI.displayMsg("Please use only numbers and letters for your password");
                return createUser();
            }
            } else {
                TextUI.displayMsg("Please use only numbers and letters for your username");
                return createUser();
            }
        }




    public User loginUser() {
        String username = TextUI.promptText("Please write username:");
        // Check if the user exists
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(username)) {
                String inputPassword = TextUI.promptText("Please write password:");
                if (u.getPassword().equals(inputPassword)) {
                    TextUI.displayMsg("Logged in");
                    ArrayList<String> userSaved = io.readVideoData(io.getUserSavedPath(), username);
                    ArrayList<String> userHistory = io.readVideoData(io.getUserHistoryPath(), username);
                    for (String s : userSaved) {
                        String[] values = s.split(";");
                        String name = values[0];
                        u.getSavedFilm().add(stringToFilm(name));
                    }
                    for (String s : userHistory) {
                        String[] values = s.split(";");
                        String name = values[0];
                        u.getSeenFilm().add(stringToFilm(name));
                    }
                    return u;
                } else {
                    TextUI.displayMsg("Wrong password. Please try again:");
                    return loginUser();
                }
            }
        }
        TextUI.displayMsg("User does not exist. Please try again:");
        return loginUser();
    }

    //Makes our string objects to film objects - needed in watchedFilm()
    public Film stringToFilm(String str) {
        for (Film f : filmList) {
            if (f.getName().equalsIgnoreCase(str)) {
                return f;
            }
        }
        return null;
    }

    public void runStartMenu() {
        int choice = 0;
        while (choice != listOfMenu.size()) {// the quit action is the last action
            choice = TextUI.promptChoice(listOfMenu, "Choose action:");
            switch (choice) {
                case 1: // search for videos
                    searchForMovie();
                    break;
                case 2: //View saved videos
                    if (currentUser.getSavedFilm().isEmpty()) {
                      TextUI.displayMsg("You have no saved films in your list");
                   } else {
                        int index1 = 1;
                        for (Film savedFilm : currentUser.getSavedFilm()) {
                            TextUI.displayMsg(index1 + ") " + savedFilm.getName()); // Using getName() method to print the title of the film
                            index1++;
                        }
                        int optionsChoice = TextUI.promptChoice(optionsForSaved, "Choose an option");
                        switch (optionsChoice) {
                            case 1:
                                if (currentUser.getSavedFilm().isEmpty()) {
                                    TextUI.displayMsg("You have no saved films in your list");
                                } else {
                                    ArrayList<String> savedVideoOptions = new ArrayList<>();
                                    int index = 1;
                                    for (Film savedFilm : currentUser.getSavedFilm()) {
                                        savedVideoOptions.add(index + ") " + savedFilm.getName());
                                        index++;
                                    }
                                    int videoChoice = TextUI.promptChoice(savedVideoOptions, "Choose a saved video to watch:");
                                    Film selectedVideo = currentUser.getSavedFilm().get(videoChoice - 1); // Adjusting for 0-based index
                                    watchingNow();
                                }
                                break;
                            case 2:
                                if (currentUser.getSavedFilm().isEmpty()) {
                                    TextUI.displayMsg("You have no saved films in your list");
                                } else {
                                    TextUI.displayMsg("Size of saved films: " + currentUser.getSavedFilm().size());
                                    TextUI.displayMsg("Saved films:");
                                    int index2 = 1;
                                    for (Film savedFilm : currentUser.getSavedFilm()) {
                                        TextUI.displayMsg(index2 + ") " + savedFilm.getName()); // Using getName() method to print the title of the film
                                        index2++;
                                    }
                                }
                                break;
                            case 3: //remove film from Saved videos
                                ArrayList<String> savedVideoOptions = new ArrayList<>();
                                int index3 = 1;
                                for (Film savedFilm : currentUser.getSavedFilm()) {
                                    savedVideoOptions.add(index3 + ") " + savedFilm.getName());
                                    index3++;
                                }
                                int videoChoice = TextUI.promptChoice(savedVideoOptions, "Choose a saved video to remove:");
                                Film selectedMovie = currentUser.getSavedFilm().get(videoChoice - 1); // Adjusting for 0-based index
                                currentUser.removeFromSaved(selectedMovie);
                                io.deleteLineFromFile(io.getUserSavedPath()+currentUser.getName()+".txt", selectedMovie.getName());
                                TextUI.displayMsg(selectedMovie.getName() + " has been removed from your saved list");
                                break;
                            case 4: //Go back
                                runStartMenu();
                                break;
                        }
                    }
                    break;
                case 3: //View watch history
                    int historyChoice = 0;
                    while (historyChoice != listOfMovies.size()) {
                        historyChoice = TextUI.promptChoice(listOfHistory, "Choose action:");
                        switch (historyChoice) {
                            case 1: //View history
                                if (currentUser.getSeenFilm().isEmpty()) {
                                    TextUI.displayMsg("You haven't watched anything yet");
                                } else {
                                    TextUI.displayMsg("Watch history:");
                                    int index1 = 1;
                                    for (Film seenFilm : currentUser.getSeenFilm()) {
                                        TextUI.displayMsg(index1 + ") " + seenFilm.getName()); // Using getName() method to print the title of the film
                                        index1++;
                                    }
                                }
                                break;
                            case 2: //Choose a movie to watch from history
                                if (currentUser.getSeenFilm().isEmpty()) {
                                    TextUI.displayMsg("You have no watch history");
                                } else {
                                    ArrayList<String> seenVideoOptions = new ArrayList<>();
                                    int index = 1;
                                    for (Film seenFilm : currentUser.getSeenFilm()) {
                                        seenVideoOptions.add(index + ") " + seenFilm.getName());
                                        index++;
                                    }
                                    int videoChoice = TextUI.promptChoice(seenVideoOptions, "Choose a video from history to watch:");
                                    Film selectedVideo = currentUser.getSeenFilm().get(videoChoice - 1); // Adjusting for 0-based index
                                    watchingNow();
                                }
                            case 3: //Go back
                                runStartMenu();
                        }
                    }
                    break;
                case 4:
                    //logout
                    currentUser = null;
                    this.runDialog();
                    break;
                case 5:
                    //quit
                    this.quitProgram();
                    break;
            }
        }
    }

    public void searchForMovie() { //Search for movies - does not work for series
        Search search = new Search();
        int searchChoice = TextUI.promptChoice(listOfSearch, "Choose a search option");
        switch (searchChoice) {
            case 1: //Search for genre
                TextUI.displayMsg("Type out the genre you would like to search for");
                TextUI.displayMsg("Action, Adventure, Biografi, Crime, Drama, Family, Fantasy, Film-Noir, History, Horror, Mystery, Romance, Sci-fi, Sport, Thriller, War");
                search.searchGenre(io.readVideoData(filmDataPath, 100));
                ArrayList<String> moviesList = search.getMoviesWithGenre();
                String[] movies = moviesList.toArray(new String[0]);
                boolean movieFound = false;
                // Only prompt for movie choice if user didn't go back to menu
                int movieChoice = TextUI.promptNumeric("Choose a Film from the list above");
                if (movieChoice >= 1 && movieChoice <= search.getMoviesWithGenre().size()) {
                    selectedMovie = search.getMoviesWithGenre().get(movieChoice - 1);
                    playMenu();
                } else {
                    TextUI.displayMsg("Invalid movie choice.");
                //todo display all unique genres in filmList
                TextUI.displayMsg("Type the genre you would like to search for");
                searchResultList = search.searchGenre(io.readVideoData(filmDataPath, 100));
                if(searchResultList.isEmpty()){
                    runStartMenu();
                } else {
                    // Only prompt for movie choice if user didn't go back to menu
                    movieChoice = TextUI.promptNumeric("Choose a Film from the list above");
                    if (movieChoice >= 1 && movieChoice <= search.getMoviesWithGenre().size()) {
                        selectedMovie = search.getMoviesWithGenre().get(movieChoice - 1);
                        playMenu();
                    } else {
                        TextUI.displayMsg("Invalid movie choice.");
                    }
                }
                }
                break;
            case 2://Search for title
                searchResultList = search.searchName(io.readVideoData(filmDataPath, 100));
                if(searchResultList.isEmpty()){
                    runStartMenu();
                } else {
                    // Only prompt for movie choice if user didn't go back to menu
                    int movieChoice2 = TextUI.promptNumeric("Choose a Film from the list above");
                    if (movieChoice2 >= 1 && movieChoice2 <= search.getMoviesWithName().size()) {
                        selectedMovie = search.getMoviesWithName().get(movieChoice2 - 1);
                        playMenu();
                    } else {
                        TextUI.displayMsg("Invalid movie choice.");
                    }
                }
                break;
            case 3: //Search for Rating
                searchResultList = search.searchRating(io.readVideoData(filmDataPath, 100));
                if(searchResultList.isEmpty()){
                    runStartMenu();
                } else {
                    // Only prompt for movie choice if user didn't go back to menu
                    int movieChoice3 = TextUI.promptNumeric("Choose a Film from the list above");
                    if (movieChoice3 >= 1 && movieChoice3 <= search.getMoviesWithRating().size()) {
                        selectedMovie = search.getMoviesWithRating().get(movieChoice3 - 1);
                        playMenu();
                    } else {
                        TextUI.displayMsg("Invalid movie choice.");
                    }
                }
                break;
            case 4: //Search for Releasedate
                searchResultList = search.searchReleaseDate(io.readVideoData(filmDataPath, 100));
                if(searchResultList.isEmpty()) {
                    runStartMenu();
                } else {
                    // Only prompt for movie choice if user didn't go back to menu
                    int movieChoice4 = TextUI.promptNumeric("Choose a Film from the list above");
                    if (movieChoice4 >= 1 && movieChoice4 <= search.getMoviesWithReleaseDate().size()) {
                        selectedMovie = search.getMoviesWithReleaseDate().get(movieChoice4 - 1);
                        playMenu();
                    } else {
                        TextUI.displayMsg("Invalid movie choice.");
                    }
                }
                break;
            case 5:
                runStartMenu();
                break;
        }
    }

    public void playMenu() {
        int choiceForMovie = 0;
        while (choiceForMovie != listOfMovies.size()) { // the quit action is the last action
            choiceForMovie = TextUI.promptChoice(listOfMovies, "Choose action:");
            switch (choiceForMovie) {
                case 1:
                    watchingNow();
                    break;
                case 2:
                    currentUser.addToSaved(stringToFilm(selectedMovie));
                    TextUI.displayMsg("The movie: " + selectedMovie + " has been added to your save list");
                    TextUI.displayMsg("You have been redirected to the Start menu");
                    io.saveVideoData(stringToFilm(selectedMovie), io.getUserSavedPath(), currentUser.getName());
                    runStartMenu();
                    break;
                case 3: //Goes back to search menu
                    searchForMovie();
                    break;
            }
        }
    }

    public void quitProgram() {
        System.exit(0);
    }

    public void watchingNow() {
        Scanner scan = new Scanner(System.in);
        TextUI.displayMsg("Now playing: " + selectedMovie);
        TextUI.displayMsg("Type \"" + "stop" + "\" if you would like to stop watching.");
        currentUser.watchedFilm(stringToFilm(selectedMovie));
        io.saveVideoData(stringToFilm(selectedMovie), io.getUserHistoryPath(), currentUser.getName());
        boolean validInput = false;
        do {
            String stopOrNot = scan.nextLine().toLowerCase();
            if (stopOrNot.equals("stop")) {
                TextUI.displayMsg("The movie has ended, and you are now being redirected to the Start menu");
                validInput = true; // Exit the loop if the user doesn't want to try again
                runStartMenu();
            } else {
                TextUI.displayMsg("Type \"" + "stop" + "\" if you would like to stop watching.");
            }
        } while (!validInput);
    }
    public ArrayList<User> getUserNames() {
        ArrayList<String> namesOfUsers = new ArrayList<>();
        for (User u : users) {
            namesOfUsers.add(u.getName());
        }
        return users;
    }

    public void setFilmList(ArrayList<Film> filmList) {
        this.filmList = filmList;
    }
}