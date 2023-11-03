
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BazaDanych bazaDanych = new BazaDanych();
        System.out.println("Wybierz opcje \n 1. Dodawanie studenta do bazy \n 2. Odczyt studenta \n 3. Modyfikacja rekordów \n 4. Usuwanie rekordów \n 5. Pokaż wszystkie rekordy");
        Scanner scanner = new Scanner(System.in);
        int wybor = scanner.nextInt();

        switch (wybor) {
            case 1:
                System.out.println("Podaj nazwisko: ");
                String nazwisko = scanner.next();
                System.out.println("Podaj imie: ");
                String imie = scanner.next();
                bazaDanych.wstawDane("STUDENCI", nazwisko, imie);
                break;
            case 2:
                System.out.println("Podaj id: ");
                int id2 = scanner.nextInt();
                List<Student> Studenci = bazaDanych.pobierzDane("STUDENCI");
                Student student1 = Studenci.get(id2-1);
                System.out.println(student1.getId() + " " + student1.getNazwisko() + " " + student1.getImie());

                return;
            case 3:
                System.out.println("Podaj id: ");
                int id = scanner.nextInt();
                System.out.println("Podaj nazwisko: ");
                String nazwisko1 = scanner.next();
                System.out.println("Podaj imie: ");
                String imie1 = scanner.next();
                bazaDanych.modyfikujDane("STUDENCI", id, nazwisko1, imie1);
                break;
            case 4:
                System.out.println("Podaj id: ");
                int id1 = scanner.nextInt();
                bazaDanych.usunDane("STUDENCI", id1);
                break;
            case 5:
                break;
            default:
                System.out.println("Nie ma takiej opcji");
                break;
        }

        List<Student> lista = bazaDanych.pobierzDane("STUDENCI");
        if(lista.isEmpty()){
            System.out.println("Brak danych");
            return;
        }
        for (Student s : lista) {
            System.out.println(s.getId() + " " + s.getNazwisko() + " " + s.getImie());
        }
        bazaDanych.zamknijPolaczenie();
    }
}