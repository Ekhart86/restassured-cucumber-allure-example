package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;
import models.register.RegisterPlayerRequest;
import models.register.RegisterPlayerResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RegisterPlayerSteps extends BaseExecutor {

    private RegisterPlayerRequest registerPlayerRequest;
    private RegisterPlayerResponse registerPlayerResponse;

    @Когда("^отправляем запрос c гостевым токеном на регистрацию нового игрока$")
    public void отправляемЗапросСГостевымТокеномНаРегистрациюНовогоИгрока() {
        registerPlayerRequest = new RegisterPlayerRequest();
        currentResponse = registerPlayer(registerPlayerRequest, guestToken);
        currentResponse.then().log().all();
    }

    @Тогда("^ответ сервера на запрос регистрации соответствует документации$")
    public void ответНаЗапросРегистрацииСоответствуетДокументации() {
        registerPlayerResponse = currentResponse.then().extract().as(RegisterPlayerResponse.class);
        assertThat(registerPlayerResponse.getId(), is(notNullValue()));
        assertThat(registerPlayerResponse.getUsername(), equalTo(registerPlayerRequest.getUsername()));
        assertThat(registerPlayerResponse.getEmail(), equalTo(registerPlayerRequest.getEmail()));
        userId = registerPlayerResponse.getId();
        username = registerPlayerResponse.getUsername();
        userPassword = registerPlayerRequest.getPasswordChange();
        userEmail = registerPlayerRequest.getEmail();
    }


}
