import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Locale;

public class ForTests {

    public static void main(String[] args){

        String date = "15.3.2000";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d.m.yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.US);

        LocalDate outputDate = LocalDate.parse(date, inputFormatter);
        System.out.println(outputDate.format(outputFormatter));

    }
}
