package pages;

import java.io.File;
import java.util.List;
import com.codeborne.selenide.Condition;
import hobby.Hobby;
import gender.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private static Logger logger = LoggerFactory.getLogger(RegistrationPage.class);

    private static String url = "https://demoqa.com/automation-practice-form";
    private static String formXpath = "//*[@class='practice-form-wrapper']";
    private static String firstNameXpath = "//*[@id='firstName']";
    private static String lastNameXpath = "//*[@id='lastName']";
    private static String emailXpath = "//*[@id='userEmail']";
    private static String maleGenderXpath = "//*[text()='Male']";
    private static String femaleGenderXpath = "//*[text()='Female']";
    private static String otherGenderXpath = "//*[text()='Other']";
    private static String phoneNumberXpath = "//*[@id='userNumber']";
    private static String dateOfBirtTextbox = "//*[@id='dateOfBirthInput']";
    private static String yearSelectXpath = "//select[@class='react-datepicker__year-select']";
    private static String monthSelectXpath = "//select[@class='react-datepicker__month-select']";
    private static String daySelectXpath = "//*[contains(@class, 'react-datepicker__day') and text()={DAY_VALUE}]";
    private static String subjectsTextboxXpath = "//*[@id='subjectsInput']";
    private static String subjectSelectXpath = "//*[contains(@class, 'subjects-auto-complete__option') and text()='{SUBJECT_VALUE}']";
    private static String sportsCheckboxXpath = "//*[@class='custom-control-label' and text()='Sports']";
    private static String musicCheckboxXpath = "//*[@class='custom-control-label' and text()='Music']";
    private static String readingCheckboxXpath = "//*[@class='custom-control-label' and text()='Reading']";
    private static String currentAddressTextarea = "//*[@id='currentAddress']";
    private static String uploadLogoXpath = "//*[@id='uploadPicture']";
    private static String stateXpath = "//*[@id='state']";
    private static String stateItemXpath = "//*[text()='{STATE_VALUE}']";
    private static String cityXpath = "//*[@id='city']";
    private static String cityItemXpath = "//*[text()='{CITY_VALUE}']";
    private static String submitButtonXpath = "//*[@id='submit']";

    public static void openPageWithForm(){
        open(url);
        $x(formXpath).shouldHave(Condition.text("Student Registration Form"));
    }

    public static void enterFirstName(String firstName){
        $x(firstNameXpath).setValue(firstName);
    }

    public static void enterLastName(String lastName){
        $x(lastNameXpath).setValue(lastName);
    }

    public static void enterEmail(String email){
        $x(emailXpath).setValue(email);
    }

    public static void setGender(Gender gender){
        if (gender == Gender.Male) {
            $x(maleGenderXpath).click();
        } else if (gender == Gender.Female) {
            $x(femaleGenderXpath).click();
        } else if (gender == Gender.Other){
            $x(otherGenderXpath).click();
        } else {
            logger.error("Unknown gender was received: " + gender.name());
        }
    }

    public static void enterPhoneNumber(String phoneNumber){
        $x(phoneNumberXpath).setValue(phoneNumber);
    }

    public static void setDateOfBirth(String dateOfBirth){
        $x(dateOfBirtTextbox).click();

        int year = Integer.parseInt(dateOfBirth.split("\\.")[2]);
        $x(yearSelectXpath).selectOptionByValue(String.valueOf(year));

        int month = Integer.parseInt(dateOfBirth.split("\\.")[1]) - 1;
        $x(monthSelectXpath).selectOptionByValue(String.valueOf(month));

        int day = Integer.parseInt(dateOfBirth.split("\\.")[0]);
        String dayXpath = getDaySelectXpathWithDayValue(day);

        $x(dayXpath).click();
    }

    public static void addSubject(String subject){
        $x(subjectsTextboxXpath).setValue(subject.substring(0,3));
        String computerScienceXpath = getSubjectSelectXpathBySubjectName(subject);
        $x(computerScienceXpath).click();
    }

    public static void addSubjects(List<String> subjects) {
        for (String subject: subjects) {
            RegistrationPage.addSubject(subject);
        }
    }
    
    public static void checkHobbyCheckbox(Hobby hobby){
        if (hobby == Hobby.Sports){
            checkSportCheckbox();
        }else if (hobby == Hobby.Music){
            checkMusicCheckbox();
        } else if (hobby == Hobby.Reading) {
            checkReadingCheckbox();
        } else {
            logger.error("Unknown hobby was received: " + hobby.name());
        }
    }

    public static void checkHobbiesCheckboxes(List<Hobby> hobbies){
        for (Hobby hobby:hobbies) {
            checkHobbyCheckbox(hobby);
        }
    }

    public static void enterCurrentAddress(String address){
        $x(currentAddressTextarea).setValue(address);
    }

    public static void attachFileToForm(String path){
        $x(uploadLogoXpath).uploadFile(new File(path));
    }

    public static void selectState(String stateName){
        $x(stateXpath).click();
        String curStateXpath = getStateXpathByName(stateName);
        $x(curStateXpath).click();
    }

    public static void selectCity(String cityName){
        $x(cityXpath).click();
        String curCityXpath = getCityXpathByName(cityName);
        $x(curCityXpath).click();
    }

    public static void clickSubmitButton(){
        $x(submitButtonXpath).click();
    }

    private static void checkSportCheckbox(){
        $x(sportsCheckboxXpath).click();
    }

    private static void checkMusicCheckbox(){
        $x(musicCheckboxXpath).click();
    }

    private static void checkReadingCheckbox(){
        $x(readingCheckboxXpath).click();
    }

    private static String getDaySelectXpathWithDayValue(int day){
        return daySelectXpath.replace("{DAY_VALUE}", String.valueOf(day));
    }

    private static String getSubjectSelectXpathBySubjectName(String subjectName){
        return subjectSelectXpath.replace("{SUBJECT_VALUE}", subjectName);
    }

    private static String getStateXpathByName(String stateName){
        return stateItemXpath.replace("{STATE_VALUE}", stateName);
    }

    private static String getCityXpathByName(String cityName) {
        return cityItemXpath.replace("{CITY_VALUE}", cityName);
    }
}
