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

public class DownloadTranscript {

    public static void downloadTranscript() throws FileNotFoundException {
        Main.startProgramLogIn();
        $(byXpath("//a[contains(text(),' Intyg »')]")).shouldBe(visible).click();

        // Switch to the new tab
        switchTo().window(1);

        $(By.xpath("//span[text()='Access through your institution']")).shouldBe(visible).click();

        $(By.id("searchinput")).setValue("Luleå").pressEnter();

        $(By.cssSelector("a.institution.identityprovider.bts-dynamic-item[data-href='https://idp.ltu.se/idp/shibboleth'] li")).shouldBe(visible).click();


        $(By.linkText("Intyg")).shouldBe(visible).click();

        //$(By.cssSelector("button.btn.btn-ladok-brand")).shouldBe(visible).click();

        // $(By.id("intygstyp")).selectOption("Registreringsintyg");

        // $(By.xpath("//span[text()='Skapa']")).shouldBe(visible).click();

        // click the link
        $(By.linkText("Registreringsintyg")).shouldBe(visible).click();

        // File downloadIntyg = $(By.tagName("body")).download();
        SelenideElement pdfLink = $(By.partialLinkText("intyg"));

        try {
            // Click the link to download the file
            File downloadIntyg = pdfLink.download();

            // Move the downloaded file to the target/downloads directory with the name "kursplan.pdf"
            Files.move(downloadIntyg.toPath(), new File("target/downloads/Intyg.pdf").toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // Handle the exception here
            e.printStackTrace();
        }

    }
}