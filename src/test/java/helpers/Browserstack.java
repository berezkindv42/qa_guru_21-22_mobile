package helpers;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class Browserstack {

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic(config.browserstackLogin(), config.browserstackPassword())
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
