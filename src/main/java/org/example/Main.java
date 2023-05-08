package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration.*;
import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Condition.*;


import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class Main {

    public static void main(String[] args) {
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

        $(By.id("CybotCookiebotDialogBodyButtonDecline")).shouldBe(visible);
        $(By.id("CybotCookiebotDialogBodyButtonDecline")).click();

        // Enter username and password
        /*$("input[name='username']").setValue(email);
        $("input[name='password']").setValue(password);

        // Click the submit button
        $("button[type='submit']").click();

        // Verify that the login was successful
        $("h1").shouldHave(text("Welcome"));

       // $("input[name='q']").setValue("Selenide").pressEnter();
        //System.out.println("Title: " + title()); */
    }
}