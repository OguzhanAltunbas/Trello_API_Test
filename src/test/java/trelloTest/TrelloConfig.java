package trelloTest;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import trelloApi.TrelloBoard;
import utils.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrelloConfig {

    public static final String API_KEY = "8e1888423683691df1564d1640c95660";
    public static final String TOKEN = "ATTAc3a8c3d1841ee29f9f178b8379a6dbde1057f7ca4898907fd999e5c59b26f0bf47B4125A";
    public static TrelloBoard trelloBoard;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "https://api.trello.com/1";
        trelloBoard = new TrelloBoard(API_KEY, TOKEN);

        Date start = new Date();
        String startTime = dateFormat.format(start);

        Logger.info("Test Start Time" + startTime);
    }

    @AfterClass
    public static void tearDown() {
        RestAssured.reset();

        Date finish = new Date();
        String finishTime = dateFormat.format(finish);
        Logger.info("Test Finish Time" + finishTime);
    }

}
