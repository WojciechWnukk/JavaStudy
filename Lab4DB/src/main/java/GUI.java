import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Baza Danych");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            JPanel panel = new JPanel();
            frame.add(panel);

            JButton dodajButton = new JButton("Dodaj Studenta");
            JButton odczytajButton = new JButton("Odczytaj Studenta");
            JButton modyfikujButton = new JButton("Modyfikuj Studenta");
            JButton usunButton = new JButton("Usuń Studenta");
            panel.add(dodajButton);
            panel.add(odczytajButton);
            panel.add(modyfikujButton);
            panel.add(usunButton);

            dodajButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dodajStudenta();
                }
            });

            odczytajButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    odczytajStudenta();
                }
            });

            modyfikujButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modyfikujStudenta();
                }
            });

            usunButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    usunStudenta();
                }
            });

            frame.setVisible(true);
        });
    }

    public static void dodajStudenta() {
        JFrame dodajFrame = new JFrame("Dodaj Studenta");
        dodajFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dodajFrame.setSize(400, 150);

        JPanel dodajPanel = new JPanel();
        dodajFrame.add(dodajPanel);

        JTextField nazwiskoField = new JTextField(20);
        JTextField imieField = new JTextField(20);

        JButton dodajButton = new JButton("Dodaj");
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazwisko = nazwiskoField.getText();
                String imie = imieField.getText();
                BazaDanych bazaDanych = new BazaDanych();
                boolean success = bazaDanych.wstawDane("STUDENCI", nazwisko, imie);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Student dodany poprawnie.");
                    wyswietlStudentow();
                    dodajFrame.dispose();
                }
            }
        });

        dodajPanel.add(new JLabel("Nazwisko:\n"));
        dodajPanel.add(nazwiskoField);
        dodajPanel.add(new JLabel("Imię:\n"));
        dodajPanel.add(imieField);
        dodajPanel.add(dodajButton);



        dodajFrame.setVisible(true);

    }

    public static void odczytajStudenta() {
        JFrame odczytajFrame = new JFrame("Odczytaj Studenta");
        odczytajFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        odczytajFrame.setSize(300, 150);

        JPanel odczytajPanel = new JPanel();
        odczytajFrame.add(odczytajPanel);

        JTextField idField = new JTextField(20);

        JButton odczytajButton = new JButton("Odczytaj");
        odczytajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                BazaDanych bazaDanych = new BazaDanych();
                List<Student> student = bazaDanych.pobierzDane("STUDENCI");
                if (student != null) {
                    JOptionPane.showMessageDialog(null, "ID: " + student.get(id-1).getId() + "\nNazwisko: " + student.get(id-1).getNazwisko() + "\nImię: " + student.get(id-1).getImie());
                    wyswietlStudentow();
                    odczytajFrame.dispose();
                }
            }
        });

        odczytajPanel.add(new JLabel("ID:"));
        odczytajPanel.add(idField);
        odczytajPanel.add(odczytajButton);

        odczytajFrame.setVisible(true);

    }

    public static void modyfikujStudenta() {
        JFrame modyfikujFrame = new JFrame("Modyfikuj Studenta");
        modyfikujFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modyfikujFrame.setSize(300, 150);

        JPanel modyfikujPanel = new JPanel();
        modyfikujFrame.add(modyfikujPanel);

        JTextField idField = new JTextField(20);
        JTextField nazwiskoField = new JTextField(20);
        JTextField imieField = new JTextField(20);

        JButton modyfikujButton = new JButton("Modyfikuj");
        modyfikujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String nazwisko = nazwiskoField.getText();
                String imie = imieField.getText();
                BazaDanych bazaDanych = new BazaDanych();
                boolean success = bazaDanych.modyfikujDane("STUDENCI", id, nazwisko, imie);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Student zmodyfikowany poprawnie.");
                    wyswietlStudentow();
                    modyfikujFrame.dispose();
                }
            }
        });

        modyfikujPanel.add(new JLabel("ID:"));
        modyfikujPanel.add(idField);
        modyfikujPanel.add(new JLabel("Nazwisko:"));
        modyfikujPanel.add(nazwiskoField);
        modyfikujPanel.add(new JLabel("Imię:"));
        modyfikujPanel.add(imieField);
        modyfikujPanel.add(modyfikujButton);

        modyfikujFrame.setVisible(true);

    }

    public static void usunStudenta() {
        JFrame usunFrame = new JFrame("Usuń Studenta");
        usunFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usunFrame.setSize(300, 150);

        JPanel usunPanel = new JPanel();
        usunFrame.add(usunPanel);

        JTextField idField = new JTextField(20);

        JButton usunButton = new JButton("Usuń");
        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                BazaDanych bazaDanych = new BazaDanych();
                boolean success = bazaDanych.usunDane("STUDENCI", id);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Student usunięty poprawnie.");
                    wyswietlStudentow();
                    usunFrame.dispose();
                }
            }
        });

        usunPanel.add(new JLabel("ID:"));
        usunPanel.add(idField);
        usunPanel.add(usunButton);

        usunFrame.setVisible(true);
    }

    public static void wyswietlStudentow() {
        BazaDanych bazaDanych = new BazaDanych();
        List<Student> student = bazaDanych.pobierzDane("STUDENCI");
        if (student != null && !student.isEmpty()) {
            StringBuilder studentInfo = new StringBuilder("Lista studentów:\n");
            for (Student s : student) {
                studentInfo.append("ID: ").append(s.getId()).append(", Nazwisko: ").append(s.getNazwisko()).append(", Imię: ").append(s.getImie()).append("\n");
            }
            JOptionPane.showMessageDialog(null, studentInfo.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Brak studentów w bazie danych.");
        }
    }
}
