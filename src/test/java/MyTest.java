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
    void test1() {
        int sum = 2 + 2;
        assertEquals(4, sum);
    }

    @Test
    void test2() {
        int sum = 3 + 3;
        assertEquals(6, sum);
    }
}