package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selectors.*;


import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class Main {
    public static String titleHomePage = "";

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

        // Close the previous tab
        switchTo().window(0).close();


    }
}