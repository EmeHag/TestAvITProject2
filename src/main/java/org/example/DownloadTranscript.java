package org.example;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.example.Main.logger;

public class DownloadTranscript {
    private static boolean registreringsintygsButtonIsVisible = false;

    public static void downloadTranscript(){
        Main.startProgramLogIn();
        $(byXpath("//a[contains(text(),' Intyg »')]")).shouldBe(visible).click();

        // Switch to the new tab
        switchTo().window(1);

        $(By.xpath("//span[text()='Access through your institution']")).shouldBe(visible).click();

        $(By.id("searchinput")).setValue("Luleå").pressEnter();

        $(By.cssSelector("a.institution.identityprovider.bts-dynamic-item[data-href='https://idp.ltu.se/idp/shibboleth'] li")).shouldBe(visible).click();

        $(By.linkText("På svenska")).shouldBe(visible).click();
        $(By.linkText("Intyg")).shouldBe(visible).click();

        //$(By.cssSelector("button.btn.btn-ladok-brand")).shouldBe(visible).click();

        // $(By.id("intygstyp")).selectOption("Registreringsintyg");

        // $(By.xpath("//span[text()='Skapa']")).shouldBe(visible).click();

        // click the link
        if ($(By.linkText("Registreringsintyg")).shouldBe(visible).isDisplayed()) {
            registreringsintygsButtonIsVisible = true;
            $(By.linkText("Registreringsintyg")).shouldBe(visible).click();
        } else {
            logger.warn("Registreringsintyg is not visible");
        }

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

    public static boolean isRegistreringsintygsButtonIsVisible() {
        return registreringsintygsButtonIsVisible;
    }
}