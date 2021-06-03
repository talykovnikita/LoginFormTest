package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import hobby.Hobby;
import gender.Gender;
import student.Student;
import pages.*;

public class RegistrationFormTests extends TestBase {

    @Test
    void successfulRegistration() {
        Student student = new Student("Name1",
                "LastName1",
                "student@email.com",
                Gender.Male,
                "9998887776",
                LocalDate.of(2000, 2, 15),
                Arrays.asList("Computer Science", "Physics"),
                Arrays.asList(Hobby.Sports, Hobby.Music),
                "Student address 123 123",
                "src/test/resources/logo.png",
                "Haryana",
                "Panipat"
        );

        RegistrationPage.registerStudent(student);
        ResultTable.verifyInputDataForStudent(student);
    }

    @Test
    void successfulRegistrationWithFaker() {
        Faker testDataProvider = new Faker();

        Student student = new Student(testDataProvider.name().firstName(),
                testDataProvider.name().lastName(),
                testDataProvider.internet().emailAddress(),
                Gender.Male,
                testDataProvider.numerify("##########"),
                LocalDate.of(1998, 12, 3),
                Arrays.asList("Computer Science", "Physics"),
                Arrays.asList(Hobby.Sports, Hobby.Music),
                testDataProvider.address().fullAddress(),
                "src/test/resources/logo.png",
                "Haryana",
                "Panipat"
        );

        RegistrationPage.registerStudent(student);
        ResultTable.verifyInputDataForStudent(student);
    }
}
