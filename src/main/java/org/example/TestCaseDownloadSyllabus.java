package org.example;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TestCaseDownloadSyllabus {

    public static void downloadSyllabus() throws IOException {
        // Read the JSON file
        Main.chromeDriver();

        // Navigate to the course syllabus
        $(By.cssSelector("button.ltu-search-btn")).click();

        // Search for the course
        $(By.id("cludo-search-bar-input")).setValue("I0015N").pressEnter();

        // Click the course, has to click on Våren 2024 because it is mislabelled
        $(byText("Våren 2024")).click();

        // Click the link to the syllabus
        $(By.linkText("Kursplan")).click();

        // Find the link to the PDF file
        SelenideElement pdfLink = $(By.cssSelector("a.utbplan-pdf-link"));

        // Click the link to download the file
        File downloadedFile = pdfLink.download();

        // Move the downloaded file to the target/downloads directory with the name "kursplan.pdf"
        Files.move(downloadedFile.toPath(), new File("target/downloads/kursplan.pdf").toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
