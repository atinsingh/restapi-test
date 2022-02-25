import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CourseInfoSteps {
    RestAssured restAssured;
    ValidatableResponse response;
    @Given("User have access to api at {string}")
    public void userHaveAccessToApiAt(String url) {
        RestAssured.baseURI = url;
    }

    @When("User call the path {string} with course id {string}")
    public void userCallUrlWithCourseId(String path, String courseId) {
         response = RestAssured.given()
                .when().get(path, courseId)
                .then();
    }

    @Then("System should return response with status {int}")
    public void systemShouldReturnResponseWithStatus(int status) {
        response.assertThat().statusCode(status);
    }

    @And("course name should be {string}")
    public void courseNameShouldBe(String courseName) {
        response.assertThat().body("courseName", equalTo(courseName));
    }

    @And("number of instructor should be {int}")
    public void numberOfInstructorShouldBe(int count) {
        response.assertThat().body("instructor", hasSize(count));
    }
}
