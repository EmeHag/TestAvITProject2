import org.example.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyTest {

    @BeforeAll
    public static void setUp(){

    }

    //
    @Test
    void checkIfLoginWasSuccessful() {
        // Start the program
        Main.startProgramLogIn();

        // Check that the title matches the expected title
        String expectedTitle = "Aktuellt - ltu.se";
        assertEquals(expectedTitle, Main.titleHomePage);

    }


    @Test
    void checkFinalExaminationDate() {
        String expectedFinalDate = "2023-04-17";
        assertEquals(expectedFinalDate, Main.examinationDate);
    }

    @Test
    void test2() {
        int sum = 3 + 3;
        assertEquals(6, sum);
    }
}