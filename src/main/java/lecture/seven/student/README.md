### Задание 1.1

<details>
    <summary>1.1</summary>
    <br>
    <img src="img.png"/>
    <br>
    <img src="img_1.png"/>
    <br>
    <img src="img_2.png"/>
    <br>
    <img src="img_3.png"/>
    <br>
    <img src="img_4.png"/>
    <br>
    <img src="img_5.png"/>
    <br>
    <img src="img_6.png"/>
</details>

<br>

### Задание 1.2

1. Что находится в секции <parent> и зачем она нужна?

В секции указан родительский POM — spring-boot-starter-parent.

* Что это: Это специальный артефакт, предоставляемый командой Spring Boot, который содержит базовую конфигурацию для любого Spring Boot проекта.

* Зачем нужен (основные функции):

    * Управление версиями: Он содержит секцию <dependencyManagement>, в которой уже подобраны совместимые версии для сотен библиотек (Spring, Hibernate, Jackson, Thymeleaf и т.д.). Благодаря этому вам не нужно указывать версии в своих зависимостях.

    * Базовая конфигурация: Он настраивает кодировку (UTF-8), версию Java, фильтрацию ресурсов.

    * Конфигурация плагинов: Предварительно конфигурирует плагины сборки (maven-compiler-plugin, maven-surefire-plugin и др.).

    * Упрощение сборки: Позволяет собирать исполняемый jar-файл (Fat JAR) без сложных ручных настроек при использовании spring-boot-maven-plugin.

2. Почему у большинства зависимостей нет версий?
   
* Потому что версии этих библиотек управляются централизованно через механизм наследования от родительского POM.

* Родитель spring-boot-starter-parent (версии 3.5.14), в свою очередь, ссылается на BOM (Bill of Materials) — spring-boot-dependencies. В этом BOM’е в секции <dependencyManagement> жестко зафиксированы проверенные на совместимость версии для spring-boot-starter-web, spring-boot-starter-security, thymeleaf-extras-springsecurity6 и прочих стартеров.

* Если бы вы указали версию вручную, возник бы риск конфликта версий (например, вы поставили бы версию Spring Security, не совместимую с версией Spring Framework, которую тянет веб-стартер). Отсутствие версий гарантирует, что проект соберется с теми библиотеками, которые разработчики Spring Boot протестировали вместе.

3. Что делает плагин spring-boot-maven-plugin?
   
Этот плагин выполняет три критически важные задачи при сборке:

* Упаковка Fat JAR (Исполняемый архив): Он переупаковывает ваш код и все библиотеки-зависимости в единый jar-файл. Внутри такого архива находится свой загрузчик классов (Spring Boot Loader), что позволяет запускать приложение просто командой java -jar student.jar, не подгружая classpath вручную.

* Поиск main-класса: Автоматически находит класс, помеченный @SpringBootApplication, чтобы знать, что запускать.

* Поддержка DevTools: Если в проекте есть spring-boot-devtools, плагин обеспечивает перезагрузку приложения при изменениях кода (во время разработки) без полной пересборки контейнера.

Без этого плагина команда mvn package соберет обычный тонкий jar, который нельзя будет запустить просто так.

4. Какие транзитивные зависимости подключает spring-boot-starter-web?

* Встроенный сервер приложений (Apache Tomcat)

* Spring MVC (Фреймворк веб-приложений)

* JSON-сериализация (Jackson)

* Ядро Spring Boot (Автоконфигурация)

* Микрометр (Наблюдаемость)

<br>

### Задание 2.1

1. Каким образом Spring «знает», что EnglishGreetingService нужно подставить в GreetingController?

Spring действует по четкому алгоритму, который называется "автоматическое связывание по типу" (autowiring by type). Вот пошаговое объяснение:

