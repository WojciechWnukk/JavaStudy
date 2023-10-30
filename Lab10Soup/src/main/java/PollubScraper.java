import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;

public class PollubScraper {
    public static void main(String[] args) {
        String url = "https://cs.pollub.pl/staff/";

        try {
            Document doc = Jsoup.connect(url).get();

            Element departmentContainer = doc.selectFirst("div.post-content");
            Elements elements = departmentContainer.getAllElements();
            String currentDepartment = null;
            List<String> currentEmployees = new ArrayList<>();

            for (Element element : elements) {
                if (element.tagName().equals("h3")) {
                    String departmentName = element.text();
                    if (!departmentName.contains("Kierownik Katedry")) {
                        if (currentDepartment != null) {
                            // Posortowanie pracowników alfabetycznie
                            Collections.sort(currentEmployees);

                            // Wyświetlenie posortowanych pracowników
                            for (String employee : currentEmployees) {
                                System.out.println("- " + employee);
                            }

                            // Wywołanie funkcji filterEmployees do filtrowania pracowników
                            List<String> filteredEmployees = filterEmployees(currentEmployees);
                            currentEmployees.clear();
                            if (!filteredEmployees.isEmpty()) {
                                System.out.println("\nOsoby z stopniem dr inż.:\n");
                                for (String filteredEmployee : filteredEmployees) {
                                    System.out.println("- " + filteredEmployee);
                                }
                            }
                        }
                        currentDepartment = departmentName;
                        System.out.println("\nZakład: " + currentDepartment);
                    }
                } else if (currentDepartment != null && element.tagName().equals("a")) {
                    currentEmployees.add(element.text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<String> filterEmployees(List<String> employees) {
        List<String> filteredEmployees = new ArrayList<>();
        for (String employee : employees) {
            if (employee.contains("dr inż.")) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }
}