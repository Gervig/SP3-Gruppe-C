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
    private User currentUser = new User("Lukas","lukas");
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
        for (String s : userList){
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
            action = ui.promptChoice(listOfActions, "Choose action:");

            switch (action) {
                case 1:
                    //Login to existing user
                   // this.loginUser();
                    this.runStartMenu();

                    break;
                case 2:
                    //Sign up new user
                    //this.createUser();
                    this.runStartMenu();
                    break;
                case 3:
                    //quit program
            //        this.quitProgram();

                    break;
            }
        }
    }



    /*
        public User createUser() {
            String newUsername = ui.promptText("Choose a username:");
    //        if (!users.get(0).equalsIgnoreCase(newUsername)) {
    //            String newPassword = ui.promptText("Choose a password:");
    //            User newUser = new User(newUsername, newPassword);
    //            users.add(newUser);
                  currentUser = newUser;
    //            return newUser;
    //        } else {
    //            System.out.println("Username already in use, please choose a different username:");
    //            return null;
    //        }
            for (User user : users) {
                if (user.getName().equalsIgnoreCase(newUsername)) {
                    System.out.println("Username already in use, please choose a different username:");
                    return null;
                } else {
                    String newPassword = ui.promptText("Choose a password:");
                    io.createUserFiles(newUsername, newPassword);
                    User newUser = new User(newUsername, newPassword);
                    users.add(newUser);
                    return newUser;
                }
            }
        }*/
    /*
        public User loginUser () {

            boolean isloggedIn = false;
            String username = ui.promptText("Please write username:");
            // String password too
            while (!isloggedIn) {
                for (User u : users) {
                    if (u.getName().equals(username)) {
                        User user = u;

                        if (user.getPassword().equals(ui.promptText("Please write password:"))) {
                            System.out.println("logged in xd");
                            isloggedIn = true;
                            return user;
                        } else {
                            System.out.println("Wrong password please try agian:");
                            //Lav evt en back option
                        }

                    }
                }

            }

        }
*/
    public ArrayList<String> selectedVideo(String str){
        selectedVideos.add(str);
        return selectedVideos;
    }
    public ArrayList<String> getSelectedVideos() {
        return selectedVideos;
    }
    public Film stringToFilm(String str){
            for (Film f : filmList){
                if (f.getName().equals(str)){
                    return f;
                }
        }
        return null;
    }

        public void runStartMenu () {
            ArrayList<String> listOfMenu = new ArrayList<>();
            listOfMenu.add("1) Search");
            listOfMenu.add("2) Saved");
            listOfMenu.add("3) History");
            listOfMenu.add("4) Logout");
            listOfMenu.add("5) Quit");
            ArrayList<String> listOfMovies = new ArrayList<>();
            listOfMovies.add("1) Play now");
            listOfMovies.add("2) Add to save");
            listOfMovies.add("3) Go back");
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

                                // Only prompt for movie choice if user didn't go back to menu
                                int movieChoice = ui.promptNumeric("Choose a movie from the list above");
                                if (movieChoice >= 1 && movieChoice <= search.getMoviesWithGenre().size()) {
                                    String selectedMovie = search.getMoviesWithGenre().get(movieChoice - 1);

                                    int choiceForMovie = 0;
                                    while (choiceForMovie != listOfMovies.size()) { // the quit action is the last action
                                        choiceForMovie = ui.promptChoice(listOfMovies, "Choose action:");
                                        switch (choiceForMovie) {
                                            case 1:
                                                Film movieToWatch = stringToFilm(selectedMovie);
                                                currentUser.watchedFilm(movieToWatch);
                                                TextUI.displayMsg("Now playing: " + selectedMovie);
                                                break;
                                            case 2:
                                                Film movieToSave = stringToFilm(selectedMovie);
                                                currentUser.addToSaved(movieToSave);
                                                TextUI.displayMsg("The movie: " + selectedMovie+" has been added to your save list");
                                                break;
                                            case 3:
                                                //go back
                                                break;
                                        }
                                    }
                                } else{
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
        ArrayList<String>namesOfUsers = new ArrayList<>();
        for(User u: users){
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