* Сканирование компонентов (Component Scanning): Когда запускается приложение Spring Boot, он сканирует пакеты, начиная с того, где находится главный класс с аннотацией @SpringBootApplication, и ищет классы, помеченные стереотипными аннотациями (@Component, @Service, @Repository, @Controller). Найдя EnglishGreetingService с аннотацией @Service, Spring создает его экземпляр (бин) и помещает его в свой контейнер (ApplicationContext) под идентификатором englishGreetingService (имя класса с маленькой буквы).

* Обнаружение точки внедрения: Когда Spring находит класс GreetingController, также помеченный аннотацией @RestController (которая внутри содержит @Controller и, следовательно, @Component), он пытается создать и его экземпляр.

* Разрешение зависимостей: Spring анализирует конструктор GreetingController и видит, что ему требуется аргумент типа GreetingService. Он обращается к своему контейнеру и задает вопрос: "Есть ли у меня бин, который реализует интерфейс GreetingService?"

* Внедрение (Injection): Контейнер находит ровно один подходящий бин — englishGreetingService. Поэтому он создает экземпляр GreetingController, передав этот бин в конструктор. Процесс завершен.

2. Что произойдёт, если убрать аннотацию @Service с класса EnglishGreetingService?

* Произойдет ошибка на этапе запуска приложения. Приложение не запустится и упадет с исключением NoSuchBeanDefinitionException.

Почему это происходит?

* Когда Spring пытается создать бин GreetingController, он не может найти ни одного кандидата на роль GreetingService в своем контейнере. 

* Класс EnglishGreetingService без аннотации — это просто обычный Java-класс, Spring его "не видит" и не создает его экземпляр.

3. Что произойдёт, если создать вторую реализацию GreetingService (например, RussianGreetingService) и тоже пометить её @Service — без дополнительной настройки?

* Это самая классическая проблема внедрения зависимостей — неоднозначность (ambiguity). Приложение снова не запустится, но уже с другой ошибкой.

Добавим второй сервис:

```
@Service
public class RussianGreetingService implements GreetingService {
    @Override
    public String greet(String name) {
        return "Привет, " + name + "!";
    }
}
```

Результат:

Spring снова попытается создать GreetingController. Он задает вопрос контейнеру: "Дай мне бин типа GreetingService". Но теперь контейнер находит два подходящих бина:

* englishGreetingService

* russianGreetingService

Spring не может решить за вас, какой из них нужен, и выбрасывает исключение NoUniqueBeanDefinitionException.

Как решить проблему (для справки):

Чтобы устранить неоднозначность, нужно явно указать Spring, какой бин использовать. Есть два основных способа:

* Использовать аннотацию @Qualifier в контроллере, указав имя нужного бина:

```
public GreetingController(@Qualifier("russianGreetingService") GreetingService greetingService) { ... }
```

* Использовать аннотацию @Primary на одном из сервисов, сделав его приоритетным кандидатом по умолчанию:

```
@Service
@Primary
public class EnglishGreetingService implements GreetingService { ... }
```

### Решение

```
// EnglishGreetingService.java — реализация
package lecture.seven.student.greet;

import org.springframework.stereotype.Service;

@Service
public class EnglishGreetingService implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
```

```
// GreetingController.java — REST-контроллер
package lecture.seven.student.greet;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/greet")
public class GreetingController {

    private final GreetingService greetingService;

    // Конструкторное внедрение — Spring сам передаст бин EnglishGreetingService
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/{name}")
    public String greet(@PathVariable String name) {
        return greetingService.greet(name);
    }
}
```

```
// GreetingService.java — интерфейс
package lecture.seven.student.greet;

public interface GreetingService {
    String greet(String name);
}
```

<details>
    <summary>2.1</summary>
    <br>
    <img src="img_7.png"/>
</details>

<br>

### Задание 2.2

1. Какой способ предпочтительнее и почему?

Однозначно предпочтительнее — Constructor Injection (ConstructorInjectionDemo).

Вот почему:

