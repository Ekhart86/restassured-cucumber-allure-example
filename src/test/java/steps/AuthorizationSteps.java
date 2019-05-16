package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;
import models.authorization.AuthorizationRequest;
import models.authorization.AuthorizationResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthorizationSteps extends BaseExecutor {

    private AuthorizationRequest authorizationRequest = new AuthorizationRequest(username, userPassword);
    private AuthorizationResponse authorizationResponse;

    @Когда("^отправляем запрос на авторизацию под созданным игроком$")
    public void отправляемЗапросНаАвторизациюПодСозданнымИгроком() {
        currentResponse = authorizationByCreatedPlayer(authorizationRequest);
        currentResponse.then().log().all();
    }

    @Тогда("^ответ сервера на запрос авторизации содержит токен$")
    public void ответСервераНаЗапросАвторизацииСодержитТокен() {
        authorizationResponse = currentResponse.then().extract().as(AuthorizationResponse.class);
        assertThat(authorizationResponse.getAccessToken(), is(not(emptyString())));
        tokenAfterLogin = authorizationResponse.getAccessToken();
    }

}
