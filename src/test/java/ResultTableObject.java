import com.codeborne.selenide.Condition;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public  class ResultTableObject {
    private static String nameXpath = "//tbody//td[text()='Student Name']//..//td[2]";
    private static String emailXpath = "//tbody//td[text()='Student Email']//..//td[2]";
    private static String genderXpath = "//tbody//td[text()='Gender']//..//td[2]";
    private static String mobileXpath = "//tbody//td[text()='Mobile']//..//td[2]";
    private static String dateOfBirthXpath = "//tbody//td[text()='Date of Birth']//..//td[2]";
    private static String subjectsXpath = "//tbody//td[text()='Subjects']//..//td[2]";
    private static String hobbiesXpath = "//tbody//td[text()='Hobbies']//..//td[2]";
    private static String pictureXpath = "//tbody//td[text()='Picture']//..//td[2]";
    private static String addressXpath = "//tbody//td[text()='Address']//..//td[2]";
    private static String cityAndStateXpath = "//tbody//td[text()='State and City']//..//td[2]";

    public static void verifyInputedDataForStudent(Student student){
        isCorrectStudentNamePresent(student.firstName + ' ' + student.lastName);
        isCorrectStudentEmailPresent(student.email);
        isCorrectGenderPresent(student.gender);
        isCorrectStudentMobilePresent(student.phoneNumber);
        isCorrectDateOfBirthPresent(student.dateOfBirth);
        isCorrectSubjectsPresent(student.subjects);
        isCorrectHobbiesPresent(student.hobbies);
        isCorrectPicturePresent(student.pathToLogo);
        isCorrectAddressPresent(student.address);
        isCorrectCityAndStatePresent(student.city, student.state);
    }

    private static void isCorrectStudentNamePresent(String name){
        $(byXpath(nameXpath)).shouldHave(Condition.text(name));
    }

    private static void isCorrectStudentEmailPresent(String email){
        $(byXpath(emailXpath)).shouldHave(Condition.text(email));
    }

    private static void isCorrectGenderPresent(Gender gender){
        $(byXpath(genderXpath)).shouldHave(Condition.text(gender.name()));
    }

    private static void isCorrectStudentMobilePresent(String phone){
        $(byXpath(mobileXpath)).shouldHave(Condition.text(phone));
    }

    private static void isCorrectDateOfBirthPresent(String dateOfBirth){

        String convertedDate = convertDateToOutputFormat(dateOfBirth);

        $(byXpath(dateOfBirthXpath)).shouldHave(Condition.text(convertedDate));
    }

    private static String convertDateToOutputFormat(String date){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.US);

        LocalDate outputDate = LocalDate.parse(date, inputFormatter);
        return outputDate.format(outputFormatter);
    }

    private static void isCorrectSubjectsPresent(List<String> subjects){
        for (String subject:subjects){
            $(byXpath(subjectsXpath)).shouldHave(Condition.text(subject));
        }
    }









    private static void isCorrectHobbiesPresent(List<Hobby> hobbies){
        for (Hobby hobby:hobbies){
            $(byXpath(hobbiesXpath)).shouldHave(Condition.text(hobby.name()));
        }

    }

    private static void isCorrectPicturePresent(String path){
        $(byXpath(pictureXpath)).shouldHave(Condition.text(path.split("/")[path.split("/").length-1]));
    }

    private static void isCorrectAddressPresent(String address){
        $(byXpath(addressXpath)).shouldHave(Condition.text(address));
    }

    private static void isCorrectCityAndStatePresent(String city, String state) {
        $(byXpath(cityAndStateXpath)).shouldHave(Condition.text(state + ' ' + city));
    }
}