a) Явная и неизменяемая зависимость
Когда зависимость передается через конструктор, объект не может быть создан в невалидном состоянии. Вы не забудете передать GreetingService, потому что без него просто не получится создать экземпляр — компилятор не даст этого сделать. Глядя на конструктор, разработчик сразу видит: "этому классу для работы нужен GreetingService, и без него никак".

b) Неизменяемость (Immutability)
Поле service можно объявить как final, что гарантирует: после создания объекта зависимость никогда не изменится. Это делает поведение класса предсказуемым и потокобезопасным.

c) Тестируемость без Spring
Конструкторное внедрение позволяет в юнит-тестах просто создать объект через new и передать мок-зависимость — без поднятия Spring-контекста. Это быстро и просто.

d) Скрытый контекст
Зависимости, передаваемые через конструктор, воспринимаются как "обязательный контракт" класса. Зависимости через сеттеры — как "опциональные", а через поля — вообще как "магия, которая откуда-то берётся".

Официальная рекомендация Spring
Начиная с Spring Framework 4.x, команда разработчиков официально рекомендует использовать Constructor Injection для обязательных зависимостей.

2. Почему поле в ConstructorInjectionDemo может быть final, а в двух других — нет?

* Constructor =	Во время вызова конструктора, до завершения создания объекта = Можно final-поле обязано быть проинициализировано ровно один раз — либо при объявлении, либо в конструкторе. Конструктор идеально подходит.

* Setter = После создания объекта, отдельным вызовом сеттера = Нельзя. Объект уже создан, все final-поля уже зафиксированы. Сеттер вызывается позже и пытается присвоить значение уже "запечатанному" полю — это ошибка компиляции.

* Field	После создания объекта, через Reflection = Нельзя. Технически Spring может записать значение в final-поле через Reflection (с флагом Field.setAccessible(true) и снятием модификатора final), но это считается грязным трюком, не рекомендуется, и может не работать с некоторыми JVM. Обычный Java-код не позволяет этого сделать — ошибка компиляции.

Процесс создания бина Spring выглядит так:

* Вызывается конструктор → объект создан, final-поля зафиксированы.

* Вызываются сеттеры.

* Внедряются значения в поля через Reflection.

3. Какой способ труднее всего тестировать без Spring-контекста?

* Труднее всего тестировать — Field Injection (FieldInjectionDemo).

* Поле приватное! У вас нет ни конструктора, ни публичного сеттера, чтобы передать мок. 

* Единственные способы "добраться" до поля — использовать Reflection или запускать Spring-тест.

```
package lecture.seven.student.greet;

public class ConstructorInjectionDemo {
    private final GreetingService service;

    public ConstructorInjectionDemo(GreetingService service) {
        this.service = service;
    }
}
```

```
package lecture.seven.student.greet;

import org.springframework.beans.factory.annotation.Autowired;

public class SetterInjectionDemo {
    private GreetingService service;

    @Autowired
    public void setService(GreetingService service) {
        this.service = service;
    }
}
```

```
package lecture.seven.student.greet;

import org.springframework.beans.factory.annotation.Autowired;

public class SetterInjectionDemo {
    private GreetingService service;

    @Autowired
    public void setService(GreetingService service) {
        this.service = service;
    }
}
```

```
package lecture.seven.student.greet;

import org.springframework.beans.factory.annotation.Autowired;

public class FieldInjectionDemo {
    @Autowired
    private GreetingService service;
}
```

<details>
    <summary>2.2</summary>
    <br>
    <img src="img_8.png"/>
</details>

<br>

### Задание 3

<details>
    <summary>3</summary>
    <br>
    <img src="img_9.png"/>
</details>

```
package lecture.seven.student.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    public Student() {}

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // Геттеры и сеттеры для всех полей
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
}
```

applications.properties:
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

```

lecture.seven.student.repository:

```
package lecture.seven.student.repository;

import lecture.seven.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
```

lecture.seven.student.service.StudentService:
```
// StudentService.java
package lecture.seven.student.service;

