package com.bashkir

import io.ktor.client.*
import io.ktor.client.engine.cio.*

const val employees =
    """[{"peopleInfo":{"phoneNumber":"74945952634","name":"Андрей","lastName":"Попков","middleName":"Викторович","email":"test@test.com","cred":null,"code":"TEACHER"},"salary":40000.0,"coef":1.0,"positionName":"Преподаватель","dateOfDismissal":null},{"peopleInfo":{"phoneNumber":"79640000000","name":"Настасья","lastName":"Бойченко","middleName":"Сергеевна","email":"anime@tyan.nya","cred":null,"code":"TEACHER"},"salary":30000.0,"coef":1.5,"positionName":"Инструктор","dateOfDismissal":null},{"peopleInfo":{"phoneNumber":"76948952838","name":"Дмитрий","lastName":"Человек","middleName":null,"email":"best@vest.com","cred":null,"code":"ACCOUNTANT"},"salary":20000.0,"coef":0.5,"positionName":"Бухгалтер","dateOfDismissal":null},{"peopleInfo":{"phoneNumber":"76142942438","name":"Кирилл","lastName":"Волк","middleName":null,"email":"ploh@loh.com","cred":null,"code":"ADMIN"},"salary":150000.0,"coef":1.5,"positionName":"Администратор","dateOfDismissal":null},{"peopleInfo":{"phoneNumber":"71142945468","name":"Артемий","lastName":"Авдеев","middleName":"Сергеевич","email":"ggg@google.com","cred":null,"code":"HR"},"salary":15000.0,"coef":2.0,"positionName":"Рекуртер","dateOfDismissal":null}]"""

const val tariffs =
    """[{"id":0,"cost":20000.0,"name":"Эконом"},{"id":1,"cost":30000.0,"name":"Стандарт"},{"id":2,"cost":45000.0,"name":"Элит"}]"""

const val positions =
    """[{"position":"Инструктор"},{"position":"Преподаватель"},{"position":"Бухгалтер"},{"position":"Рекуртер"},{"position":"Администратор"}]"""

const val users =
    """[{"phoneNumber":"7964561924","name":"Виктор","lastName":"Гродский","middleName":"Аркадьевич","email":"vik@gmail.com","cred":null,"code":"STUDENT"},{"phoneNumber":"7452344234","name":"Петр","lastName":"Верзилов","middleName":"Юрьевич","email":"1@vk.ru","cred":null,"code":"STUDENT"},{"phoneNumber":"79640000000","name":"Настасья","lastName":"Бойченко","middleName":"Сергеевна","email":"anime@tyan.nya","cred":null,"code":"TEACHER"},{"phoneNumber":"74945952634","name":"Андрей","lastName":"Попков","middleName":"Викторович","email":"test@test.com","cred":null,"code":"TEACHER"},{"phoneNumber":"76948952838","name":"Дмитрий","lastName":"Человек","middleName":null,"email":"best@vest.com","cred":null,"code":"ACCOUNTANT"},{"phoneNumber":"76142942438","name":"Кирилл","lastName":"Волк","middleName":null,"email":"ploh@loh.com","cred":null,"code":"ADMIN"},{"phoneNumber":"71142945468","name":"Артемий","lastName":"Авдеев","middleName":"Сергеевич","email":"ggg@google.com","cred":null,"code":"HR"}]"""

const val lessons =
    """[{"id":4,"date":"2020-01-01T00:00:00Z","type":{"id":1,"name":"Лекция","duration":120,"onePlace":false},"phoneNumber":"74945952634","students":[{"userInfo":{"phoneNumber":"7452344234","name":"Петр","lastName":"Верзилов","middleName":"Юрьевич","email":"1@vk.ru","cred":null,"code":"STUDENT"},"graduation":"2020-01-01","tariff":{"id":0,"cost":20000.0,"name":"Эконом"},"lessons":[]}]}]"""

const val teachers =
    """[{"peopleInfo":{"phoneNumber":"79640000000","name":"Настасья","lastName":"Бойченко","middleName":"Сергеевна","email":"anime@tyan.nya","cred":null,"code":"TEACHER"},"salary":30000.0,"coef":1.5,"positionName":"Инструктор","dateOfDismissal":null},{"peopleInfo":{"phoneNumber":"74945952634","name":"Андрей","lastName":"Попков","middleName":"Викторович","email":"test@test.com","cred":null,"code":"TEACHER"},"salary":40000.0,"coef":1.0,"positionName":"Преподаватель","dateOfDismissal":null}]"""

const val teacherLessons =
    """[{"id":2,"date":"2053-08-10T20:24:09.531Z","type":{"id":2,"name":"Вождение","duration":120,"onePlace":true},"phoneNumber":"74945952634","students":[]},{"id":4,"date":"2020-01-01T00:00:00Z","type":{"id":1,"name":"Лекция","duration":120,"onePlace":false},"phoneNumber":"74945952634","students":[{"userInfo":{"phoneNumber":"7452344234","name":"Петр","lastName":"Верзилов","middleName":"Юрьевич","email":"1@vk.ru","cred":null,"code":"STUDENT"},"graduation":"2020-01-01","tariff":{"id":0,"cost":20000.0,"name":"Эконом"},"lessons":[]}]}]"""

