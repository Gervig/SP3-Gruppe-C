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

  /*  public void runDialog() {
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
                    this.quitProgram();

                    break;
            }
        }
    }

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
        for (User user : users){
            if (user.getName().equalsIgnoreCase(newUsername)){
            System.out.println("Username already in use, please choose a different username:");
            return null;
            }else{
            String newPassword = ui.promptText("Choose a password:");
            io.createUserFiles(newUsername, newPassword);
            User newUser = new User(newUsername, newPassword);
            users.add(newUser);
            return newUser;
        }
    }

    public User loginUser() {

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

    public void runStartMenu() {
        int choice = 0;
        switch (choice) {
            case 1:
                //Search for movie / Series
                Search search = new Search();

                break;
            case 2:
                //View saved movies / series
                currentUser.viewSaved();

                break;
            case 3:
                //View watch historie
                currentUser.viewSaved();

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

    private void quitProgram() {
        io.saveData(this.currentUser, );
    }

 */
}
