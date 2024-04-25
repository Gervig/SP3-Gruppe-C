import java.io.*;
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

    public ArrayList<String> readVideoData(String path, int length) {
        ArrayList<String> videoData = new ArrayList<>();
        File file = new File(path);

        try {
            Scanner scan = new Scanner(file);

            for (int i = 0; i < length; i++) {
                String line = scan.nextLine();
                videoData.add(i, line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

        return videoData;
    }
    public ArrayList<String> readVideoData(String path, String username) {
        ArrayList<String> videoData = new ArrayList<>();
        path += "\\" + username + ".txt";
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);

            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                videoData.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

        return videoData;
    }

    //Saves data for users, should maybe be refactored to "saveUserData"

    public static void saveData(ArrayList<User> users, String path) {
        try {
            FileWriter writer = new FileWriter(path);
            for (User u : users) {
                writer.write(u + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Læs video objekter i det givne array, og skriv det ind i filen med username navn, i directoryiet path
    //Skriver lige nu ikke season/episodes ned

    public static void saveVideoData(Video v, String path, String username) {
        path += "\\" + username + ".txt";
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(v.getName() + ";" + v.getReleaseDate() + ";" + v.getGenre() + "; " + v.getRating() + ";\n");
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

    public void deleteLineFromFile(String filePath, String matchString) {
        // Read the contents of the file into a list
        ArrayList<String> lines = new ArrayList<>();
        boolean foundMatch = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the matchString
                if (!foundMatch && line.split(";")[0].equals(matchString)) {
                    foundMatch = true;
                    continue; // Skip this line and all subsequent lines
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write the modified contents back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            boolean firstLine = true;
            for (String line : lines) {
                if (!firstLine) {
                    writer.newLine(); // Add newline only if it's not the first line
                } else {
                    firstLine = false;
                }
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createUserFiles(String username, String password) {
        File fileH = new File(userHistoryPath + username + ".txt");
        File fileS = new File(userSavedPath + username + ".txt");
        try {
            FileWriter writer1 = new FileWriter(fileH);
            FileWriter writer2 = new FileWriter(fileS);
            writer1.close();
            writer2.close();
        } catch (IOException e){
                System.out.println("An error occured");
                e.printStackTrace();
            }
        try {
            FileWriter writer = new FileWriter("data\\UserData\\Users.txt", true);
            writer.write(username + ";" + password + ";" + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getUserHistoryPath() {
        return userHistoryPath;
    }

    public void setUserHistoryPath(String userHistoryPath) {
        this.userHistoryPath = userHistoryPath;
    }

    public String getUserSavedPath() {
        return userSavedPath;
    }

    public void setUserSavedPath(String userSavedPath) {
        this.userSavedPath = userSavedPath;
    }
}
