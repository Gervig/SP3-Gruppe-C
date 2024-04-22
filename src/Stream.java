import java.util.ArrayList;

public class Stream {
    String name;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Film> filmsList = new ArrayList<>();
    ArrayList<Series> seriesList = new ArrayList<>();
    TextUI ui;
    FileIO io;
    ArrayList<String> listOfActions = new ArrayList<>();
    String seriesDataPath = "data\\series.txt";
    String filmDataPath = "data\\film.txt";
    private User currentUser;

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
            filmsList.add(film);
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
    }

    public void runDialog() {
        ui.displayMsg("Welcome to " + this.name);
        int action = 0;
        while (action != listOfActions.size()) {// the quit action is the last action
            action = ui.promptChoice(listOfActions, "Choose action:");

            switch (action) {
                case 1:
                    //Login to existing user
                //    this.loginUser();
                    this.runStartMenu();

                    break;
                case 2:
                    //Sign up new user
                //    this.createUser();
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
                        ui.promptChoice(listOfSearch, "Choose a search option");
                        switch (choice) {
                            case 1:
                                //Search for genre
                                search.searchGenre(io.readFilmData(filmDataPath, 100));

                                //When the result of the search comes we want to prompt a new choice for the user
                               // ui.promptChoice(search.getMoviesWithGenre(), "Choose a film");
                                ui.promptNumeric("Choose a film from the list above");
                                ui.displayMsg("You have chosen: " + search.getMoviesWithGenre());
                                int movieChoice = 0;
                                while (movieChoice != listOfMenu.size())
                                switch (movieChoice) {
                                    case 1:
                                        //Play the film now
                                        break;
                                    case 2:
                                        //Save for later
                                        break;
                                    case 3:
                                        //Go back to search list
                                        break;
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
    }
