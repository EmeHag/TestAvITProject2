import org.example.DownloadSyllabus;
import org.example.DownloadTranscript;
import org.example.ExaminationDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testCases {

    @Test
    void checkFinalExaminationDate() {
        ExaminationDate.checkFinalExaminationDate();
        String expectedFinalDate = "2023-04-17";
        assertEquals(expectedFinalDate, ExaminationDate.examinationDate);
    }

    @Disabled
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
    @Disabled
    @Test
    void checkCreateButton(){
        DownloadTranscript.downloadTranscript();
        assertTrue(DownloadTranscript.isRegistreringsintygsButtonIsVisible());
    }



}