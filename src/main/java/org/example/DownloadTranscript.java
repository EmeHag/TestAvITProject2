package org.example;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static org.example.Main.logger;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class DownloadTranscript {
    // Set the boolean to false
    private static boolean skapaIntygButtonIsVisible = false;

    public static void downloadTranscript() {
        // Start the program and log in
        Main.startProgramLogIn();
        // Click on Intyg
        $(byXpath("//a[contains(text(),' Intyg »')]")).shouldBe(visible).click();

        // Switch to the new tab
        switchTo().window(1);

        // Choose to log in with your institution
        $(By.xpath("//span[text()='Access through your institution']")).shouldBe(visible).click();

        // Search for Luleå
        $(By.id("searchinput")).setValue("Luleå").pressEnter();

        // Click on Luleå tekniska universitet
        $(By.cssSelector("a.institution.identityprovider.bts-dynamic-item[data-href='https://idp.ltu.se/idp/shibboleth'] li")).shouldBe(visible).click();

        // Choose Swedish
        $(By.linkText("På svenska")).shouldBe(visible).click();

        // Click on Intyg
        $(By.linkText("Intyg")).shouldBe(visible).click();

        // Click on Skapa Intyg
       if ($(By.cssSelector("button.btn.btn-ladok-brand")).shouldBe(visible).isDisplayed()) {
            // Set the boolean to true if the button is visible
            skapaIntygButtonIsVisible = true;
            $(By.cssSelector("button.btn.btn-ladok-brand")).shouldBe(visible).click();
            logger.info("The button Skapa Intyg is there");
        } else {
            logger.warn("The button Skapa Intyg is not there");
        }

        // Choose Registreringsintyg in the dropdown menu
        $(By.id("intygstyp")).selectOption("Registreringsintyg");

        // Click on Skapa
        $(By.xpath("//span[text()='Skapa']")).shouldBe(visible).click();

        // Click the link to the newly created transcript
        $(By.linkText("Registreringsintyg")).shouldBe(visible).click();

        // Find the link to the PDF file
        SelenideElement pdfLink = $(By.partialLinkText("intyg"));

        try {
            // Click the link to download the file
            File downloadIntyg = pdfLink.download();

            // Move the downloaded file to the target/downloads directory with the name "Intyg.pdf"
            Files.move(downloadIntyg.toPath(), new File("target/downloads/Intyg.pdf").toPath(), StandardCopyOption.REPLACE_EXISTING);
            logger.info("File downloaded successfully");
        } catch (IOException e) {
            logger.error("File download failed" + e.getMessage());
        }

    }
    public static boolean isSkapaIntygButtonIsVisible() {
        // Return true if the button is visible
        return skapaIntygButtonIsVisible;
    }
}