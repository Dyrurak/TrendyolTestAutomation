package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertNotEquals;

public class ApiTestCase {
    public static String API_ROOT = "baseurl";
    public static String END_POINT = "/api/books/";
    public static String internalServerErrorMessage = "Internal Server Error has occurred!";

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = API_ROOT;
    }

    public void checkInternalServerError(Response response){
        assertNotEquals(response.statusCode(), HttpStatus.SC_INTERNAL_SERVER_ERROR, internalServerErrorMessage);
    }
}
