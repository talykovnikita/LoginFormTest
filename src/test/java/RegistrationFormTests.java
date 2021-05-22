import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {

    @Test
    void registrateNewMaleStudent() {
        Student maleStudent = new Student("maleName",
                "maleLastName",
                "male@email.com",
                Gender.Male,
                "9998887776",
                "08.06.1995",
                Arrays.asList("Computer Science", "Physics"),
                Arrays.asList(Hobby.Sports, Hobby.Music),
                "Male address 123 123",
                "src/test/resources/logo.png",
                "Haryana",
                "Panipat"
        );

        registrateStudent(maleStudent);
        ResultTableObject.verifyInputedDataForStudent(maleStudent);
    }

    @Test
    void registrateNewFemaleStudent(){
        Student maleStudent = new Student("femaleName",
                "femaleLastName",
                "female@email.com",
                Gender.Female,
                "9998887776",
                "03.01.2001",
                Arrays.asList("Computer Science", "Physics"),
                Arrays.asList(Hobby.Reading),
                "Female address 123 123",
                "src/test/resources/logo.png",
                "Haryana",
                "Panipat"
        );
        registrateStudent(maleStudent);
        ResultTableObject.verifyInputedDataForStudent(maleStudent);
    }

    @Test
    void registrateNewStudentWithoutSubjects() {
        Student student = new Student("femaleName",
                "femaleLastName",
                "female@email.com",
                Gender.Female,
                "9998887776",
                "03.01.2001",
                Collections.<String>emptyList(),
                Arrays.asList(Hobby.Reading),
                "Female address 123 123",
                "src/test/resources/logo.png",
                "Haryana",
                "Panipat"
        );

        registrateStudent(student);
        ResultTableObject.verifyInputedDataForStudent(student);
    }

    @Test
    void registrateNewStudentWithoutHobbies() {
        Student student = new Student("femaleName",
                "femaleLastName",
                "female@email.com",
                Gender.Other,
                "9998887776",
                "03.01.2001",
                Arrays.asList("Computer Science", "Physics"),
                Collections.<Hobby>emptyList(),
                "Female address 123 123",
                "src/test/resources/logo.png",
                "Haryana",
                "Panipat"
        );

        registrateStudent(student);
        ResultTableObject.verifyInputedDataForStudent(student);
    }

    private void registrateStudent(Student student){
        RegistrationFormObject.openPageWithForm();
        RegistrationFormObject.enterFirstName(student.firstName);
        RegistrationFormObject.enterLastName(student.lastName);
        RegistrationFormObject.enterEmail(student.email);
        RegistrationFormObject.setGender(student.gender);
        RegistrationFormObject.enterPhoneNumber(student.phoneNumber);
        RegistrationFormObject.setDateOfBirt(student.dateOfBirth);
        RegistrationFormObject.addSubjects(student.subjects);
        RegistrationFormObject.checkHobbiesCheckboxes(student.hobbies);
        RegistrationFormObject.attachFileToForm(student.pathToLogo);
        RegistrationFormObject.enterCurrentAddress(student.address);
        RegistrationFormObject.selectState(student.state);
        RegistrationFormObject.selectCity(student.city);
        RegistrationFormObject.clickSubmitButton();
        sleep(3000);
    }

    @BeforeEach
    private void preparation(){
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
    }
}
