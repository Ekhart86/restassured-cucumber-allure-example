Фреймворк для тестирования API с использованием Rest Assured, Cucumber, Allure 2

Для корректной работы в Intellij IDEA необходимо установить плагины Lombok и Cucumber for java.

Для запуска тестов необходимо скачать проект, зайти в корневую папку,

Выполнить в терминале команды:

Запуск тестов :

mvn clean test

Генерация локального отчёта:

mvn allure:serve

Либо можно сделать всё одной командой:

mvn clean test allure:serve

Пример отчёта:


![Allure 2 report](https://user-images.githubusercontent.com/25115868/57881644-83667400-782a-11e9-9c31-a71de0a31b82.png)

К каждому шагу содежащему запрос, прикрепляется 2 файла, с запросом и ответом от сервера.


![request](https://user-images.githubusercontent.com/25115868/57882430-59ae4c80-782c-11e9-9b7e-968a6ebfd2d9.png)

![response](https://user-images.githubusercontent.com/25115868/57882441-629f1e00-782c-11e9-8de3-a9e95a8eab3a.png)







