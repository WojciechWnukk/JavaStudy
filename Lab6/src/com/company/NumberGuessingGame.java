package com.company;

import lombok.NonNull;

import java.util.Scanner;

@NonNull
public class NumberGuessingGame {

    private int numberToGuess;
    private int numberOfGuesses;
    private boolean hasWon;

    public NumberGuessingGame() {
        this.numberToGuess = (int) (Math.random() * 100) + 1;
        this.numberOfGuesses = 0;
        this.hasWon = false;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

            while (!hasWon) {
                System.out.println("Guess a number between 1 and 100");
                int guess = scanner.nextInt();
                numberOfGuesses++;
                if (guess == numberToGuess) {
                    hasWon = true;
                    System.out.println("You win!");
                } else if (guess > numberToGuess) {
                    System.out.println("Guess lower!");
                } else {
                    System.out.println("Guess higher!");
                }
        }
    }

}
