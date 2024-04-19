import java.util.ArrayList;

public class Stream {
    String name;
    ArrayList<User> users = new ArrayList<>();
    TextUI ui;
    FileIO io;
    ArrayList<String> listOfActions = new ArrayList<>();
    String seriesDataPath = "data\\series.txt";
    String filmDataPath = "data\\film.txt";

    public void Stream(String name) {
        this.name = name;
        this.ui = new TextUI();
        this.io = new FileIO();

        listOfActions.add("1) Login");
        listOfActions.add("2) Sign up");
        listOfActions.add("3) Quit");

        this.setup();
    }

    private void setup() {
        //readFilmData(filmDataPath);
        //readSeriesData(seriesDataPath);

    }
/*
    public void runStartMenu() {
        ui.displayMsg("Welcome to " + this.name);
        int action = 0;
        while (action != listOfActions.size()) {// the quit action is the last action
           action = ui.promptChoice(listOfActions, "Choose action:");

            switch (action) {
                case 1:
                    //Login to existing user
                    this.loginUser();
                    this.runDialog(); //Skal kalde på UserMenu (findes i domænemodellen, findes IKKE i classeDiagrammet)

                    break;
                case 2:
                    //Sign up new user
                    this.createUser();
                    this.runDialog();
                    break;
                case 3:
                    //quit program
                    this.quitProgram();

                    break;
            }

            public void createUser(){
                String newUsername = promptText("Choose a username:");
                if(!users.getUsername().equals(newUsername)){
                    String newPassword = promptText("Choose a password:");
                    User newUser = new User(newUsername, newPassword);
                    users.add(newUser);
            }else{
                System.out.println("Username already in use, please choose a different username:");
            }

            }

            public void loginUser() {

                System.out.println("write username and password");

                boolean isloggedIn = false;
                String username = promptText("Please type your username:");
                // String password too
                while (!isloggedIn) {
                    for (User u : users) {
                        if (u.getusername.equals(username)) {
                            User user = u;

                            if (user.getPassword.equals(passwordPromptFromUserInput)) {
                                System.out.println("logged in xd");
                                isloggedIn = true;
                                return;
                            } else {
                                sout
                            }

                        }
                    }

                }
            }

            public void runDialog () {

            }

            private void quitProgram () {

            }
        }
    }

 */
}