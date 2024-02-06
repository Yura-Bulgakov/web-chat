Для возможности удаленной отладки при запуске с помощью Maven 
нужно в папке проекта создать каталог .mvn и добавить в него файл
конфигурации jvm.config (<проект>/.mvn/jvm.config) со следующим значением:

-Xdebug -Xrunjdwp:transport=dt_socket,address=8080,server=y,suspend=y

## Список пользователей

| ID | Login  | Password | Name     | User Type | Banned  |
|----|--------|----------|----------|-----------|---------|
| 1  | admin  | admin    | Юрий     | Админ     | false   |
| 2  | user1  | user1    | Олег     | Клиент    | false   |
| 3  | user2  | user2    | Иван     | Клиент    | false   |
| 4  | user3  | user3    | Дмитрий  | Клиент    | true    |