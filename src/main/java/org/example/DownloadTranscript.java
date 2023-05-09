package org.example;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.example.Main.logger;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.example.Main.logger;

public class DownloadTranscript {
    private static boolean registreringsintygsButtonIsVisible = false;

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

        // Click on the link to transcripts
        $(By.linkText("Intyg")).shouldBe(visible).click();

        // Click on Skapa Intyg
        //$(By.cssSelector("button.btn.btn-ladok-brand")).shouldBe(visible).click();

        // Choose the type of transcript in the dropdown menu
        // $(By.id("intygstyp")).selectOption("Registreringsintyg");

        // Click on Skapa
        // $(By.xpath("//span[text()='Skapa']")).shouldBe(visible).click();

        // click the link
        if ($(By.linkText("Registreringsintyg")).shouldBe(visible).isDisplayed()) {
            registreringsintygsButtonIsVisible = true;
            $(By.linkText("Registreringsintyg")).shouldBe(visible).click();
        } else {
            logger.warn("Registreringsintyg is not visible");
        }
        // Click the link to the transcript
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
    public static boolean isRegistreringsintygsButtonIsVisible() {
        return registreringsintygsButtonIsVisible;
    }
}