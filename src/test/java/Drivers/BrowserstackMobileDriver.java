package Drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities caps) {
        // Set your access credentials
        caps.setCapability("browserstack.user", "berezkindv_Q8M15k");
        caps.setCapability("browserstack.key", "qdjj3ZPvdvkzyry2eMnX");

        // Set URL of the application under test
        caps.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");
        return new AndroidDriver(getBrowserstackUrl(), caps);
    }

//    @Override
//    public WebDriver createDriver(Capabilities capabilities) {
//        MutableCapabilities mutableCapabilities = new MutableCapabilities();
//        mutableCapabilities.merge(capabilities);
//        mutableCapabilities.setCapability("browserstack.appium_version", "1.22.0");
//        mutableCapabilities.setCapability("browserstack.user", browserstackLogin);
//        mutableCapabilities.setCapability("browserstack.key", browserstackPassword);
//        mutableCapabilities.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");
//        mutableCapabilities.setCapability("device", "Google Pixel 3");
//        mutableCapabilities.setCapability("os_version", "9.0");
//        mutableCapabilities.setCapability("project", "First Java Project");
//        mutableCapabilities.setCapability("build", "browserstack-build-1");
//        mutableCapabilities.setCapability("name", "first_test");
//
//        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
//    }
}
