import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BazaDanych {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:mojaPierwszaBaza.db";
    private Connection connection;
    private Statement statement;

    public BazaDanych() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            System.err.println("Brak sterownika JDBC");
            ex.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
            tworzTabele();
        } catch (SQLException ex) {
            System.err.println("Problem z otwarciem połączenia");
            ex.printStackTrace();
        }
    }

    public boolean tworzTabele() {
        String tworz = "CREATE TABLE IF NOT EXISTS STUDENCI(id INTEGER PRIMARY KEY AUTOINCREMENT, nazwisko String, imie String)";
        try {
            statement.execute(tworz);
        } catch (SQLException e) {
            System.err.println("Błąd przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean wstawDane(String tabela, String nazwisko, String imie) {
        if(!walidacja(nazwisko) && !walidacja(imie)) {
            return false;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + tabela + " VALUES (null,?,?)");
            preparedStatement.setString(1, nazwisko);
            preparedStatement.setString(2, imie);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy wprowadzaniu danych studenta: " + nazwisko + " " + imie);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Student> pobierzDane(String tabela) {
        List<Student> wyjscie = new LinkedList<Student>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tabela);
            int id;
            String nazwisko, imie;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                nazwisko = resultSet.getString("nazwisko");
                imie = resultSet.getString("imie");
                wyjscie.add(new Student(id, nazwisko, imie));
            }
        } catch (SQLException e) {
            System.err.println("Problem z wczytaniem danych z BD");
            e.printStackTrace();
            return null;
        }
        return wyjscie;
    }

    public boolean modyfikujDane(String tabela, int id, String nazwisko, String imie) {
        if(!walidacja(nazwisko) && !walidacja(imie)) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + tabela + " SET nazwisko = ?, imie = ? WHERE id = ?");
            preparedStatement.setString(1, nazwisko);
            preparedStatement.setString(2, imie);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy modyfikacji danych studenta: " + nazwisko + " " + imie);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean usunDane(String tabela, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + tabela + " WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy usuwaniu danych studenta o id: " + id);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void zamknijPolaczenie() {
        try {
            connection.close();
        } catch (
                SQLException e) {
            System.err.println("Problem z zamknięciem połączenia");
            e.printStackTrace();
        }
    }

    public boolean walidacja(String dana) {
        if (dana == null || dana.equals("")) {
            System.out.println("Wprowadzono puste dane");
            return false;
        }
        if (dana.matches(".*\\d.*")) {
            System.out.println("Wprowadzono niepoprawne dane");
            return false;
        }
        return true;
    }
}
