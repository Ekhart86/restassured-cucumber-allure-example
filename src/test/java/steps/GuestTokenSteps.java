package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;
import io.restassured.response.Response;
import models.guest.GuestTokenResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GuestTokenSteps extends BaseExecutor {


    private Response response;
    private GuestTokenResponse guestTokenResponse;


    @Когда("^Отправляем запрос на получение токена гостя$")
    public void получитьТокенГостя(String scope) {
        response = getGuestToken("client_credentials", "guest:default");
        response.then().log().all();
    }

    @Тогда("^Ответ на запрос получения токена имеет статус код (\\d+)$")
    public void статусОтвета(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Тогда("^Ответ на запрос получения токена содержит токен гостя$")
    public void ответСодержитТокен() {
        guestTokenResponse = response.then().extract().as(GuestTokenResponse.class);
        assertThat(guestTokenResponse.getAccessToken(), is(not(emptyString())));
        guestToken = guestTokenResponse.getAccessToken();
    }

}
