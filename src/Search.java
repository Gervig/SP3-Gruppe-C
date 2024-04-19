import java.util.ArrayList;
import java.util.Scanner;


public class Search {
    private String title;
    private String releaseDate;
    private String genre;
    private String rating;
    private ArrayList<Video> csr;
    FileIO reader = new FileIO();
    Scanner scan = new Scanner(System.in);

    private void seperator() {
        for (String line : reader.readFilmData("data/film.txt", 100)) {
            String[] parts = line.split(";");
            title = parts[0];
            releaseDate = parts[1];
            genre = parts[2];
            rating = parts[3];
        }
    }

    public ArrayList<String> searchGenre(ArrayList<String> filmDataList) {
        ArrayList<String> moviesWithGenre = new ArrayList<>();
        boolean validInput = false;
        int counter = 1;

        do {
        TextUI.displayMsg("Please enter the genre you want to search");
        String choice = scan.nextLine();
        seperator();

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
       if(!moviesWithGenre.isEmpty()) {
        TextUI.displayMsg("Here is all the movies with the genre " + "\""+choice+"\""+"\n");
        for (String movie : moviesWithGenre) {

            TextUI.displayMsg(counter+" "+movie);
            counter +=1;
        }
           validInput = true; // Set validInput to true to exit the Do {} loop
    } else {
           TextUI.displayMsg("No film or series was found within those search parameters, try another search");
           TextUI.displayMsg("Would you like to try another search? (yes/no)");
           String tryAgain = scan.nextLine().toLowerCase();
           if (!tryAgain.equals("yes")) {
               validInput = true; // Exit the loop if the user doesn't want to try again
           }
       }
    } while (!validInput);

    return moviesWithGenre;
}

   public ArrayList<String> searchName(ArrayList<String> filmDataList){
       ArrayList<String> moviesWithName = new ArrayList<>();
       boolean validInput = false;
       int counter = 1;

       do {
           TextUI.displayMsg("Please enter the name you want to search for");
           String choice = scan.nextLine();
           seperator();
            /*
           for (String filmData : filmDataList) {
               String[] parts = filmData.split(";\\s*");
               String[] names = parts[0].split(" \\s*"); // Splitting the genre string into individual names

               for (String name : names) {
                   if (name.equalsIgnoreCase(choice.trim())) {
                       // If any of the movie's names match the user's choice, add it to the list
                       moviesWithName.add(parts[0]);
                       break; // Once a match is found, no need to continue checking other names for this movie
                   }
               }
           }
             */
           for (String filmData : filmDataList) {
               String[] parts = filmData.split(";\\s*");
               String movieName = parts[0].toLowerCase(); // Convert movie name to lowercase for case-insensitive search

               if (movieName.contains(choice)) {
                   // If the movie name contains the user's choice (partial match), add it to the list
                   moviesWithName.add(parts[0]);
               }
           }
           if(!moviesWithName.isEmpty()) {
               TextUI.displayMsg("Here is all the movies that contains the word " + "\""+choice+"\""+"\n");
               for (String movie : moviesWithName) {

                   TextUI.displayMsg(counter+" "+movie);
                   counter +=1;
               }
               validInput = true; // Set validInput to true to exit the Do {} loop
           } else {
               TextUI.displayMsg("No film or series was found within those search parameters, try another search");
               TextUI.displayMsg("Would you like to try another search? (yes/no)");
               String tryAgain = scan.nextLine().toLowerCase();
               if (!tryAgain.equals("yes")) {
                   validInput = true; // Exit the loop if the user doesn't want to try again
               }
           }
       } while (!validInput);

       return moviesWithName;
       //todo add partial name search

   }
    public ArrayList<String> searchRating(ArrayList<String> filmDataList){
        ArrayList<String> moviesWithRating = new ArrayList<>();
        boolean validInput = false;
        int counter = 1;

        do {
            TextUI.displayMsg("Please enter the 2 ratings you want to search within");
            double lower = Double.parseDouble(scan.nextLine());
            double upper = Double.parseDouble(scan.nextLine());
            System.out.println(" ");

            seperator();

            for (String filmData : filmDataList) {
                String[] parts = filmData.split(";\\s*");
                double rating = Double.parseDouble(parts[3]);

                if (rating >= lower && rating <= upper) {
                    // If the movie's rating falls within the specified range, add it to the list
                    moviesWithRating.add(parts[0] + " (" + rating + ")");
                }
            }
            if(!moviesWithRating.isEmpty()) {
                TextUI.displayMsg("Here is all the movies within your rating range " + "\""+upper+"-"+lower+"\""+"\n");
                for (String movie : moviesWithRating) {
                    TextUI.displayMsg(counter+" "+movie);
                    counter +=1;
                }
                validInput = true; // Set validInput to true to exit the Do {} loop
            } else {
                TextUI.displayMsg("No film or series was found within those search parameters, try another search");
                TextUI.displayMsg("Would you like to try another search? (yes/no)");
                String tryAgain = scan.nextLine().toLowerCase();
                if (!tryAgain.equals("yes")) {
                    validInput = true; // Exit the loop if the user doesn't want to try again
                }
            }
        } while (!validInput);

        return moviesWithRating;

    }
   public ArrayList<String> searchReleaseDate(ArrayList<String> filmDataList){
       ArrayList<String> moviesWithReleaseDate = new ArrayList<>();
       boolean validInput = false;
       int counter = 1;

       do {
           TextUI.displayMsg("Please enter the 2 release dates you want to search within");
           int lower = Integer.parseInt(scan.nextLine());
           int upper = Integer.parseInt(scan.nextLine());
           System.out.println(" ");

           seperator();

           for (String filmData : filmDataList) {
               String[] parts = filmData.split(";\\s*");
               int releaseDate = Integer.parseInt(parts[1]);

               if (releaseDate >= lower && releaseDate <= upper) {
                   // If the movie's release Date falls within the specified range, add it to the list
                   moviesWithReleaseDate.add(parts[0] + " (" + releaseDate + ")");
               }
           }
           if(!moviesWithReleaseDate.isEmpty()) {
               TextUI.displayMsg("Here is all the movies within your release date range " + "\""+upper+"-"+lower+"\""+"\n");
               for (String movie : moviesWithReleaseDate) {
                   TextUI.displayMsg(counter+" "+movie);
                   counter +=1;
               }
               validInput = true; // Set validInput to true to exit the Do {} loop
           } else {
               TextUI.displayMsg("No film or series was found within the year "+lower+" and "+upper+ " please try a different range");
               TextUI.displayMsg("Would you like to try another search? (yes/no)");
               String tryAgain = scan.nextLine().toLowerCase();
               if (!tryAgain.equals("yes")) {
                   validInput = true; // Exit the loop if the user doesn't want to try again
               }
           }
       } while (!validInput);

       return moviesWithReleaseDate;

   }

}
