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

    public User currentUser;

    protected Film film;

    protected Series series;

    public Stream(String name) {
        this.name = name;
        this.ui = new TextUI();
        this.io = new FileIO();

        listOfActions.add("1) Login");
        listOfActions.add("2) Sign up");
        listOfActions.add("3) Quit");

        this.setup();
    }

    private void setup() {
        ArrayList<String> filmData = new ArrayList<>(io.readFilmData(filmDataPath, 100));
        for (String s : filmData) {
            String[] values = s.split(";");
            String name = values[0];
            String year = values[1];
            String genre = values[2];
            float rating = Float.parseFloat(values[3].trim());
            Film film = new Film(name, year, genre, rating);
            filmList.add(film);
        }

        ArrayList<String> seriesData = new ArrayList<>(io.readSeriesData(seriesDataPath, 100));
        for (String s : seriesData) {
            String[] values = s.split(";");
            String name = values[0];
            String year = values[1];
            String genre = values[2];
            float rating = Float.parseFloat(values[3].trim());
            String episodes = values[4];
            Series series = new Series(name, year, genre, rating, episodes);
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
        ui.displayMsg("Welcome to " + this.name);
        int action = 0;
//        while (action != listOfActions.size()) {// the quit action is the last action
            action = ui.promptChoice(listOfActions, "Choose action:");

            switch (action) {
                case 1:
                    //Login to existing user
                    currentUser = this.loginUser();
                    this.runStartMenu();

                    break;
                case 2:
                    //Sign up new user
                    currentUser = this.createUser();
                    this.runStartMenu();
                    break;
                case 3:
                    //quit program
//                    this.quitProgram();

                    break;
            }
//        }
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
        // String password too
            for (User u : users) {
                if (u.getName().equals(username)) {
                    User user = u;
                    String input = ui.promptText("Please write password:");
                    if (user.getPassword().equals(input)) {
                        System.out.println("Login in success");
                        return user;
                    } else {
                        System.out.println("Wrong password please try again:");
                        return loginUser();
                    }

                }
            }
        return null;

        }

    public void runStartMenu () {
        int choice = 0;
        switch (choice) {
            case 1:
                //Search for movie / Series
                Search search = new Search();

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
//                this.quitProgram();
                break;
        }
    }

//    private void quitProgram() {
//        io.saveData(this.currentUser, );
//    }


    public ArrayList<User> getUserNames() {
    ArrayList<String>namesOfUsers = new ArrayList<>();
        for(User u: users){
        namesOfUsers.add(u.getName());
    }

        return users;
    }


}
