import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestCountriesAPITest {

    @Test
    public void getCountryByName_Positive() {
        String countryName = "Canada";
        Response response = RestAssured.get("https://restcountries.com/v3.1/name/" + countryName + "?fullText=true");

        // Verify the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getCountryByName_Negative() {
        String countryName = "InvalidCountryName";
        Response response = RestAssured.get("https://restcountries.com/v3.1/name/" + countryName + "?fullText=true");

        // Verify the status code is 404
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
