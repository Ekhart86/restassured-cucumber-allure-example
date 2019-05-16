package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;
import models.guest.GuestTokenResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GuestTokenSteps extends BaseExecutor {

    private GuestTokenResponse guestTokenResponse;

    @Когда("^отправляем запрос на получение токена гостя$")
    public void отправляемЗапросНаПолучениеТокенаГостя() {
        currentResponse = getGuestToken("client_credentials", "guest:default");
        currentResponse.then().log().all();
    }

    @Тогда("^ответ сервера содержит токен гостя$")
    public void ответСервераСодержитТокенГостя() {
        guestTokenResponse = currentResponse.then().extract().as(GuestTokenResponse.class);
        assertThat(guestTokenResponse.getAccessToken(), is(not(emptyString())));
        guestToken = guestTokenResponse.getAccessToken();
    }

}
