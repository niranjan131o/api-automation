package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

// This our base class where we will write all before and after classes and also Itestlisteners
public class BaseTest {
    protected RequestSpecification request;

    // Setting up Base URL and Headers
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.football-data.org/";
        request = RestAssured
                .given()
                .header("Cache-Control", "no-cache")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive");
    }
}