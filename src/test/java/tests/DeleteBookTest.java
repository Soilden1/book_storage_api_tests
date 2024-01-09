package tests;

import api.BookApi;
import api.LoginApi;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import modeles.AddBookRequestModel;
import modeles.LoginRequestModel;
import modeles.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BookShelfPage;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static tests.TestData.LOGIN;
import static tests.TestData.PASSWORD;

@Epic("Работа с 'книжным шкафом'")
@Owner("dimacm14")
@Severity(CRITICAL)
public class DeleteBookTest extends TestBase {

    private final LoginApi login = new LoginApi();
    private final BookApi book = new BookApi();
    private final BookShelfPage bookShelf = new BookShelfPage();

    @Test
    @DisplayName("Удалить одну книгу")
    void deleteBook() {
        String isbnGitPocketGuide = "9781449325862",
                isbnSpeakingJavaScript = "9781449365035";

        LoginResponseModel loginResponse = login.login(new LoginRequestModel(LOGIN, PASSWORD));

        book.deleteAllBooks(loginResponse);
        book.addBook(loginResponse, new AddBookRequestModel(), isbnGitPocketGuide); // добавить Git Pocket Guide
        book.addBook(loginResponse, new AddBookRequestModel(), isbnSpeakingJavaScript); // добавить Speaking Java Script
        book.deleteBook(loginResponse, isbnGitPocketGuide); // удалить Git Pocket Guide

        bookShelf.setCookie(loginResponse)
                .openPage()
                .checkUserNameValue(LOGIN)
                .checkGitPocketBookIsMissing();
    }
}
