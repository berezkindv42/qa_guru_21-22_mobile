package helpers;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    public static String videoUrl(String sessionId) {
        CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
        String url = format("https://api-cloud.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(config.browserstackLogin(), config.browserstackPassword())
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}

//    public static String
//            browserstackLogin = "berezkindv_Q8M15k",
//            browserstackPassword = "qdjj3ZPvdvkzyry2eMnX";
