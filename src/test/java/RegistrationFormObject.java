import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormObject {

    private static String url = "https://demoqa.com/automation-practice-form";
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
    }

    public static void enterFirstName(String firstName){
        $(byXpath(RegistrationFormObject.firstNameXpath)).setValue(firstName);
    }

    public static void enterLastName(String lastName){
        $(byXpath(RegistrationFormObject.lastNameXpath)).setValue(lastName);
    }

    public static void enterEmail(String email){
        $(byXpath(RegistrationFormObject.emailXpath)).setValue(email);
    }

    public static void setGender(Gender gender){
        if (gender == Gender.Male) {
            $(byXpath(RegistrationFormObject.maleGenderXpath)).click();
        } else if (gender == Gender.Female) {
            $(byXpath(RegistrationFormObject.femaleGenderXpath)).click();
        } else {
            $(byXpath(RegistrationFormObject.otherGenderXpath)).click();
        }
    }

    public static void enterPhoneNumber(String phoneNumber){
        $(byXpath(RegistrationFormObject.phoneNumberXpath)).setValue(phoneNumber);
    }

    public static void setDateOfBirt(String dateOfBirth){
        $(byXpath(dateOfBirtTextbox)).click();

        int year = Integer.parseInt(dateOfBirth.split("\\.")[2]);
        $(byXpath(RegistrationFormObject.yearSelectXpath)).selectOptionByValue(String.valueOf(year));

        int month = Integer.parseInt(dateOfBirth.split("\\.")[1]) - 1;
        $(byXpath(RegistrationFormObject.monthSelectXpath)).selectOptionByValue(String.valueOf(month));

        int day = Integer.parseInt(dateOfBirth.split("\\.")[0]);
        String dayXpath = RegistrationFormObject.getDaySelectXpathWithDayValue(day);
        $(byXpath(dayXpath)).click();
    }

    public static void addSubject(String subject){
        $(byXpath(RegistrationFormObject.subjectsTextboxXpath)).setValue(subject.substring(0,3));
        String computerScienceXpath = getSubjectSelectXpathBySubjectName(subject);
        $(byXpath(computerScienceXpath)).click();
    }

    public static void addSubjects(List<String> subjects) {
        for (String subject: subjects) {
            RegistrationFormObject.addSubject(subject);
        }

    }
    
    public static void checkHobbyCheckbox(Hobby hobby){
        if (hobby == Hobby.Sports){
            checkSportsCheckbox();
        }else if (hobby == Hobby.Music){
            checkMusicCheckbox();
        } else {
            checkReadingCheckbox();
        }
    }

    public static void checkHobbiesCheckboxes(List<Hobby> hobbies){
        for (Hobby hobby:hobbies) {
            checkHobbyCheckbox(hobby);
        }
    }

    public static void enterCurrentAddress(String address){
        $(byXpath(currentAddressTextarea)).setValue(address);
    }

    public static void attachFileToForm(String path){
        $(byXpath(uploadLogoXpath)).uploadFile(new File(path));
    }

    public static void selectState(String stateName){
        $(byXpath(stateXpath)).click();
        String curStateXpath = getStateXpathByName(stateName);
        $(byXpath(curStateXpath)).click();

    }

    public static void selectCity(String cityName){
        $(byXpath(cityXpath)).click();
        String curCityXpath = getCityXpathByName(cityName);
        $(byXpath(curCityXpath)).click();
    }

    public static void clickSubmitButton(){
        $(byXpath(submitButtonXpath)).click();
    }

    private static void checkSportsCheckbox(){
        $(byXpath(sportsCheckboxXpath)).click();
    }

    private static void checkMusicCheckbox(){
        $(byXpath(musicCheckboxXpath)).click();
    }

    private static void checkReadingCheckbox(){
        $(byXpath(readingCheckboxXpath)).click();
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
