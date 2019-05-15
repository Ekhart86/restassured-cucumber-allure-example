package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;
import io.restassured.response.Response;
import models.register.RegisterPlayerRequest;
import models.register.RegisterPlayerResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RegisterPlayerSteps extends BaseExecutor {

    private Response response;
    private RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
    private RegisterPlayerResponse registerPlayerResponse;


    @Когда("^Отправляем запрос на регистрацию нового игрока c гостевым токеном$")
    public void отправляемЗапросНаРегистрациюНовогоИгрокаCГостевымТокеном() {
        response = registerPlayer(registerPlayerRequest, guestToken);
        response.then().log().all();
    }

    @Тогда("^Ответ на запрос регистрации имеет статус код (\\d+)$")
    public void статусОтвета(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Тогда("^Ответ на запрос регистрации соответствует документации$")
    public void ответНаЗапросРегистрацииСоответствуетДокументации() {
        registerPlayerResponse = response.then().extract().as(RegisterPlayerResponse.class);
        assertThat(registerPlayerResponse.getId(), is(notNullValue()));
        assertThat(registerPlayerResponse.getUsername(), equalTo(registerPlayerRequest.getUsername()));
        assertThat(registerPlayerResponse.getEmail(), equalTo(registerPlayerRequest.getEmail()));
        userId = registerPlayerResponse.getId();
        username = registerPlayerResponse.getUsername();
        userPassword = registerPlayerRequest.getPasswordChange();
        userEmail = registerPlayerRequest.getEmail();
    }


}
