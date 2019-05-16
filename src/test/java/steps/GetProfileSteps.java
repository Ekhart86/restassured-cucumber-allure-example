package steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;
import models.profile.ProfileResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetProfileSteps extends BaseExecutor {

    private ProfileResponse profileResponse;

    @Когда("^отправляем запрос на получение данных профиля авторизованного игрока$")
    public void отправляемЗапросНаПолучениеДанныхПрофиляАвторизованногоИгрока() {
        currentResponse = getUserProfile(userId, tokenAfterLogin);
        currentResponse.then().log().all();
    }

    @Тогда("^ответ сервера на запрос получения профиля соответствует документации$")
    public void ответСервераНаЗапросПолученияПрофиляСоответствуетДокументации() {
        profileResponse = currentResponse.then().extract().as(ProfileResponse.class);
        assertThat(profileResponse.getId(), is(notNullValue()));
        assertThat(profileResponse.getId(), equalTo(userId));
        assertThat(profileResponse.getUsername(), equalTo(username));
        assertThat(profileResponse.getEmail(), equalTo(userEmail));
    }

    @Когда("^отправляем запрос на получение данных профиля другого игрока$")
    public void отправляемЗапросНаПолучениеДанныхПрофиляДругогоИгрока() {
        currentResponse = getUserProfile(userId - 1, tokenAfterLogin);
        currentResponse.then().log().all();
    }
}
