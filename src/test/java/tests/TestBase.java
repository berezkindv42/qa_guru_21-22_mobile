package tests;

import config.CredentialsConfig;
import drivers.BrowserstackMobileDriver;
import drivers.EmulatorMobileDriver;
import com.codeborne.selenide.Configuration;
import drivers.RealPhoneMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.getSessionId;


public class TestBase {

    @BeforeAll
    public static void setup() {
        CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
        addListener("AllureSelenide", new AllureSelenide());

        String deviceHostVar = System.getProperty("deviceHost");

//        String deviceHost = config.deviceHost().toLowerCase();
        String deviceHost = deviceHostVar.toLowerCase();
        switch (deviceHost) {
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "emulator":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                break;
            case "real":
                Configuration.browser = RealPhoneMobileDriver.class.getName();
                break;
            default:
                throw new RuntimeException("Неверно передан параметр deviceHost, " +
                        "вберите из: browserstack / emulator / real");
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();

        Attach.attachAsText("Устройство запуска", "");
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        closeWebDriver();

        Attach.video(sessionId);
    }
}
