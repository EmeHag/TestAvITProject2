import org.example.DownloadSyllabus;
import org.example.DownloadTranscript;
import org.example.ExaminationDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

public class testCases {

    @Disabled
    @Test
    void checkFinalExaminationDate() {
        ExaminationDate.checkFinalExaminationDate();
        String expectedFinalDate = "2023-04-17";
        assertEquals(expectedFinalDate, ExaminationDate.examinationDate);
    }


    @Test
    @Disabled
    void checkIfTranscriptCanBeDownloaded() throws FileNotFoundException {
        DownloadTranscript.downloadTranscript();

        // Check if the file exists
        assertTrue(new File("target/downloads/Intyg.pdf").exists());
    }

    @Disabled
    @Test
    void checkIfSyllabusCanBeDownloaded() {
        DownloadSyllabus.downloadSyllabus();

        // Check if the file exists
        assertTrue(new File("target/downloads/kursplan.pdf").exists());
    }
}