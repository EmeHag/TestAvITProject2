import org.example.ExaminationDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.*;

public class testCases {

    @BeforeAll
    public static void setUp(){

    }


   @Test
    void checkFinalExaminationDate() {
        ExaminationDate.checkFinalExaminationDate();
        String expectedFinalDate = "2023-04-17";
        assertEquals(expectedFinalDate, ExaminationDate.examinationDate);
    }



/*
    @Test
    void checkIfSyllabusCanBeDownloaded() throws IOException {
        DownloadSyllabus.downloadSyllabus();

        // Check if the file exists
        assertTrue(new File("target/downloads/kursplan.pdf").exists());
    }

*/
}