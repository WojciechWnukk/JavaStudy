import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchingData {
    public static void main(String[] args) {
    List<Event> events = searchGodzinyDziekanskie("https://pollub.pl/wyszukiwarka?query=godziny+dziekańskie");

    // Wyświetlenie wyników
    for (Event event : events) {
        System.out.println(event);
    }
}

    public static List<Event> searchGodzinyDziekanskie(String url) {
        List<Event> events = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements results = doc.select("h4");

            for (Element result : results) {
                String title = result.text();
                //System.out.println(title);
                String dateStr = result.select("small").text();
                //System.out.println(dateStr);
                if (!dateStr.isEmpty()) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateFormat.parse(dateStr);
                    events.add(new Event(title, date));
                }
            }
            // Sortowanie listy zdarzeń względem daty (od najstarszego do najmłodszego)
            events.sort((event1, event2) -> event1.getDate().compareTo(event2.getDate()));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return events;
    }

}