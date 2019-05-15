package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;
import io.restassured.response.Response;
import models.guest.GuestTokenResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GuestTokenSteps {

    private BaseExecutor executor = new BaseExecutor();
    private Response response;

    @Когда("^Отправляем запрос на получение токена гостя, с параметром scope - \"([^\"]*)\"$")
    public void получитьТокенГостя(String scope) {
        response = executor.getGuestToken("client_credentials", scope);
    }

    @Тогда("^Получаем статус ответа (\\d+)$")
    public void статусОтвета(int statusCode) {
        response.then().statusCode(statusCode).log().all();
    }

    @Тогда("^Ответ содержит токен гостя$")
    public void ответСодержитТокен() {
        GuestTokenResponse guestTokenResponse = response.then().extract().as(GuestTokenResponse.class);
        assertThat(guestTokenResponse.getAccessToken(), is(not(emptyString())));
    }
}
