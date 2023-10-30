package com.example.lab3;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Klient {
    JTextArea odbiorWiadomosci;
    JTextField wiadomosc;
    JTextField imieField;
    String imieKlienta;
    BufferedReader czytelnik;
    PrintWriter pisarz;
    Socket gniazdo;
    String kluczVigenere;
    public static void main(String[] args) {
        Klient klient = new Klient();
        klient.polaczMnie();
    }
    public void polaczMnie() {
        JFrame frame = new JFrame("Prosty klient czatu");
        JPanel panel = new JPanel();
        odbiorWiadomosci = new JTextArea(15, 50);
        odbiorWiadomosci.setLineWrap(true);
        odbiorWiadomosci.setWrapStyleWord(true);
        odbiorWiadomosci.setEditable(false);
        JScrollPane przewijanie = new JScrollPane(odbiorWiadomosci);
        przewijanie.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        przewijanie.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        wiadomosc = new JTextField(20);
        imieField = new JTextField(10);
        JButton przyciskWyslij = new JButton("Wyslij");
        przyciskWyslij.addActionListener(new SluchaczPrzycisku());

        JLabel imieLabel = new JLabel("Imię klienta:");

        panel.add(przewijanie);
        panel.add(wiadomosc);
        panel.add(imieLabel);
        panel.add(imieField);
        panel.add(przyciskWyslij);

        konfiguruj();
        Thread watekOdbiorcy = new Thread(new Odbiorca());
        watekOdbiorcy.start();

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(new Dimension(600, 400));
        frame.setVisible(true);

        imieKlienta = JOptionPane.showInputDialog(frame, "Podaj swoje imię:");
        kluczVigenere = JOptionPane.showInputDialog(frame, "Podaj klucz Vigenere'a:");
    }
    private void konfiguruj() {
        try {
            gniazdo = new Socket("127.0.0.1", 2021);
            InputStreamReader czytelnikStrm = new InputStreamReader(gniazdo.getInputStream());
            czytelnik = new BufferedReader(czytelnikStrm);
            pisarz = new PrintWriter(gniazdo.getOutputStream());
            System.out.println("Zakończono konfiguracje sieci");
        } catch (IOException ex) {
            System.out.println("Konfiguracja sieci nie powiodła się!");
            ex.printStackTrace();
        }
    }
    private String zaszyfrujWiadomosc(String wiadomosc) {
        StringBuilder zaszyfrowanaWiadomosc = new StringBuilder();
        int wiadomoscLength = wiadomosc.length();
        int kluczLength = kluczVigenere.length();

        for (int i = 0; i < wiadomoscLength; i++) {
            char znak = wiadomosc.charAt(i);
            char kluczZnak = kluczVigenere.charAt(i % kluczLength);
            char zaszyfrowanyZnak = (char) (znak + kluczZnak);
            zaszyfrowanaWiadomosc.append(zaszyfrowanyZnak);
        }

        return zaszyfrowanaWiadomosc.toString();
    }

    private String deszyfrujWiadomosc(String zaszyfrowanaWiadomosc) {
        StringBuilder odszyfrowanaWiadomosc = new StringBuilder();
        int wiadomoscLength = zaszyfrowanaWiadomosc.length();
        System.out.println("Odczytano zaszyfrowaną wiadomość: " + zaszyfrowanaWiadomosc);
        System.out.println("Klucz Vigenere'a: " + kluczVigenere);

        int kluczLength = kluczVigenere.length();

        for (int i = 0; i < wiadomoscLength; i++) {
            char znak = zaszyfrowanaWiadomosc.charAt(i);
            char kluczZnak = kluczVigenere.charAt(i % kluczLength);
            char odszyfrowanyZnak = (char) (znak - kluczZnak);
            odszyfrowanaWiadomosc.append(odszyfrowanyZnak);
        }

        return odszyfrowanaWiadomosc.toString();
    }


    private String formatujWiadomosc(String wiadomosc) {
        return imieKlienta + ": " + wiadomosc;
    }


    private class SluchaczPrzycisku implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                /*String sformatowanaWiadomosc = formatujWiadomosc(wiadomosc.getText());
                pisarz.println(sformatowanaWiadomosc);*/
                String zaszyfrowanaWiadomosc = zaszyfrujWiadomosc(wiadomosc.getText());
                pisarz.println(zaszyfrowanaWiadomosc);
                pisarz.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            wiadomosc.setText("");
            wiadomosc.requestFocus();
        }
    }
    public class Odbiorca implements Runnable {
        @Override
        public void run() {
            String zaszyfrowanaWiadomosc;
            System.out.println("Tu");
            try {
                while ((zaszyfrowanaWiadomosc = czytelnik.readLine()) != null) {
                    System.out.println("Odczytano zaszyfrowaną wiadomość: " + zaszyfrowanaWiadomosc);

                    // Deszyfrowanie wiadomości
                    String odszyfrowanaWiadomosc = deszyfrujWiadomosc(zaszyfrowanaWiadomosc);
                    System.out.println("Odszyfrowana wiadomość: " + odszyfrowanaWiadomosc);

                    // Wyświetlenie odszyfrowanej wiadomości na interfejsie
                    odbiorWiadomosci.append(odszyfrowanaWiadomosc + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}