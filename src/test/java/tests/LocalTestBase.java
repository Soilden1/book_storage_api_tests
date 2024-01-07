package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class LocalTestBase {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 10000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (!browser.equals("firefox")) {
            Attach.browserConsoleLogs();
        }
        Attach.addVideo();

        closeWebDriver();
    }
}
