# Mayflower exercise
## Task:
Используя любой язык программирования необходимо написать следующие автотесты для сайта https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all
1. Вывести все строки таблицы Customers и убедиться, что запись с ContactName равной 'Giovanni Rovelli' имеет Address = 'Via Ludovico il Moro 22'.
2. Вывести только те строки таблицы Customers, где city='London'. Проверить, что в таблице ровно 6 записей.
3. Добавить новую запись в таблицу Customers и проверить, что эта запись добавилась.
4. Обновить все поля (кроме CustomerID) в любой записи таблицы Customersи проверить, что изменения записались в базу.
5. Придумать собственный автотест и реализовать (тут все ограничивается только вашей фантазией).
Заполнить поле ввода можно с помощью js кода, используя объект window.editor.

**Требования**:
- Для реализации обязательно использовать **Selenium WebDriver**
- Тесты должны запускаться **в docker контейнере**
- Код автотестов нужно выложить в любой git-репозиторий
## Pre-conditions:
1. You must have the docker
2. Run the docker
## Steps:
1. **Clone this repository**: git clone https://github.com/John-Golt1/Mayflower_test_exercise.git
2. **If you haven't selenoid** in your computer, **run the command**: docker pull selenoid/chrome:latest 
3. **In directory Mayflower_test_exercise** run the command: **docker-compose-up**
