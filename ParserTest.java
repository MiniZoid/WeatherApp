import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ParserTest {
    
    private JsonParser parserTest;


    public ParserTest(){}

    @BeforeEach
    public void setUp(){
        parserTest = new JsonParser();
    }

    @AfterEach
    public void tearDown(){
        parserTest = null;
    }

    @Test
    public void parseAndLoadTest(){
        parserTest.parseAndLoad("http://api.weatherapi.com/v1/current.json?key=f22bd71b6703489fb7f41918220303&q=90001&aqi=no");
        assertTrue(parserTest.parsed);
    }
}
