import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Register {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name: ");
        System.out.print("Login:");
        String login = scanner.nextLine();
        System.out.print("Password:");
        String password = scanner.nextLine();

        if (isPasswordValid(password)) {
            try {
                FileWriter fileWriter = new FileWriter("users.txt");
                fileWriter.write(login + "\n");
                fileWriter.write(password + "\n");
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error");
            }
        } else {
            System.out.println("Password is not valid");
        }

    }


    public static boolean isPasswordValid(String password) {
        // Sprawdzenie długości hasła
        if (password.length() < 8) {
            return false;
        }

        // Sprawdzenie różnorodności znaków
        if (!Pattern.matches(".*[a-z].*", password)) {
            return false; // Brak małych liter
        }
        if (!Pattern.matches(".*[A-Z].*", password)) {
            return false; // Brak wielkich liter
        }
        if (!Pattern.matches(".*\\d.*", password)) {
            return false; // Brak cyfr
        }
        if (!Pattern.matches(".*[!@#$%^&*()].*", password)) {
            return false; // Brak znaków specjalnych
        }

        // Sprawdzenie, czy nie ma powtarzających się znaków
        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                return false; // Powtarzają się znaki
            }
        }

        // Wszystkie kryteria spełnione
        return true;
    }
}
