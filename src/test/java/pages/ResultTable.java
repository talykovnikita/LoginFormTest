package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.codeborne.selenide.SelenideElement;
import hobby.Hobby;
import gender.Gender;
import student.Student;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ResultTable {
    private static final SelenideElement header = $(".modal-title.h4");
    private static final SelenideElement nameCell = $x("//tbody//td[text()='Student Name']//..//td[2]");
    private static final SelenideElement emailCell = $x("//tbody//td[text()='Student Email']//..//td[2]");
    private static final SelenideElement genderCell = $x("//tbody//td[text()='Gender']//..//td[2]");
    private static final SelenideElement mobileCell = $x("//tbody//td[text()='Mobile']//..//td[2]");
    private static final SelenideElement dateOfBirthCell = $x("//tbody//td[text()='Date of Birth']//..//td[2]");
    private static final SelenideElement subjectsCell = $x("//tbody//td[text()='Subjects']//..//td[2]");
    private static final SelenideElement hobbiesCell = $x("//tbody//td[text()='Hobbies']//..//td[2]");
    private static final SelenideElement pictureCell = $x("//tbody//td[text()='Picture']//..//td[2]");
    private static final SelenideElement addressCell = $x("//tbody//td[text()='Address']//..//td[2]");
    private static final SelenideElement cityAndStateCell = $x("//tbody//td[text()='State and City']//..//td[2]");

    public static void verifyInputDataForStudent(Student student) {
        isResultTableOpened();
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

    private static void isResultTableOpened(){
        header.shouldHave(text("Thanks for submitting the form"));
    }

    private static void isCorrectStudentNamePresent(String name) {
        nameCell.shouldHave(text(name));
    }

    private static void isCorrectStudentEmailPresent(String email) {
        emailCell.shouldHave(text(email));
    }

    private static void isCorrectGenderPresent(Gender gender) {
        genderCell.shouldHave(text(gender.name()));
    }

    private static void isCorrectStudentMobilePresent(String phone) {
        mobileCell.shouldHave(text(phone));
    }

    private static void isCorrectDateOfBirthPresent(LocalDate date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.US);
        String convertedDate = date.format(outputFormatter);
        dateOfBirthCell.shouldHave(text(convertedDate));
    }

    private static void isCorrectSubjectsPresent(List<String> subjects) {
        for (String subject : subjects) {
            subjectsCell.shouldHave(text(subject));
        }
    }

    private static void isCorrectHobbiesPresent(List<Hobby> hobbies) {
        for (Hobby hobby : hobbies) {
            hobbiesCell.shouldHave(text(hobby.name()));
        }
    }

    private static void isCorrectPicturePresent(String path) {
        pictureCell.shouldHave(text(path.split("/")[path.split("/").length - 1]));
    }

    private static void isCorrectAddressPresent(String address) {
        addressCell.shouldHave(text(address));
    }

    private static void isCorrectCityAndStatePresent(String city, String state) {
        cityAndStateCell.shouldHave(text(state + ' ' + city));
    }
}