import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    //
    // Not adapted to our program, needs review to make it work for us properly (Maybe?)
    //
    private final Scanner scan = new Scanner(System.in);

    public void displayList(ArrayList<String> list, String msg){
        System.out.println(msg);
        for (String option : list) {
            System.out.println(option);
        }
    }
    public String promptText(String msg){
        displayMsg(msg);
        return scan.nextLine();
    }
    public boolean promptBinary(String msg, String accept, String reject ){
        boolean output;
        // todo: check at der tastes enten y eller n
        // lav rekursivt kald hvis det er noget tredje

        String input = promptText(msg);
        if(input.equalsIgnoreCase(accept)){
            return true;
        }else if(input.equalsIgnoreCase(reject)){
            return false;
        }else{
            return promptBinary(msg,accept, reject);
        }

    }
    public int promptNumeric(String msg){
        String input = promptText(msg);         //Give brugere et sted at placere sit svar og vente pÃ¥ svaret
        return Integer.parseInt(input);
    }
    public int promptChoice(ArrayList<String> optionslist, String msg){//["Gin&Tonic", "Beer","Vine" ]
        displayMsg(msg);
        displayList(optionslist, "");
        //  return optionslist.get(input-1);
        return promptNumeric("");

    }

    public void displayMsg(String msg){
        System.out.println("\n***************");
        System.out.println(msg);
        System.out.println("***************\n");
    }

}