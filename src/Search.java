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
    private void seperator(){
       for (String line : reader.readFilmData("data/film.txt",100)){
          String[] parts = line.split(";");
           title = parts[0];
           releaseDate = parts[1];
           genre = parts[2];
           rating = parts[3];
       }
    }
   public void searchGenre(){
      TextUI.displayMsg("Please enter the genre you want to search");
      String choice = scan.nextLine();
      seperator();
      if(genre.contains(choice)){
         TextUI.displayMsg("Now playing: "+choice);
      } else {
         TextUI.displayMsg("No film or series was found within those search parameters, try another search");
      }

      //Use scanner to gain user input which will be the search
      //Use string given by to search a file
      //Pull data from fileIO, data comes as CSV
      //Separate the data from CSV to only gain data about genre
      //Return full list of movies that correlates with the searched genre


   }
   public void searchName(){
      //Repeat searchGenre() but for movie names

   }
   public void searchRating(){
      //Repeat searchGenre() but for rating (Potentially a range)


   }
   public void searchReleaseDate(){
      //Repeat searchGenre() but for release date (Potentially a range)


   }

}
