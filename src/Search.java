import java.util.ArrayList;
import java.util.Scanner;


public class Search {
    private ArrayList<Video> csr;
    Scanner scan = new Scanner(System.in);
   public void searchGenre(){
      TextUI.displayMsg("Please enter the genre you want to search");
      String choice = scan.nextLine();

      //Use scanner to gain user input which will be the search hejsa
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