import lecture.seven.student.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student save(Student student);
    Student findById(Long id);
    void deleteById(Long id);
}
```

lecture.seven.student.service.StudentServiceImpl:

```
package lecture.seven.student.service;

import lecture.seven.student.model.Student;
import lecture.seven.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public Student findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
```

lecture.seven.student.controller.StudentRestController:

```
package lecture.seven.student.controller;

import lecture.seven.student.model.Student;
import lecture.seven.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Student s = studentService.findById(id);
        return s != null ? ResponseEntity.ok(s) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        if (studentService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        student.setId(id);
        return ResponseEntity.ok(studentService.save(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (studentService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

Перед началом закомментируем зависимость Spring Security, чтобы не мешала.

<details>
    <summary>test</summary>
    <br>
    <img src="img_10.png"/>
    <br>
    <img src="img_11.png"/>
    <br>
    <img src="img_12.png"/>
    <br>
    <img src="img_13.png"/>
    <br>
    <img src="img_14.png"/>
</details>

<br>

### Задание 4

<details>
    <summary>test</summary>
    <br>
    <img src="img_15.png"/>
    <br>
    <img src="img_16.png"/>
</details>

lecture.seven.student.controller.StudentWebController:

```
package lecture.seven.student.controller;

import lecture.seven.student.model.Student;
import lecture.seven.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    private final StudentService service;

    public StudentWebController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", service.findAll());
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}
```

src/main/resources/templates/students.html:

```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Students</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">

<h2 class="mb-4">Список студентов</h2>

<form th:action="@{/students/save}" th:object="${student}" method="post"
      class="row g-2 mb-4">
    <div class="col-md-4">
        <input type="text" th:field="*{name}" class="form-control"
               placeholder="Имя" required/>
    </div>
    <div class="col-md-4">
        <input type="text" th:field="*{surname}" class="form-control"
               placeholder="Фамилия" required/>
    </div>
    <div class="col-md-4">
        <button type="submit" class="btn btn-primary">Добавить</button>
    </div>
</form>

<table class="table table-striped">
    <thead>
    <tr><th>ID</th><th>Имя</th><th>Фамилия</th><th>Действия</th></tr>
    </thead>
    <tbody>
    <tr th:each="s : ${students}">
        <td th:text="${s.id}"></td>
        <td th:text="${s.name}"></td>
        <td th:text="${s.surname}"></td>
        <td>
            <a th:href="@{/students/delete/{id}(id=${s.id})}"
               class="btn btn-danger btn-sm"
               onclick="return confirm('Удалить?')">Удалить</a>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
```

Перейдите в браузере на localhost:8080/students. Добавьте несколько студентов, удалите одного. 

Объясните: 

(1) что делает атрибут th:object?

(2) как Thymeleaf привязывает поля формы к объекту через th:field="*{name}"?

(3) что делает redirect: в значении возвращаемой строки?

Ответы:

1) Что делает атрибут th:object?

* Связывает HTML-форму с объектом Java из модели (`Model`). Он указывает, какой именно объект будет использовать данные из полей этой формы при отправке.

2) Как Thymeleaf привязывает поля формы через th:field="*{name}"?

* Через геттеры и сеттеры Java-объекта. Звёздочка `*` выбирает свойство объекта, указанного в `th:object`.

* Thymeleaf автоматически создаёт HTML-атрибуты `name`, `id` и `value`. При отправке формы Spring автоматически вызывает `setName()`.

3) Что делает redirect: в возвращаемой строке?

* Отправляет браузеру HTTP-ответ 302 для перехода на новый URL-адрес.

* Браузер выполняет новый чистый GET-запрос. 

* Предотвращает повторную отправку формы и дублирование данных в базе при обновлении страницы.

<details>
    <summary>test</summary>
    <br>
    <img src="img_17.png"/>
    <br>
    <img src="img_18.png"/>
    <br>
    <img src="img_19.png"/>
</details>
