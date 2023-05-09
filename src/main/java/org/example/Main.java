package org.example;

import com.codeborne.selenide.Configuration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        startProgramLogIn();
        logOut();
    }

    public static void chromeDriver() {
        try {
            holdBrowserOpen = true;
            System.setProperty("webdriver.chrome.driver", "src/main/java/chromedriver.exe");
            Configuration.startMaximized = true;
            open("https://www.ltu.se");

            //Cookies
            $(By.id("CybotCookiebotDialogBodyButtonDecline")).shouldBe(visible).click();

            logger.info("Driver initialized and cookies accepted");
        } catch (Exception e) {
            logger.error("Error while initializing driver: " + e.getMessage());
        }
    }

    public static void startProgramLogIn() {
        //Configuration.
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
            logger.info("Credentials successfully imported");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        chromeDriver();

        try {
            //Navigate to login
            $(byXpath("//a[@href='/student' and @onclick=\"gaClickEvent('First page Extra links', 'Click', '/student');\"]")).shouldBe(visible).click();
            $(byXpath("//a[@href='https://portal.ltu.se/group/student/start' and @onclick=\"gaClickEvent('First page Extra links', 'Click', 'https://portal.ltu.se/group/student/start');\"]")).click();

            // Enter username and password
            $("input[name='username']").shouldBe(visible).setValue(email);
            $("input[name='password']").setValue(password);

            // Click the login button
            $(byValue("LOGGA IN")).should(be(enabled)).click();
        }
        catch (Exception e) {
            logger.error("Error while logging in: " + e.getMessage());
        }
    }

    public static void logOut () {
        try {
            // Click the logout button
            $(By.id("_145_userAvatar")).click(); // Click the avatar dropdown
             //logOutButton = $(By.cssSelector(".sign-out")).click(); // Click the logout button
            logger.info("Logged out successfully");
        }
        catch (Exception e) {
            logger.error("Error while logging out: " + e.getMessage());
        }
    }
}