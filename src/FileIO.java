import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    private String userHistoryPath = "data\\UserData\\UserHistory\\";
    private String userSavedPath = "data\\UserData\\UserSaved\\";

    public ArrayList<String> readUsers() {
        String path = "data\\UserData\\Users.txt";
        ArrayList<String> users = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                users.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return users;
    }

    //reads filmdata
    public ArrayList<String> readFilmData(String path, int length) {
        ArrayList<String> filmdata = new ArrayList<>();
        File file = new File(path);

        try {
            Scanner scan = new Scanner(file);

            for (int i = 0; i < length; i++) {
                String line = scan.nextLine();
                filmdata.add(i, line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

        return filmdata;
    }


    //reads seriesdata
    public ArrayList<String> readSeriesData(String path, int length) {
        ArrayList<String> seriesdata = new ArrayList<>();
        File file = new File(path);

        try {
            Scanner scan = new Scanner(file);

            for (int i = 0; i < length; i++) {
                String line = scan.nextLine();
                seriesdata.add(i, line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            ;
        }

        return seriesdata;
    }

    //Saves data
    public static void saveData(ArrayList<User> users, String path) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write("Title, release date, genre");
            for (User u : users) {
                writer.write(u + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> readUserData(String path) {
        ArrayList<String> userData = new ArrayList<>();
        File file = new File(path);

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                userData.add(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return userData;
    }

    public void createUserFiles(String username, String password) {
        File fileH = new File(userHistoryPath + username + ".txt");
        File fileS = new File(userSavedPath + username + ".txt");
        try {
            FileWriter writer = new FileWriter("data\\UserData\\Users.txt", true);
            writer.write(username + ";" + password + ";" + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
