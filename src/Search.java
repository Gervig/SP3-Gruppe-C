import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Search {
    Scanner scan = new Scanner(System.in);
    private final ArrayList<String> moviesWithGenre = new ArrayList<>();
    private final ArrayList<String> moviesWithName = new ArrayList<>();
    private final ArrayList<String> moviesWithRating = new ArrayList<>();
    private final ArrayList<String> moviesWithReleaseDate = new ArrayList<>();
    private TextUI TextUI;


    public ArrayList<String> searchGenre(ArrayList<String> filmDataList) {

        boolean validInput = false;
        int counter = 1;

        do {
            String choice = scan.nextLine();

            for (String filmData : filmDataList) {
                String[] parts = filmData.split(";\\s*");
                String[] genres = parts[2].split(",\\s*"); // Splitting the genre string into individual genres

                for (String genre : genres) {
                    if (genre.equalsIgnoreCase(choice.trim())) {
                        // If any of the movie's genres match the user's choice, add it to the list
                        moviesWithGenre.add(parts[0]);
                        break; // Once a match is found, no need to continue checking other genres for this movie
                    }
                }
            }
            if (!moviesWithGenre.isEmpty()) {
                TextUI.displayMsg("Here is all the movies with the genre " + "\"" + choice + "\"" + "\n");
                for (String movie : moviesWithGenre) {

                    TextUI.displayMsg(counter + " " + movie);
                    counter += 1;
                }
                validInput = true; // Set validInput to true to exit the Do {} loop
            } else {
                TextUI.displayMsg("No film or series was found within those search parameters, try another search");
                TextUI.displayMsg("Would you like to try another search? (yes/no)");
                String tryAgain = scan.nextLine().toLowerCase();
                if (!tryAgain.equals("yes") || !tryAgain.equals("y")) {
                    validInput = true; // Exit the loop if the user doesn't want to try again
                }
            }
        } while (!validInput);
        return moviesWithGenre;
    }

    public ArrayList<String> searchName(ArrayList<String> filmDataList) {
        boolean validInput = false;
        int counter = 1;

        do {
            TextUI.displayMsg("Please enter the name you want to search for");
            String choice = scan.nextLine();
            choice = choice.toLowerCase();
            for (String filmData : filmDataList) {
                String[] parts = filmData.split(";\\s*");
                String movieName = parts[0].toLowerCase(); // Convert movie name to lowercase for case-insensitive search

                if (movieName.contains(choice)) {
                    // If the movie name contains the user's choice (partial match), add it to the list
                    moviesWithName.add(parts[0]);
                }
            }
            if (!moviesWithName.isEmpty()) {
                TextUI.displayMsg("Here is all the movies that contains the word " + "\"" + choice + "\"" + "\n");
                for (String movie : moviesWithName) {

                    TextUI.displayMsg(counter + " " + movie);
                    counter += 1;
                }
                validInput = true; // Set validInput to true to exit the Do {} loop
            } else {
                TextUI.displayMsg("No film or series was found within those search parameters, try another search");
                TextUI.displayMsg("Would you like to try another search? (yes/no)");
                String tryAgain = scan.nextLine().toLowerCase();
                if (!tryAgain.equals("yes") || !tryAgain.equals("y")) {
                    validInput = true; // Exit the loop if the user doesn't want to try again
                }
            }
        } while (!validInput);

        return moviesWithName;


    }

    public ArrayList<String> searchRating(ArrayList<String> filmDataList) {

        boolean validInput = false;
        int counter = 1;

        do {
            TextUI.displayMsg("Please enter the 2 ratings you want to search within");
            double lower = Double.parseDouble(scan.nextLine());
            double upper = Double.parseDouble(scan.nextLine());
            TextUI.displayMsg(" ");

            for (String filmData : filmDataList) {
                String[] parts = filmData.split(";\\s*");
                double rating = Double.parseDouble(parts[3]);

                if (rating >= lower && rating <= upper) {
                    // If the movie's rating falls within the specified range, add it to the list
                    moviesWithRating.add(parts[0] + " (" + rating + ")");
                }
            }
            if (!moviesWithRating.isEmpty()) {
                TextUI.displayMsg("Here is all the movies within your rating range " + "\"" + upper + "-" + lower + "\"" + "\n");
                for (String movie : moviesWithRating) {
                    TextUI.displayMsg(counter + " " + movie);
                    counter += 1;
                }
                validInput = true; // Set validInput to true to exit the Do {} loop
            } else {
                TextUI.displayMsg("No film or series was found within those search parameters, try another search");
                TextUI.displayMsg("Would you like to try another search? (yes/no)");
                String tryAgain = scan.nextLine().toLowerCase();
                if (!tryAgain.equals("yes") || !tryAgain.equals("y")) {
                    validInput = true; // Exit the loop if the user doesn't want to try again
                }
            }
        } while (!validInput);

        return moviesWithRating;

    }

    public ArrayList<String> searchReleaseDate(ArrayList<String> filmDataList) {

        boolean validInput = false;
        int counter = 1;

        do {
            TextUI.displayMsg("Please enter the 2 release dates you want to search within");
            int lower = Integer.parseInt(scan.nextLine());
            int upper = Integer.parseInt(scan.nextLine());
            TextUI.displayMsg(" ");
            for (String filmData : filmDataList) {
                String[] parts = filmData.split(";\\s*");
                int releaseDate = Integer.parseInt(parts[1]);

                if (releaseDate >= lower && releaseDate <= upper) {
                    // If the movie's release Date falls within the specified range, add it to the list
                    moviesWithReleaseDate.add(parts[0] + " (" + releaseDate + ")");
                }
            }
            if (!moviesWithReleaseDate.isEmpty()) {
                TextUI.displayMsg("Here is all the movies within your release date range " + "\"" + upper + "-" + lower + "\"" + "\n");
                for (String movie : moviesWithReleaseDate) {
                    TextUI.displayMsg(counter + " " + movie);
                    counter += 1;
                }
                validInput = true; // Set validInput to true to exit the Do {} loop
            } else {
                TextUI.displayMsg("No film or series was found within the year " + lower + " and " + upper + " please try a different range");
                TextUI.displayMsg("Would you like to try another search? (yes/no)");
                String tryAgain = scan.nextLine().toLowerCase();
                if (!tryAgain.equals("yes") || !tryAgain.equals("y")) {
                    validInput = true; // Exit the loop if the user doesn't want to try again
                }
            }
        } while (!validInput);

        return moviesWithReleaseDate;

    }

    //getters
    public ArrayList<String> getMoviesWithName() {
        return moviesWithName;
    }

    public ArrayList<String> getMoviesWithRating() {
        return moviesWithRating;
    }

    public ArrayList<String> getMoviesWithReleaseDate() {
        return moviesWithReleaseDate;
    }

    public ArrayList<String> getMoviesWithGenre() {
        return moviesWithGenre;
    }
}