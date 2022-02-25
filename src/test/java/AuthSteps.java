import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class AuthSteps {
    ValidatableResponse response;
    @Given("user can access api url {string}")
    public void userCanAccessApiUrl(String url) {
        RestAssured.baseURI = url;
    }

    @When("he call end point {string} with user {string} and password {string}")
    public void heCallEndPointWithUserAndPassword(String endpoint, String user, String pass) {
        Map<String, String> data = new HashMap<>();
        data.put("username", user);
        data.put("password", pass);
        data.put("rememberMe", "true");
        response = RestAssured.given().header("Content-type","application/json").body(data).post(endpoint).then();

    }

    @Then("api should return with status code {int}")
    public void apiShouldReturnWithStatusCode(int status) {
        response.assertThat().statusCode(status);
    }

    @And("message should {string}")
    public void messageShould(String msg) {
        response.assertThat().body("message", equalTo(msg));
    }
}
