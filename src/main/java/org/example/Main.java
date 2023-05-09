package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Main {
    public static String titleHomePage = "";
    public static String examinationDate = "";

    public static void main(String[] args){
        startProgramLogIn();
    }

    public static void startProgramLogIn(){
        //Configuration.
        holdBrowserOpen = true;
        String email = "";
        String password = "";

        // Read the JSON file
        try {
            File jsonFile = new File("C:\\temp\\ltu.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            // Extract the username and password values
            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
        } catch (IOException e) {
            System.out.println("e");
        }

        System.setProperty("webdriver.chrome.driver", "src/main/java/chromedriver.exe");
        Configuration.startMaximized = true;
        open("https://www.ltu.se");

        //Cookies
        $(By.id("CybotCookiebotDialogBodyButtonDecline")).shouldBe(visible);
        $(By.id("CybotCookiebotDialogBodyButtonDecline")).click();

        //Navigate to login
        $(byXpath("//a[@href='/student' and @onclick=\"gaClickEvent('First page Extra links', 'Click', '/student');\"]")).click();
        $(byXpath("//a[@href='https://portal.ltu.se/group/student/start' and @onclick=\"gaClickEvent('First page Extra links', 'Click', 'https://portal.ltu.se/group/student/start');\"]")).click();

        // Enter username and password
        $("input[name='username']").setValue(email);
        $("input[name='password']").setValue(password);

        // Click the login button
        $(byValue("LOGGA IN")).should(be(enabled)).click();

        // Verify that the login was successful
        titleHomePage = title();

        checkFinalExaminationDate();
    }


    public static void checkFinalExaminationDate (){
        //Click on the tentamen button
        $(byXpath("//a[contains(text(),'Tentamen')]")).click();

        // Click on the tentamensschema button
        $(byXpath("//a[contains(text(),'Tentamensschema')]")).click();

        // Switch to the new tab
        switchTo().window(1);

        // find the search field by ID and enter the value "I0015N"
        $("#enkel_sokfalt").setValue("I0015N").pressEnter();

        // Click on the course link
        $(byLinkText("I0015N-VT23-47000-, Test av IT-system vt234 50")).click();

        // Switch to the new tab
        switchTo().window(2);

        // Save the examination date
        examinationDate = $(By.cssSelector("tr.data-white td.commonCell.data:last-child")).getText();

        try {
            // Take a screenshot and save it to a file
            File screenshotFile = Screenshots.takeScreenShotAsFile();

            // Save the screenshot to a specific directory with a specific name
            FileUtils.copyFile(screenshotFile, new File("target/screenshots/final_examination.jpeg"));
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }

    }
}