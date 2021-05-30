package tests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import hobby.Hobby;
import gender.Gender;
import student.Student;
import pages.*;

public class RegistrationFormTests extends TestBase{

    @Test
    void successfulRegistration() {
        Student student = new Student("maleName",
                "maleLastName",
                "male@email.com",
                Gender.Male,
                "9998887776",
                "15.03.1995",
                Arrays.asList("Computer Science", "Physics"),
                Arrays.asList(Hobby.Sports, Hobby.Music),
                "Male address 123 123",
                "src/test/resources/logo.png",
                "Haryana",
                "Panipat"
        );

        registerStudent(student);
        ResultTable.verifyInputDataForStudent(student);
    }

    private void registerStudent(Student student) {
        RegistrationPage.openPageWithForm();
        RegistrationPage.enterFirstName(student.firstName);
        RegistrationPage.enterLastName(student.lastName);
        RegistrationPage.enterEmail(student.email);
        RegistrationPage.setGender(student.gender);
        RegistrationPage.enterPhoneNumber(student.phoneNumber);
        RegistrationPage.setDateOfBirth(student.dateOfBirth);
        RegistrationPage.addSubjects(student.subjects);
        RegistrationPage.checkHobbiesCheckboxes(student.hobbies);
        RegistrationPage.attachFileToForm(student.pathToLogo);
        RegistrationPage.enterCurrentAddress(student.address);
        RegistrationPage.selectState(student.state);
        RegistrationPage.selectCity(student.city);
        RegistrationPage.clickSubmitButton();
    }
}
