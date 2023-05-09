import org.example.TestCaseDownloadSyllabus;
import org.example.TestCaseExaminationDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

public class testCases {

    @BeforeAll
    public static void setUp(){

    }


   /* @Test
    void checkFinalExaminationDate() {
        TestCaseExaminationDate.checkFinalExaminationDate();
        String expectedFinalDate = "2023-04-17";
        assertEquals(expectedFinalDate, TestCaseExaminationDate.examinationDate);
    }
    */



    @Test
    void checkIfSyllabusCanBeDownloaded() throws IOException {
        TestCaseDownloadSyllabus.downloadSyllabus();

        // Check if the file exists
        assertTrue(new File("target/downloads/kursplan.pdf").exists());
    }


}