const val vacations = """[{"id":0,"coef":1.0,"periodOfVacation":"2021.12.18 - 2022.01.05","employee":"74945952634"}]"""

const val vacationToPost =
    """{"id":1,"coef":0.5,"periodOfVacation":"2022.01.18 - 2022.01.28","employee":"79640000000"}"""

const val types =
    """[{"id":1,"name":"Лекция","duration":120,"onePlace":false},{"id":2,"name":"Вождение","duration":120,"onePlace":true}]"""

const val teacherOwnLessons =
    """[{"id":2,"date":"2053-08-10T20:24:09.531Z","type":{"id":2,"name":"Вождение","duration":120,"onePlace":true},"phoneNumber":"74945952634","students":[]},{"id":4,"date":"2020-01-01T00:00:00Z","type":{"id":1,"name":"Лекция","duration":120,"onePlace":false},"phoneNumber":"74945952634","students":[{"userInfo":{"phoneNumber":"7452344234","name":"Петр","lastName":"Верзилов","middleName":"Юрьевич","email":"1@vk.ru","cred":null,"code":"STUDENT"},"graduation":"2020-01-01","tariff":{"id":0,"cost":20000.0,"name":"Эконом"},"lessons":[]}]}]"""

const val lessonToPost =
    """{"id":8,"date":"2043-08-10T20:24:09.531Z","type":{"id":2,"name":"Вождение","duration":120,"onePlace":true},"phoneNumber":"74945952634","students":[]}"""

const val studentToPost =
    """"""

val client = HttpClient(CIO)

const val token =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL2F1dG8tc2Nob29sLmhlcm9rdWFwcC5jb20vbG9naW4iLCJwaG9uZU51bWJlciI6Ijc0NTIzNDQyMzQiLCJpc3MiOiJodHRwczovL2F1dG8tc2Nob29sLmhlcm9rdWFwcC5jb20vIiwiZXhwIjoxNjM5ODU4NDg3fQ.vb5JMZfCLLN1plbYWH7hoVwPfqkqEvZ6rcddXLzSar8"
const val studentToken =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL2F1dG8tc2Nob29sLmhlcm9rdWFwcC5jb20vbG9naW4iLCJwaG9uZU51bWJlciI6Ijc0NTIzNDQyMzQiLCJpc3MiOiJodHRwczovL2F1dG8tc2Nob29sLmhlcm9rdWFwcC5jb20vIiwiZXhwIjoxNjM5ODU4NDg3fQ.vb5JMZfCLLN1plbYWH7hoVwPfqkqEvZ6rcddXLzSar8"
const val accountantToken =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL2F1dG8tc2Nob29sLmhlcm9rdWFwcC5jb20vbG9naW4iLCJwaG9uZU51bWJlciI6Ijc2OTQ4OTUyODM4IiwiaXNzIjoiaHR0cHM6Ly9hdXRvLXNjaG9vbC5oZXJva3VhcHAuY29tLyIsImV4cCI6MTYzOTg1ODUyN30.CyyBo63JJP28w0xseT3v0TAFaMi6oyx2-h2ocee6p9E"
const val hrToken =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL2F1dG8tc2Nob29sLmhlcm9rdWFwcC5jb20vbG9naW4iLCJwaG9uZU51bWJlciI6IjcxMTQyOTQ1NDY4IiwiaXNzIjoiaHR0cHM6Ly9hdXRvLXNjaG9vbC5oZXJva3VhcHAuY29tLyIsImV4cCI6MTYzOTg1ODU2N30.IdiUqsArUTmobaRcuLiUdqJLpmPfDxMM8SZQ9F3hdDI"
const val adminToken =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL2F1dG8tc2Nob29sLmhlcm9rdWFwcC5jb20vbG9naW4iLCJwaG9uZU51bWJlciI6Ijc2MTQyOTQyNDM4IiwiaXNzIjoiaHR0cHM6Ly9hdXRvLXNjaG9vbC5oZXJva3VhcHAuY29tLyIsImV4cCI6MTYzOTg1ODYwNn0.bAyU0kWbGmFlJkG6eYmyISvg0v7ZYiFuAN3RPOr9WpE"
const val teacherToken =
    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL2F1dG8tc2Nob29sLmhlcm9rdWFwcC5jb20vbG9naW4iLCJwaG9uZU51bWJlciI6Ijc0OTQ1OTUyNjM0IiwiaXNzIjoiaHR0cHM6Ly9hdXRvLXNjaG9vbC5oZXJva3VhcHAuY29tLyIsImV4cCI6MTYzOTg1ODY0N30.BD_7uSJBk8ARRyTAXxsUVrsY00lFg4T5y6_daIFqnR0"

const val empPhone = "74945952634"

const val lessonId = 2