package com.bashkir

const val employee = """{"peopleInfo":{"phoneNumber":"74945952634","name":"Андрей","lastName":"Попов","middleName":"Викторович","email":"test@test.com","cred":null,"code":"TEACHER"},"salary":40000.0,"coef":1.0,"positionName":"Преподаватель","dateOfDismissal":null}"""

const val employee1 = """{"peopleInfo":{"phoneNumber":"79640000000","name":"Настасья","lastName":"Бойченко","middleName":"Сергеевна","email":"anime@tyan.nya","cred":null,"code":"TEACHER"},"salary":30000.0,"coef":1.5,"positionName":"Инструктор","dateOfDismissal":null}"""

const val employees = """[$employee,$employee1]"""

const val lessons = """"""

//fun Application.insertTestData() = transaction {
//    val positionInstructor = Position.new("Преподаватель") {}
//
//    val man1 = Employee.new("74945952634", {
//        salary = 40000F
//        coef = 1F
//        position = positionInstructor
//    }) {
//        name = "Андрей"
//        lastName = "Попков"
//        middleName = "Викторович"
//        email = "test@test.com"
//        login = "listar"
//        password = "forgotui1401"
//        code = Code.TEACHER
//    }
//
//    val man2 = Employee.new("79640000000", {
//        salary = 30000F
//        coef = 1.5F
//        position = Position["Инструктор"]
//
//    }) {
//        name = "Настасья"
//        lastName = "Бойченко"
//        middleName = "Сергеевна"
//        email = "anime@tyan.nya"
//        login = "stef"
//        password = "1234"
//        code = Code.TEACHER
//    }
//
//    val curse = EducationType.new("Курс") {}
//    val height = EducationType.new("Высшее") {}
//    val middle = EducationType.new("Среднее") {}
//    val middleSpecial = EducationType.new("Среднее специальное") { }
//
//    Education.new {
//        speciality = "Инженер"
//        institution = "МГУ"
//        periodOfStudy = "2010-2015"
//        educationTypeName = curse
//        employee = man1
//    }
//
//    Education.new {
//        speciality = "Слесарь"
//        institution = "МПТ"
//        periodOfStudy = "2015-2019"
//        educationTypeName = height
//        employee = man2
//    }
//
//    val lesson = LessonType.new {
//        name = "Лекция"
//        duration = 120
//        onePlace = false
//    }
//
//    val driving = LessonType.new {
//        name = "Вождение"
//        duration = 120
//        onePlace = true
//    }
//
//    Lessons.new {
//        date = Instant.now()
//        type = lesson
//        phoneNumber = man2
//    }
//
//    Lessons.new {
//        date = Instant.now().plusSeconds(1000000000)
//        type = driving
//        phoneNumber = man1
//    }
//}
