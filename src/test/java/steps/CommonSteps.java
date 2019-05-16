package steps;

import cucumber.api.java.ru.Тогда;
import executors.BaseExecutor;

public class CommonSteps extends BaseExecutor {

    @Тогда("^код ответа сервера (\\d+)$")
    public void ответНаЗапросПрофиляИмеетСтатусКод(int statusCode) {
        currentResponse.then().statusCode(statusCode);
    }
}
