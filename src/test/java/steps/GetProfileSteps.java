package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;
import io.restassured.response.Response;
import models.profile.ProfileResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class GetProfileSteps extends BaseExecutor {

    private Response response;
    private ProfileResponse profileResponse;

    @Когда("^отправляем запрос на получение данных профиля авторизованного игрока$")
    public void отправляемЗапросНаПолучениеДанныхПрофиляАвторизованногоИгрока() {
        response = getUserProfile(userId, tokenAfterLogin);
        response.then().log().all();
    }

    @Тогда("^Ответ на запрос профиля имеет статус код (\\d+)$")
    public void ответНаЗапросПрофиляИмеетСтатусКод(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Тогда("^Ответ на запрос получения профиля соответствует документации$")
    public void ответНаЗапросПолученияПрофиляСоответствуетДокументации() {
        profileResponse = response.then().extract().as(ProfileResponse.class);
        assertThat(profileResponse.getId(), is(notNullValue()));
        assertThat(profileResponse.getId(), equalTo(userId));
        assertThat(profileResponse.getUsername(), equalTo(username));
        assertThat(profileResponse.getEmail(), equalTo(userEmail));
    }

    @Когда("^отправляем запрос на получение данных профиля другого игрока$")
    public void отправляемЗапросНаПолучениеДанныхПрофиляДругогоИгрока() {
        response = getUserProfile(userId - 1, tokenAfterLogin);
        response.then().log().all();
    }
}
