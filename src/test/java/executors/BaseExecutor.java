package executors;

import constants.Endpoints;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.authorization.AuthorizationRequest;
import models.guest.GuestTokenRequest;
import models.register.RegisterPlayerRequest;

import static io.restassured.RestAssured.given;
import static utils.Util.convertStringToBase64;

public class BaseExecutor {

    private final String authorizationBasicHeader = "Basic " + convertStringToBase64("front_2d6b0a8391742f5d789d7d915755e09e:");
    public static String guestToken;
    public static String tokenAfterLogin;
    public static Integer userId;
    public static String username;
    public static String userPassword;
    public static String userEmail;


    private RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Endpoints.BASE_URL)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .build();


    /**
     * POST Запрос на получение токена гостя
     */
    public Response getGuestToken(String grantType, String scope) {

        return given()
                .spec(requestSpecification)
                .header("Authorization", authorizationBasicHeader)
                .body(new GuestTokenRequest(grantType, scope))
                .log()
                .all()
                .post(Endpoints.CLIENT_CREDENTIALS_GRANT);
    }

    /**
     * POST Запрос на регистрацию нового пользователя
     */
    public Response registerPlayer(RegisterPlayerRequest body, String guestToken) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + guestToken)
                .body(body)
                .log()
                .all()
                .post(Endpoints.REGISTRATION);
    }

    /**
     * POST Запрос на авторизацию зарегестрированного пользователя
     */
    public Response authorizationByCreatedPlayer(AuthorizationRequest body) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", authorizationBasicHeader)
                .body(body)
                .log()
                .all()
                .post(Endpoints.AUTHORIZATION);
    }

    /**
     * GET Запрос на получение данных профиля пользователя
     */
    public Response getUserProfile(Integer userId, String tokenAfterLogin) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + tokenAfterLogin)
                .log()
                .all()
                .get(String.format(Endpoints.GET_PROFILE, userId));
    }
}
