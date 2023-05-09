package org.example;

import com.codeborne.selenide.Screenshots;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ExaminationDate {

    public static String examinationDate = "";

    public static void checkFinalExaminationDate (){
        Main.startProgramLogIn();
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

        // Take a screenshot
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
