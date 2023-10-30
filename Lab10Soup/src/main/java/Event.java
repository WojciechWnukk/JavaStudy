import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private String title;
    private Date date;

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return "Tytuł: " + title + ", Data: " + dateFormat.format(date);
    }
}