package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import modeles.LoginResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BookShelfPage {

    private final SelenideElement gitPocketBook = $("[id='see-book-Git Pocket Guide']"),
            userNameValue = $("#userName-value");

    @Step("Открыть страницу")
    public BookShelfPage openPage() {
        open("/profile");
        return this;
    }

    @Step("Получить куки")
    public BookShelfPage setCookie(LoginResponseModel loginResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
        return this;
    }

    @Step("Книга 'Git Pocket Guide' отсутствует в шкафу")
    public BookShelfPage checkGitPocketBookIsMissing() {
        gitPocketBook.shouldNotBe(visible);
        return this;
    }

    @Step("Проверить имя пользователя")
    public BookShelfPage checkUserNameValue(String text) {
        userNameValue.shouldHave(text(text));
        return this;
    }
}
