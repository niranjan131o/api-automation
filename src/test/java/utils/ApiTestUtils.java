package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;

/**
 * Utility class for API test assertions and logging.
 */
public class ApiTestUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Logs the API response in JSON format and performs validations.
     * @param response
     * @param expectedStatusCode
     * @param responseValidation
     */
    public static void logAndAssertResponse(Response response, int expectedStatusCode,boolean responseValidation) {
        int statusCode = response.getStatusCode();

        try {
            JsonNode jsonResponse = objectMapper.readTree(response.asString());
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonResponse);

            Allure.addAttachment("Response JSON", prettyJson);

            // Validating status code
            Allure.step("Validating response status code: Expected " + expectedStatusCode + " | Actual " + statusCode);
            Assert.assertEquals(statusCode, expectedStatusCode, "Unexpected status code.");
            if(responseValidation){
                // Response schema validation for 2XX
                validateResponseStructure(jsonResponse);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Assert.fail("Failed to parse JSON response.");
        }
    }

    /**
     * Validates the structure and data integrity of the API response.
     *
     * @param jsonResponse
     */
    private static void validateResponseStructure(JsonNode jsonResponse) {
        // Verifying whether the count field is present or not
        Assert.assertTrue(jsonResponse.has("count"), "Response should contain 'count' field.");

        // Extracting count value to compare with areas array size
        int expectedCount = jsonResponse.get("count").asInt();

        // Verifying the area array is present or not
        Assert.assertTrue(jsonResponse.has("areas"), "Response should contain 'areas' field.");

        // extracting the whole area array object
        JsonNode areasArray = jsonResponse.get("areas");

        // Validating whether above extracted array object is a valid array or not
        Assert.assertTrue(areasArray.isArray(), "'areas' should be an array.");

        // Comparing both array size and count(both should match)
        Assert.assertEquals(areasArray.size(), expectedCount, "'areas' array size should match 'count'.");

        // Validate each 'area' array scheme object
        for (JsonNode area : areasArray) {
            Assert.assertTrue(area.has("id"), "Each area should have an 'id'.");
            Assert.assertTrue(area.has("name"), "Each area should have a 'name'.");
            Assert.assertTrue(area.has("countryCode"), "Each area should have a 'countryCode'.");
            Assert.assertTrue(area.has("parentAreaId"), "Each area should have a 'parentAreaId'.");
            Assert.assertTrue(area.has("parentArea"), "Each area should have a 'parentArea'.");

            // Validate country code is a string or not
            String countryCode = area.get("countryCode").asText();
//            if(countryCode.length()< 3){
//                System.out.println(countryCode);
//            }

            // Validate the country code length is greater than 2
            Assert.assertTrue(countryCode.length()>= 2, "Country code should be exactly 2 characters long.");

            // Validate parent area is not null
            Assert.assertFalse(area.get("parentArea").asText().isEmpty(), "Parent area should not be empty.");
        }

        Allure.step("Response structure validation successful.");
    }
}
