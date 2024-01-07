package api;

import io.qameta.allure.Step;
import modeles.LoginRequestModel;
import modeles.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpec;
import static specs.Specs.responseSpec;

public class LoginApi {

    @Step("Авторизоваться")
    public LoginResponseModel login(LoginRequestModel loginRequest) {
        return given(requestSpec)
                .body(loginRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}
