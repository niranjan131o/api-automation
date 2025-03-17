package tests;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiTestUtils;

import static io.restassured.RestAssured.given;
import static utils.ApiTestUtils.logAndAssertResponse;

@Epic("API Automation Assignment")
@Feature("Get List of Areas API Tests")
public class GetAreasListAPITests extends BaseTest {

    private static final String ENDPOINT = "/v4/areas/"; // Get API Version 4 to get list of areas

    @Test(description = "Verify that GET areas returns valid response",priority = 0)
    public void testGetAreasSchemaValidation() {
        Response response = request.when().get("/v4/areas/");
        ApiTestUtils.logAndAssertResponse(response, 200,true);
    }

    @Test(description = "Verify GET Method returns status code 200",priority = 1)
    public void testGetAreas200() {
        Allure.step("Sending GET request to " + ENDPOINT);
        Response response = given()
                .when()
                .get(ENDPOINT);

        logAndAssertResponse(response, 200,true);
    }
//
//    @Test(description = "Verify GET /v4/areas/ without auth returns 401")
//    @Severity(SeverityLevel.BLOCKER)
//    @Story("Unauthorized access")
//    public void testGetAreas401() {
//        Allure.step("Sending GET request without authentication");
//
//        Response response = given()
//                .when()
//                .get(ENDPOINT);
//
//        logAndAssertResponse(response, 401,false);

//    }
//
//    @Test(description = "Verify GET /v4/areas/ with invalid token returns 403")
//    @Severity(SeverityLevel.BLOCKER)
//    @Story("Access with invalid token")
//    public void testGetAreas403() {
//        Allure.step("Sending GET request with an invalid token");
//
//        Response response = given()
//                .when()
//                .get(ENDPOINT);
//
//        logAndAssertResponse(response, 403,false);

//    }


    @Test(description = "Verify POST Method returns 405",priority = 2)
    @Severity(SeverityLevel.MINOR)
    @Story("Invalid HTTP method usage")
    public void testPostAreas405() {
        Allure.step("Sending POST request to " + ENDPOINT);

        Response response = given()
                .when()
                .post(ENDPOINT);
        Assert.assertEquals(response.jsonPath().getString("message"),"Method not allowed. Use GET method only.");

        logAndAssertResponse(response, 405,false);
    }


    @Test(description = "Verify PUT Method returns 405",priority = 3)
    @Severity(SeverityLevel.MINOR)
    @Story("Invalid HTTP method usage")
    public void testPutAreas405() {
        Allure.step("Sending PUT request to " + ENDPOINT);

        Response response = given()
                .when()
                .put(ENDPOINT);
        Assert.assertEquals(response.jsonPath().getString("message"),"Method not allowed. Use GET method only.");

        logAndAssertResponse(response, 405,false);
    }


    @Test(description = "Verify DELETE Method returns 405",priority = 4)
    @Severity(SeverityLevel.MINOR)
    @Story("Invalid HTTP method usage")
    public void testDeleteAreas405() {
        Allure.step("Sending DELETE request to " + ENDPOINT);

        Response response = given()
                .when()
                .delete(ENDPOINT);
        Assert.assertEquals(response.jsonPath().getString("message"),"Method not allowed. Use GET method only.");

        logAndAssertResponse(response, 405,false);
    }

}
