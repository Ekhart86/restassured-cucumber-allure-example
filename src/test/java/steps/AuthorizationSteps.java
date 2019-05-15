package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;
import io.restassured.response.Response;
import models.authorization.AuthorizationRequest;
import models.authorization.AuthorizationResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthorizationSteps extends BaseExecutor {

    private Response response;
    private AuthorizationRequest authorizationRequest = new AuthorizationRequest(username, userPassword);
    private AuthorizationResponse authorizationResponse;

    @Когда("^Отправляем запрос на авторизацию под созданным игроком$")
    public void отправляемЗапросНаАвторизациюПодСозданнымИгроком() {
        response = authorizationByCreatedPlayer(authorizationRequest);
        response.then().log().all();
    }

    @Тогда("^Ответ на запрос авторизации имеет статус код (\\d+)$")
    public void ответНаЗапросАвторизацииИмеетСтатусКод(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Тогда("^Ответ на запрос авторизации содержит токен$")
    public void ответНаЗапросАвторизацииСодержитТокен() {
        authorizationResponse = response.then().extract().as(AuthorizationResponse.class);
        assertThat(authorizationResponse.getAccessToken(), is(not(emptyString())));
        tokenAfterLogin = authorizationResponse.getAccessToken();
    }

}
