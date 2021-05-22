import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Locale;

public class ForTests {
    public enum Hobby {
        Sports,
        Reading,
        Music,
    }

    public static void main(String[] args){

        Hobby h = Hobby.Music;
        System.out.println(h.name());

    }
}
