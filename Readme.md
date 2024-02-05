Это проект Telegram бота,присылающего сведения о погоде в городе.
Автор:Шииловских Лада, Баянов Лев.

Для работы проекта необходимо зарегестрировать свой бот в @BotFather и зарегестрироваться в https://openweathermap.org
Создать файл config.properties и поместить токен бота в переменную
BOT_TOKEN, имя бота в BOT_NAME, токен API в перменную WEATHER_SERVICE_TOKEN

Бот принимает название города на английском.
Пример работы:
-Moscow
-City: Moscow
 Temperature: 10.38
 Description: heavy rain
 WindSpeed: 8.9
Команда set сохраняет текущий город:
-set Ekaterinburg
-Ваш город проживания - Ekaterinburg
команда current отображает температуру в текущем городе:
-current
-City: Ekaterinburg
 Temperature: -8.9
 Description: light snow
 WindSpeed: 0.5