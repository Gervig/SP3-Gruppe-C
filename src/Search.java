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

   public void searchName(){
      //Repeat searchGenre() but for movie names

       //return movie that contains this name

   }
   public void searchRating(){
      //Repeat searchGenre() but for rating (Potentially a range)


   }
   public void searchReleaseDate(){
      //Repeat searchGenre() but for release date (Potentially a range)


   }

}
