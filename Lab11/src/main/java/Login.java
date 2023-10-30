import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tries = 3;

        while(tries>0) {
            System.out.print("Enter your name: ");
            String login = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            tries--;
            if (isLoginValid(login, password)) {
                System.out.println("Login successful as " + login);
                break;
            } else {
                System.out.println("Login failed");
            }

        }
        if(tries==0) {
            System.out.println("Reached maximum number of tries");
        }


    }

    public static boolean isLoginValid(String login, String password) {
        try {
            FileReader fileReader = new FileReader("users.txt");
            Scanner scanner = new Scanner(fileReader);
            String fileLogin = scanner.nextLine();
            String filePassword = scanner.nextLine();
            scanner.close();
            fileReader.close();
            return login.equals(fileLogin) && password.equals(filePassword);
        } catch (Exception e) {
            System.out.println("Error");
        }
        return false;
    }



}
