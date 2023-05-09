import org.example.DownloadSyllabus;
import org.example.DownloadTranscript;
import org.example.ExaminationDate;
import org.example.Main;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testCases {

    @Test
    void checkFinalExaminationDate() {
        ExaminationDate.checkFinalExaminationDate();
        String expectedFinalDate = "2023-04-17";
        assertEquals(expectedFinalDate, ExaminationDate.examinationDate);
    }

    @Test
    void checkIfTranscriptCanBeDownloaded(){
        DownloadTranscript.downloadTranscript();
        // Check if the file exists
        assertTrue(new File("target/downloads/Intyg.pdf").exists());
    }

    @Test
    void checkIfSyllabusCanBeDownloaded() {
        DownloadSyllabus.downloadSyllabus();

        // Check if the file exists
        assertTrue(new File("target/downloads/kursplan.pdf").exists());
    }

    @Test
    void checkCreateButton(){
        DownloadTranscript.downloadTranscript();
        assertTrue(DownloadTranscript.isRegistreringsintygsButtonIsVisible());
    }



}