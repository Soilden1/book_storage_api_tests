package api;

import io.qameta.allure.Step;
import modeles.AddBookRequestModel;
import modeles.DeleteBookRequestModel;
import modeles.IsbnModel;
import modeles.LoginResponseModel;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpec;
import static specs.Specs.responseSpec;

public class BookApi {

    @Step("Добавить книгу")
    public void addBook(LoginResponseModel loginResponse, AddBookRequestModel bookList, String isbnValue) {
        IsbnModel isbn = new IsbnModel(isbnValue);
        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbn);

        bookList.setUserId(loginResponse.getUserId());
        bookList.setCollectionOfIsbns(isbnList);

        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(bookList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .statusCode(201);
    }

    @Step("Удалить книгу")
    public void deleteBook(LoginResponseModel loginResponse, String isbn) {
        DeleteBookRequestModel deleteBookRequest = new DeleteBookRequestModel();
        deleteBookRequest.setUserId(loginResponse.getUserId());
        deleteBookRequest.setIsbn(isbn);

        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(deleteBookRequest)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec)
                .statusCode(204);
    }

    @Step("Удалить все книги")
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .statusCode(204);
    }
}