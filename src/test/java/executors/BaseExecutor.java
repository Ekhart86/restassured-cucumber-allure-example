package executors;

import constants.Endpoints;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.guest.GuestTokenRequest;

import static constants.Endpoints.AUTHORIZATION_BASIC;
import static io.restassured.RestAssured.given;

public class BaseExecutor {

    {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder().log(LogDetail.ALL).build();

    }

    /**
     * Запрос на получение токена гостя
     */
    public Response getGuestToken(String grantType, String scope) {

        return given()
                .header("Authorization", AUTHORIZATION_BASIC)
                .body(new GuestTokenRequest(grantType, scope))
                .post(Endpoints.CLIENT_CREDENTIALS_GRANT);
    }


}
