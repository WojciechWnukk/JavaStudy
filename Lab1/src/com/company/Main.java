package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        // Zadanie 1.1: Operacje na strumieniach

        List<String> przedmioty = new ArrayList<>();
        przedmioty.addAll(Arrays.asList("Integracja systemow", "Interakcja człowiek-komputer",
                "Programowanie aplikacji mobilnych na platformę Android",
                "Programowanie aplikacji mobilnych na platformę iOS"));

        Stream<String> strumienPrzedmiotow = przedmioty.stream();
        List<Integer> listaOcen = strumienPrzedmiotow
                .filter(przedmiot -> !przedmiot.contains("Zaaw"))
                .map(przedmiot -> {
                    return (int) (Math.random() * 4) + 2;
                })
                .collect(Collectors.toList());

        listaOcen.forEach(ocena -> System.out.println("1.1: Ocena: " + ocena));

        Map<Integer, Long> liczbaPowtorzen = listaOcen.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        liczbaPowtorzen.forEach((ocena, liczba) -> {
            if (liczba > 1) {
                System.out.println("Ocena " + ocena + " powtarza się " + liczba + " razy.");
            }
        });

        // Zadanie 1.2: Operacje na plikach

        List<String> ocenyIZapis = listaOcen
                .stream()
                .map(ocena -> "Ocena: " + ocena)
                .collect(Collectors.toList());

        Collections.sort(ocenyIZapis);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("oceny.txt"));
            for (String ocena : ocenyIZapis) {
                writer.write(ocena);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Zadanie 1.3: Operacje na plikach

        List<String> ocenyIZPliku = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("oceny.txt"));
            String linia;
            while ((linia = reader.readLine()) != null) {
                ocenyIZPliku.add(linia);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double srednia = ocenyIZPliku.stream()
                .mapToDouble(ocena -> Integer.parseInt(ocena.substring(7)))
                .average()
                .orElse(0);

        Optional<String> najlepszaOcena = ocenyIZPliku.stream()
                .max(Comparator.comparingInt(ocena -> Integer.parseInt(ocena.substring(7))));
        Optional<String> najgorszaOcena = ocenyIZPliku.stream()
                .min(Comparator.comparingInt(ocena -> Integer.parseInt(ocena.substring(7))));

        System.out.println("Średnia ocen: " + srednia);
        System.out.println("Najlepsza ocena: " + najlepszaOcena.orElse("Brak ocen"));
        System.out.println("Najgorsza ocena: " + najgorszaOcena.orElse("Brak ocen"));
    }
}
