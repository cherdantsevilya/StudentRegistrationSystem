# StudentRegistrationSystem

## Описание

Тестовое задание. 
<br>
Приложение предоставляет реализацию REST API, а также хранит данную информацию в БД, для системы учета студентов.
<br>
С помощью инструмента Liquibase на вход подается несколько скриптов: список дисциплин, список учебных групп, список студентов (без списка оценок).
<br>
А в дальнейшем происходит тестирования, путем поднятия PostgreSQL c помощью Docker и Testcontainers.

## Запуск

Для того чтобы запустить приложение у вас должен быть установлен **[Docker](https://docs.master.dockerproject.org/desktop/install/windows-install/)**.
<br>
Если вы работаете на Windows, необоходимо также установить **[WSL2](https://learn.microsoft.com/en-us/windows/wsl/install-manual)**.

Открываем приложение в среде разработки и запускаем тесты. 
<br>
Также можно поднять легковесную базу данных H2 и попробовать отправить несколько запросов с помощью Postman.

## Методы

**GET /api/group/search** - получить все группы

**GET /api/subject/search** - получить все предметы

**GET /api/student/search** - получить всех студентов

**GET /api/student/surname** - получить всех студентов отсортированных по фамилии

**GET /api/student/name** - получить всех студентов отсортированных по имени

**GET /api/student/age** - получить всех студентов отсортированных по возрасту

**GET /api/student/surname/{surname}** - получить всех студентов по совпадению фамилии

**GET /api/student/name/{name}** - получить всех студентов по совпадению имени

**GET /api/student/age/{age}** - получить всех студентов по совпадению возраста

**GET /api/student/groupId/{groupId}** - получить всех студентов по идентификатору группы

**POST /api/student/new/group/{groupId}** - создать студента и прикрепить его к группе

**PUT /api/student/{studentId}/group/{groupId}** - добавить студенту группу

**PUT /api/student/{studentId}/subject/{subjectId}** - добавить студенту предмет

и др..

