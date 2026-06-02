### Тест

<details open>
    <summary>test</summary>
    <br>
    <img src="img_96.png"/>
    <br>
    <img src="img_60.png"/>
    <br>
    <img src="img_61.png"/>
    <br>
    <img src="img_62.png"/>
    <br>
    <img src="img_63.png"/>
    <br>
    <img src="img_64.png"/>
    <br>
    <img src="img_65.png"/>
    <br>
    <img src="img_66.png"/>
    <br>
    <img src="img_67.png"/>
    <br>
    <img src="img_68.png"/>
    <br>
    <img src="img_69.png"/>
    <br>
    <img src="img_70.png"/>
    <br>
    <img src="img_71.png"/>
    <br>
    <img src="img_72.png"/>
    <br>
    <img src="img_73.png"/>
    <br>
    <img src="img_74.png"/>
    <br>
    <img src="img_75.png"/>
    <br>
    <img src="img_76.png"/>
    <br>
    <img src="img_77.png"/>
    <br>
    <img src="img_78.png"/>
    <br>
    <img src="img_79.png"/>
    <br>
    <img src="img_80.png"/>
    <br>
    <img src="img_81.png"/>
    <br>
    <img src="img_82.png"/>
    <br>
    <img src="img_83.png"/>
    <br>
    <img src="img_84.png"/>
    <br>
    <img src="img_85.png"/>
    <br>
    <img src="img_86.png"/>
    <br>
    <img src="img_87.png"/>
    <br>
    <img src="img_88.png"/>
    <br>
    <img src="img_89.png"/>
    <br>
    <img src="img_90.png"/>
    <br>
    <img src="img_91.png"/>
    <br>
    <img src="img_92.png"/>
    <br>
    <img src="img_93.png"/>
    <br>
    <img src="img_94.png"/>
    <br>
    <img src="img_95.png"/>
</details>

### Задание 1.1

<details open>
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

## Задание 1.2

1. Что находится в секции <parent> и зачем она нужна?

В секции указан родительский POM — spring-boot-starter-parent. Он нужен для:

- Управления версиями: содержит список совместимых версий сотен библиотек (dependencyManagement).

- Задает кодировку (UTF-8), версию Java и конфигурацию компилятора.

- Настройки плагинов: упрощает сборку исполняемого jar-файла.

2. Почему у большинства зависимостей нет версий?

* Версиями централизованно управляет родительский POM через BOM (Bill of Materials).

* Гарантирует совместимость библиотек между собой и исключает конфликты версий. 

* Если указать версию вручную, проект может не собраться из-за несовместимости.

3. Что делает плагин spring-boot-maven-plugin?

Плагин выполняет три задачи при сборке:

- Упаковывает ваш код и все зависимости в один исполняемый архив.

- Автоматически находит точку входа с @SpringBootApplication для запуска через java -jar.

- DevTools: обеспечивает быструю перезагрузку приложения при изменении кода.
  
Без этого плагина соберется "тонкий" jar, который не запустится самостоятельно.

4. Какие транзитивные зависимости подключает spring-boot-starter-web?

* Встроенный сервер приложений (Apache Tomcat)

* Spring MVC (Фреймворк веб-приложений)

* JSON-сериализация (Jackson)

* Ядро Spring Boot (Автоконфигурация)

* Микрометр (Наблюдаемость)

<br>

## Задание 2.1

1. Каким образом Spring «знает», что EnglishGreetingService нужно подставить в GreetingController?

- Spring находит @Service на EnglishGreetingService, создает бин и кладет в контекст.

- Spring видит конструктор GreetingController, требующий интерфейс GreetingService.

- Spring находит в контексте единственный бин этого типа и передает его в конструктор.

2. Что произойдёт, если убрать аннотацию @Service с класса EnglishGreetingService?

* Приложение упадет при запуске с NoSuchBeanDefinitionException. 

* Spring не создаст бин для этого класса и не сможет удовлетворить зависимость контроллера.

3. Если создать RussianGreetingService с @Service (без настроек)
   
* Приложение упадет при запуске с NoUniqueBeanDefinitionException. 

* Возникнет конфликт неоднозначности: Spring найдет два бина одного типа и не поймет, какой выбрать.


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

<details open>
    <summary>2.1</summary>
    <br>
    <img src="img_7.png"/>
</details>

<br>

## Задание 2.2

1. Какой способ предпочтительнее и почему?
   
Constructor Injection (внедрение через конструктор). Причины:

- Компилятор не позволит создать объект без зависимости.

- Поле можно сделать final, что гарантирует потокобезопасность.

- В юнит-тестах объект создается через обычный new с передачей мока.

2. Почему поле в Constructor Injection может быть final, а в других нет?

По правилам Java final-поле должно быть инициализировано в момент создания объекта.

- Constructor Injection: зависимость передается прямо в конструктор во время создания объекта.

- Setter Injection: зависимость внедряется позже, когда объект уже создан и его final-поля запечатаны.

- Field Injection: Spring внедряет зависимость после создания объекта через Reflection API. Изменение final-полей на этом этапе нарушает спецификацию Java.

3. Какой способ труднее всего тестировать без Spring-контекста?
   
* Field Injection (внедрение через поле). 

  * Приватное поле без конструктора и сеттера невозможно заполнить в обычном юнит-тесте. 

  * Приходится либо использовать громоздкую Java Reflection, либо поднимать тяжелый Spring-контекст.


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

<details open>
    <summary>2.1</summary>
    <br>
    <img src="img_8.png"/>
</details>

<br>

## Задание 3

<details open>
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

<details open>
    <summary>3 test</summary>
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

## Задание 4

<details open>
    <summary>4</summary>
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

<details open>
    <summary>4 test</summary>
    <br>
    <img src="img_17.png"/>
    <br>
    <img src="img_18.png"/>
    <br>
    <img src="img_19.png"/>
</details>

<br>

## Задание 5

Расскомментируем зависимость Spring Security.

lecture.seven.student.config.SecurityConfig:
```
package lecture.seven.student.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/students").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/students/**").hasRole("ADMIN")
                .requestMatchers("/students/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form.permitAll())
            .logout(logout -> logout.permitAll())
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.withUsername("user")
            .password(encoder.encode("password"))
            .roles("USER")
            .build();

        UserDetails admin = User.withUsername("admin")
            .password(encoder.encode("password"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
```

<details open>
    <summary>5.1</summary>
    <br>
    <img src="img_20.png"/>
    <br>
    <img src="img_21.png"/>
    <br>
    <img src="img_22.png"/>
    <br>
    <img src="img_23.png"/>
    <br>
    <img src="img_24.png"/>
    <br>
    <img src="img_25.png"/>
    <br>
    <img src="img_26.png"/>
    <br>
    <img src="img_27.png"/>
</details>

Добавьте в SecurityConfig:

```
@EnableMethodSecurity   // на уровне класса
```

<details open>
    <summary>5.2</summary>
    <br>
    <img src="img_28.png"/>
</details>

StudentRestController добавьте @PreAuthorize:

```
@GetMapping
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public List<Student> getAll() { ... }

@DeleteMapping("/{id}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Void> delete(@PathVariable Long id) { ... }
```

<details open>
    <summary>5.2</summary>
    <br>
    <img src="img_29.png"/>
    <br>
    <img src="img_30.png"/>
    <br>
    <img src="img_31.png"/>
</details>

1. В чём различие между requestMatchers(...).hasRole(...) в SecurityConfig и @PreAuthorize на методе?

* requestMatchers проверяет URL на входе в приложение (HTTP-уровень). 

* @PreAuthorize проверяет права перед вызовом конкретного Java-метода (уровень кода).

2. Что произойдёт при двух конфликтующих правилах (например, в SecurityConfig разрешено USER, а на методе @PreAuthorize("hasRole('ADMIN')"))?

* Действует принцип двойного замка. Чтобы получить доступ, пользователь должен пройти обе проверки. Конфликт (USER в конфиге, ADMIN на методе) приведет к отказу в доступе (HTTP 403 Forbidden).

<br>

## Задание 6

Расширьте StudentRepository:

```
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContainingIgnoreCase(String namePart);
    List<Student> findBySurnameContainingIgnoreCase(String surnamePart);
    long countByName(String name);
}
```

Добавьте в REST-контроллер эндпоинты, использующие новые методы. Например:

```
package lecture.seven.student.controller;

import lecture.seven.student.model.Student;
import lecture.seven.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (studentService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    // Поиск по имени (содержит подстроку, игнорируя регистр)
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String q) {
        return studentService.findByNameContainingIgnoreCase(q);
    }

    // Поиск по фамилии
    @GetMapping("/search/surname")
    public List<Student> searchBySurname(@RequestParam String q) {
        return studentService.findBySurnameContainingIgnoreCase(q);
    }

    // Подсчёт студентов с определённым именем
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> countByName(@RequestParam String name) {
        long count = studentService.countByName(name);
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("count", count);
        return ResponseEntity.ok(result);
    }
}
```

interface service:
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
    List<Student> findByNameContainingIgnoreCase(String namePart);
    List<Student> findBySurnameContainingIgnoreCase(String surnamePart);
    long countByName(String name);
}
```

class interface:
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

    @Override
    public List<Student> findByNameContainingIgnoreCase(String namePart) {return repository.findByNameContainingIgnoreCase(namePart);}

    @Override
    public List<Student> findBySurnameContainingIgnoreCase(String surnamePart) {return repository.findBySurnameContainingIgnoreCase(surnamePart);}

    @Override
    public long countByName(String name) {return repository.countByName(name);}
}
```

<details open>
    <summary>6.1</summary>
    <br>
    <img src="img_32.png"/>
    <br>
    <img src="img_33.png"/>
    <br>
    <img src="img_34.png"/>
</details>

Добавьте зависимость:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

Создайте логирующий аспект:

```
package lecture.seven.student.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* lecture.seven.student.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(">>> " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* lecture.seven.student.service.*.*(..))",
                    returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println("<<< " + joinPoint.getSignature().getName() + " => " + result);
    }
}
```

<details open>
    <summary>6.2</summary>
    <br>
    <img src="img_35.png"/>
</details>

Добавьте автоматическое создание тестовых данных при старте приложения:

```
package lecture.seven.student.config;

import jakarta.annotation.PostConstruct;
import lecture.seven.student.model.Student;
import lecture.seven.student.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {

    private final StudentRepository repository;

    public AppInitializer(StudentRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            repository.save(new Student("Ali", "Hasan"));
            repository.save(new Student("Fatima", "Kassem"));
            repository.save(new Student("Ivan", "Petrov"));
            repository.save(new Student("Ekaterina", "Sidorova"));
            System.out.println("Созданы тестовые студенты");
        }
    }
}
```

<details open>
    <summary>6.3</summary>
    <br>
    <img src="img_36.png"/>
</details>

Что произойдёт, если убрать @PostConstruct?

* Метод init() не выполнится, так как не участвует в жизненном цикле приложения. 

* Spring создаст бин, но проигнорирует этот метод.

* Тестовые студенты в базу не добавятся.

А если убрать @Component?

* Класс не станет бином, не будет участвовать в жизненном цикле приложения и выпадет из контекста Spring.

* Объект класса не создастся, метод не вызовется, база останется пустой.

<br>

Добавьте интеграционный тест с использованием @WebMvcTest:

```
package lecture.seven.student.controller;

import lecture.seven.student.model.Student;
import lecture.seven.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private StudentService service;

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAll_ok() throws Exception {
        when(service.findAll()).thenReturn(List.of(new Student("A", "B")));
        mvc.perform(get("/api/students"))
           .andExpect(status().isOk());
    }
}
```

<details open>
    <summary>6.4</summary>
    <br>
    <img src="img_37.png"/>
    <br>
    <img src="img_38.png"/>
    <br>
    <img src="img_39.png"/>
    <br>
    <img src="img_40.png"/>
    <br>
    <img src="img_41.png"/>
</details>

(1) что делает @WebMvcTest?

* Изолиция веб-слой.

* Загружает только указанный контроллер и инфраструктуру Spring MVC, игнорируя остальные компоненты приложения для ускорения теста.

(2) зачем нужен @MockitoBean?

* Подменяет зависимость в контексте.

* Создает бин-заглушку и автоматически внедряет её вместо реального `StudentService` прямо в контекст тестируемого контроллера.

(3) что делает @WithMockUser?

* Имитирует реального пользователя.

* Внедряет готовый объект Authentication, предотвращая ошибки доступа 401 и 403 без проведения реальной аутентификации.

<br>

## Задание 7

В этой части вы соедините четыре техники, которые отличают учебный пример от реального сервиса: валидацию, DTO, централизованную обработку ошибок и транзакции.

### Задание 7.1

Добавьте в pom.xml:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

Создайте пакет lecture.seven.student.dto и в нём DTO с валидацией:

```
package lecture.seven.student.dto;

import jakarta.validation.constraints.*;

public record StudentRequest(
    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 100, message = "Имя 2–100 символов")
    String name,

    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 2, max = 100)
    String surname
) {}
```

В StudentRestController замените параметр @RequestBody Student student на @Valid @RequestBody StudentRequest request в POST и PUT.

StudentService:
```
// StudentService.java
package lecture.seven.student.service;

import lecture.seven.student.dto.StudentRequest;
import lecture.seven.student.model.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student save(Student student);
    Student findById(Long id);
    void deleteById(Long id);
    List<Student> findByNameContainingIgnoreCase(String namePart);
    List<Student> findBySurnameContainingIgnoreCase(String surnamePart);
    long countByName(String name);
    Student update(Long id, StudentRequest request);
    Student create(StudentRequest request);
}
```

StudentServiceImpl:
```
package lecture.seven.student.service;

import jakarta.persistence.EntityNotFoundException;
import lecture.seven.student.dto.StudentRequest;
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

    @Override
    public List<Student> findByNameContainingIgnoreCase(String namePart) {
        return repository.findByNameContainingIgnoreCase(namePart);
    }

    @Override
    public List<Student> findBySurnameContainingIgnoreCase(String surnamePart) {
        return repository.findBySurnameContainingIgnoreCase(surnamePart);
    }

    @Override
    public long countByName(String name) {
        return repository.countByName(name);
    }
    @Override
    public Student update(Long id, StudentRequest request) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        student.setName(request.name());
        student.setSurname(request.surname());
        return repository.save(student);
    }
    @Override
    public Student create(StudentRequest request) {
        Student student = new Student(request.name(), request.surname());
        return repository.save(student);
    }
}
```

Методы:
```
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.update(id, request));
    }
    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(request));
    }
```

Проверьте через curl. Должно вернуть HTTP 400 (а после задания 7.3 — структурированную ошибку). POST /api/students. {"name":"","surname":"A"}

<details open>
    <summary>7.1</summary>
    <br>
    <img src="img_42.png"/>
</details>

(1) Что произойдёт, если убрать @Valid с параметра?

* Валидация полей `StudentRequest` полностью отключится.

* Любые некорректные данные пройдут в метод.

(2) Чем @NotNull отличается от @NotBlank для типа String?

* `@NotNull`: запрещает `null`. Разрешает `""` (пусто) и `"   "` (пробелы).

* `@NotBlank`: запрещает `null`, `""` и `"   "`.

(3) Где валидация запускается во времени — до входа в метод контроллера или после?

* До входа в метод контроллера (на этапе конвертации JSON в объект).

* При ошибке генерируется `MethodArgumentNotValidException`.


<br>

### Задание 7.2

Добавьте Response DTO в тот же пакет:

```
package lecture.seven.student.dto;

public record StudentResponse(
    Long id,
    String name,
    String surname
) {}
```

Создайте StudentMapper:

```
package lecture.seven.student.dto;

import lecture.seven.student.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request) {
        Student s = new Student();
        s.setName(request.name());
        s.setSurname(request.surname());
        return s;
    }

    public StudentResponse toResponse(Student s) {
        return new StudentResponse(s.getId(), s.getName(), s.getSurname());
    }
}
```

Перепишите StudentRestController, чтобы он работал только с DTO:

```
@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService service;
    private final StudentMapper mapper;

    public StudentRestController(StudentService service, StudentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return service.findAll().stream()
            .map(mapper::toResponse)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
        Student s = service.findById(id);
        return s != null
            ? ResponseEntity.ok(mapper.toResponse(s))
            : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(
            @Valid @RequestBody StudentRequest request) {
        Student saved = service.save(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Student entity = mapper.toEntity(request);
        entity.setId(id);
        return ResponseEntity.ok(mapper.toResponse(service.save(entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

Запустите приложение, выполните CRUD-запросы и убедитесь, что в JSON-ответах нет полей entity, которых нет в StudentResponse.

<details open>
    <summary>7.2</summary>
    <br>
    <img src="img_43.png"/>
    <br>
    <img src="img_45.png"/>
    <br>
    <img src="img_46.png"/>
    <br>
    <img src="img_47.png"/>
    <br>
    <img src="img_48.png"/>
    <br>
    <img src="img_50.png"/>
</details>

<br>

1. Зачем нужно разделять Entity и DTO?

- Скрывает системные поля (пароли, хеши, id).

- Гибкость: формат API не зависит от структуры таблиц БД.

- Исключает передачу лишних данных по сети.

2. Что произойдёт при возврате Entity с Lazy-связью вне транзакции?
   
* Будет выброшено исключение LazyInitializationException. 

* Jackson попытается прочитать ленивое поле Group вне сессии Hibernate, когда соединение с БД уже закрыто.

3. Какие преимущества даёт record для DTO?

- Автоматически создаются конструктор, геттеры, методы equals(), hashCode() и toString().

- Неизменяемость (Immutability): все поля по умолчанию final, что гарантирует потокобезопасность.

- Синтаксис четко описывает структуру данных без лишнего шаблонного кода (boilerplate).

<br>

### Задание 7.3

Создайте пакет lecture.seven.student.exception и в нём:

```
package lecture.seven.student.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> fieldErrors
    ) {}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(new ErrorResponse(
            Instant.now(), 400, "Bad Request",
            "Validation failed",
            request.getRequestURI(),
            fieldErrors
        ));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            EntityNotFoundException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
            Instant.now(), 404, "Not Found",
            ex.getMessage(), request.getRequestURI(), null
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(
            Exception ex, HttpServletRequest request) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(
            Instant.now(), 500, "Internal Server Error",
            ex.getMessage(), request.getRequestURI(), null
        ));
    }
}
```

Перезапустите приложение и снова попробуйте невалидный POST: /api/students {"name":"","surname":"A"}.

<details open>
    <summary>7.3</summary>
    <br>
    <img src="img_49.png"/>
</details>

Измените StudentService.findById, чтобы при отсутствии бросать EntityNotFoundException:

```
@Override
public Student findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(
            "Student with id " + id + " not found"));
}
```

<details open>
    <summary>7.3</summary>
    <br>
    <img src="img_51.png"/>
</details>

1. Чем @RestControllerAdvice отличается от @ControllerAdvice?

- @ControllerAdvice возвращает имя HTML-шаблона (View). Чтобы вернуть JSON, каждому методу нужна аннотация @ResponseBody.

- @RestControllerAdvice уже включает в себя @ResponseBody. Все методы автоматически сериализуют возвращаемый объект в JSON/XML.

2. В каком порядке Spring выбирает обработчик при совпадении классов исключений?
   
Самый близкий по иерархии класс:

* Если выброшено EntityNotFoundException, Spring выберет обработчик для EntityNotFoundException.class, так как он точнее соответствует типу, чем более общий Exception.class.

3. Почему обработчик для Exception.class — последний, и почему порядок методов в коде не важен?

- Exception.class — "предохранитель" для всех непойманных ошибок. Сначала должны отрабатывать точечные обработчики бизнес-исключений.

- Spring при запуске сканирует аннотации и строит карту соответствия исключений. 

    - При ошибке он вычисляет расстояние по дереву наследования между выброшенным исключением и зарегистрированными классами.

    - Поэтому физическое расположение методов в Java-классе не влияет на выбор.

<br>

### Задание 7.4

Добавьте @Transactional в методы StudentServiceImpl (в интерфейс новый метод тоже добавляем):

```
package lecture.seven.student.service;

import jakarta.persistence.EntityNotFoundException;
import lecture.seven.student.dto.StudentRequest;
import lecture.seven.student.model.Student;
import lecture.seven.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

//    @Override
//    public Student findById(Long id) { return repository.findById(id).orElse(null);}
    @Transactional(readOnly = true)
    @Override
    public Student findById(Long id) { return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));}

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    // Демонстрация: метод, бросающий исключение посередине
    @Transactional
    public void saveTwoOneBroken(Student good, Student bad) {
        repository.save(good);
        if (bad.getName() == null || bad.getName().isBlank()) {
            throw new IllegalStateException("Имя не может быть пустым");
        }
        repository.save(bad);
    }

    @Override
    public List<Student> findByNameContainingIgnoreCase(String namePart) {
        return repository.findByNameContainingIgnoreCase(namePart);
    }

    @Override
    public List<Student> findBySurnameContainingIgnoreCase(String surnamePart) {
        return repository.findBySurnameContainingIgnoreCase(surnamePart);
    }

    @Override
    public long countByName(String name) {
        return repository.countByName(name);
    }
    @Override
    public Student update(Long id, StudentRequest request) {
        Student student = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        student.setName(request.name());
        student.setSurname(request.surname());
        return repository.save(student);
    }
    @Override
    public Student create(StudentRequest request) {
        Student student = new Student(request.name(), request.surname());
        return repository.save(student);
    }
}
```

Вызовите saveTwoOneBroken через временный REST-эндпоинт или CommandLineRunner, передав bad с пустым именем. Проверьте через H2-консоль или GET /api/students: первый студент должен не сохраниться благодаря откату транзакции.

<details open>
    <summary>7.4</summary>
    <br>
    <img src="img_52.png"/>
    <br>
    <img src="img_53.png"/>
    <br>
    <img src="img_54.png"/>
    <br>
    <img src="img_55.png"/>
    <br>
    <img src="img_56.png"/>
    <br>
    <img src="img_57.png"/>
</details>

Изучите подводные камни (self-invocation). Создайте такой класс:

```
@Service
public class TxPitfallService {

    private final StudentRepository repository;
    public TxPitfallService(StudentRepository r) { this.repository = r; }

    public void outerWithoutAnnotation(Student good, Student bad) {
        // ВНИМАНИЕ: this.innerWithTransaction(...) не создаст транзакцию,
        // потому что вызов идёт через this, минуя Spring-прокси
        this.innerWithTransaction(good, bad);
    }

    @Transactional
    public void innerWithTransaction(Student good, Student bad) {
        repository.save(good);
        if (bad.getName().isBlank()) {
            throw new IllegalStateException("Bad student");
        }
        repository.save(bad);
    }
}
```

<details open>
    <summary>7.4</summary>
    <br>
    <img src="img_58.png"/>
    <br>
    <img src="img_59.png"/>
</details>

(1) Где правильно ставить @Transactional — на контроллере, сервисе или репозитории, и почему?

Правильно: На уровне Сервиса (@Service).

* Почему не в Контроллере: Держит соединение с БД слишком долго (включая сериализацию JSON), что исчерпывает пул соединений.

* Почему не в Репозитории: Репозиторий делает атомарные CRUD-операции. Бизнес-логика сервиса требует объединения нескольких операций в одну неделимую транзакцию.

2) Что значит readOnly = true?

Оптимизация для операций чтения.

* Hibernate не отслеживает изменения сущностей и не тратит ресурсы на автоматические SQL-запросы UPDATE при закрытии транзакции.

* Драйвер переводит сессию в режим чтения, снижая количество блокировок в СУБД.

(3) Какие три типичные ловушки @Transactional-прокси?

1. Внутренний вызов (Self-Invocation): Вызов метода внутри того же класса (через this) идет в обход Spring-прокси. Аннотация игнорируется.

2. Не-публичные методы: Аннотация работает только на public-методах. На private/protected прокси-перехватчик не срабатывает.

3. Проглатывание исключений (try-catch): Если поймать исключение внутри метода и не выбросить его наружу, прокси-обертка не узнает об ошибке и сделает commit вместо rollback.

4) При каких исключениях по умолчанию транзакция откатывается, а при каких — нет?

* Откатывается: Непроверяемые (Unchecked) исключения — RuntimeException и его наследники (NullPointerException, IllegalArgumentException), а также Error.

* НЕ откатывается: Проверяемые (Checked) исключения — Exception и его наследники за исключением RuntimeException (IOException, SQLException).

Решение: Для отката на любые ошибки использовать @Transactional(rollbackFor = Exception.class).

<br>

## Контрольные вопросы

1) Сформулируйте принцип IoC своими словами. Чем он отличается от обычного процедурного подхода?

* IoC (Инверсия управления) передает контроль за созданием и связыванием объектов от разработчика фреймворку.

* В процедурном подходе вы сами пишете new Service(), а при IoC контейнер сам создает и внедряет нужный объект.

2) Назовите три способа DI в Spring. Какой из них предпочтителен и почему?

* Способы: Через конструктор, через сеттер, через поле (@Autowired).

* Предпочтительный: Через конструктор. Гарантирует неизменяемость зависимостей (final), защищает от NullPointerException и упрощает написание модульных тестов без поднятия контекста Spring.

3) Чем отличаются @Component, @Service, @Repository, @Controller?

* Технически = одинаковые бины, но с разной семантической ролью для разработчика и Spring. 

* @Component — базовый бин общего назначения, @Service — для бизнес-логики, @Repository — для работы с БД, @Controller — для обработки HTTP-запросов и возврата HTML-страниц.

4) Чем @RestController отличается от @Controller?

* @RestController объединяет в себе @Controller и @ResponseBody.

* Методы @Controller возвращают имена HTML-шаблонов, а @RestController автоматически сериализует возвращаемые объекты напрямую в JSON или XML тела HTTP-ответа.

5) Что делает аннотация @SpringBootApplication?

* Объединяет три аннотации: @SpringBootConfiguration (помечает класс источником конфигурации), @EnableAutoConfiguration (включает автонастройку зависимостей на основе classpath) и @ComponentScan (запускает сканирование и поиск бинов в текущем пакете и его подпакетах).

6) Что такое starter-модуль? Приведите 3 примера и опишите, что они подключают.

* Стартер — это готовый набор скомпонованных зависимостей для быстрой интеграции технологии без ручной настройки версий.

1. spring-boot-starter-web: подключает Tomcat, Spring MVC и Jackson для работы с JSON.

2. spring-boot-starter-data-jpa: подключает Hibernate, JDBC-драйверы и ядро Spring Data.

3. spring-boot-starter-security: подключает модули авторизации/аутентификации и дефолтные фильтры безопасности.

7) Что такое ApplicationContext и чем он отличается от BeanFactory?

* Это IoC-контейнер Spring для управления жизненным циклом бинов. 

* BeanFactory предоставляет только базовый функционал и ленивую инициализацию бинов по запросу.

* ApplicationContext расширяет его, добавляя синглтон-инициализацию при старте, интеграцию с AOP, обработку событий, ...

8) Какие scope бинов вы знаете и в чём их различие?

* singleton: один экземпляр на весь IoC-контейнер (по умолчанию).

* prototype: новый экземпляр при каждом запросе или внедрении бина.

* request: один экземпляр на каждый HTTP-запрос (только веб).

* session: один экземпляр на одну HTTP-сессию пользователя (только веб).

* application: один экземпляр на жизненный цикл ServletContext (только веб).

9) Объясните принципы AOP: Aspect, JoinPoint, Pointcut, Advice.

* Aspect: модуль со сквозной логикой (например, логирование), применяемый независимо к разным частям системы.

* JoinPoint: точка в программе, где может быть применен аспект (в Spring это всегда вызов метода).

* Pointcut: выражение-фильтр, определяющее, к каким конкретно JoinPoint нужно применить аспект.

* Advice: сам код (действие), который выполняется до, после или вместо целевого метода в точке Pointcut.

10) Что делает @PathVariable и чем отличается от @RequestParam?

* Обе извлекают данные из URL.

* @PathVariable достает значение прямо из пути (например, /users/{id} -> /users/5).

* @RequestParam вытягивает именованные query-параметры из строки после знака вопроса (например, /users?page=2).

11) Объясните цепочку Controller → Service → Repository. Зачем нужны эти слои?

* Цепочка: Controller принимает HTTP-запрос, валидирует его и передает в Service.

* Service выполняет бизнес-логику и обращается к Repository для чтения/записи данных в БД.

* Зачем нужны: Паттерн разделения ответственности (Separation of Concerns).

* Позволяет изолированно изменять, тестировать и масштабировать веб-слой, логику приложения и логику хранения данных.

12) Что такое JpaRepository? Как Spring Data сам генерирует реализацию методов вида findByNameContainingIgnoreCase?

* JpaRepository — интерфейс Spring Data со встроенными CRUD-методами и поддержкой пагинации.

* Реализация генерируется динамически при старте приложения: Spring парсит имя метода по ключевым словам по правилам Property Expressions, с помощью Java Dynamic Proxy строит SQL-запрос и подставляет параметры.

13) Как Thymeleaf получает данные от контроллера и подставляет их в шаблон?

* Контроллер принимает объект Model и наполняет его данными через model.addAttribute("key", value), после чего возвращает строку с именем HTML-файла.

* Spring передает Model в движок Thymeleaf, который парсит HTML на сервере и подставляет значения из модели вместо кастомных атрибутов (например, th:text).

14) Что такое SecurityFilterChain в Spring Security?

* Упорядоченная цепочка сервлетных фильтров, через которую проходит каждый входящий HTTP-запрос до попадания в контроллер.

* Фильтры отвечают за проверку токенов, аутентификацию, авторизацию и защиту от атак вроде CSRF.

15) Зачем нужен PasswordEncoder и почему пароли нельзя хранить в открытом виде?

* Нужен для безопасного необратимого хеширования паролей (с добавлением соли) перед сохранением в БД.

* Пароли в открытом виде хранить нельзя, так как при утечке базы данных злоумышленники мгновенно скомпрометируют аккаунты пользователей.

16) Опишите алгоритм работы JWT-аутентификации.

1. Клиент отправляет учетные данные на сервер.
2. Сервер проверяет их и генерирует строку-токен (JWT), подписанную секретным ключом.
3. Токен возвращается клиенту, который сохраняет его локально.
4. При каждом следующем запросе клиент передает токен в заголовке Authorization: Bearer <token>.
5. Сервер проверяет валидность подписи токена своим ключом и, если всё ок, пускает пользователя.

17) В чём преимущество JWT перед стандартной session-based аутентификацией для REST API?

* JWT не требует состояния (Stateless).

* Серверу не нужно хранить сессии в памяти или Redis и синхронизировать их. 

* Вся информация о пользователе уже зашита в токен, что делает систему легко масштабируемой.

18) Зачем нужна аннотация @Valid на параметре контроллера? Что случится, если её убрать?

* Запускает автоматическую проверку полей входящего DTO на основе ограничений Jakarta Bean Validation (@NotNull, @Email и др.).

* Если её убрать, валидация не сработает, и в бизнес-логику пройдут некорректные или пустые данные.

20) Перечислите 5 проблем, которые возникают при возврате @Entity напрямую из REST-контроллера.

1. Ошибка LazyInitializationException при сериализации Jackson-ом незагруженных связей вне транзакции.
2. Бесконечная рекурсия (циклические зависимости) при сериализации двунаправленных связей (например, One-to-Many).
3. Утечка конфиденциальных полей (пароли, хэши, системные ID) клиенту.
4. Избыточный сетевой трафик из-за передачи ненужных для фронтенда данных.
5. Жесткое связывание (сильное зацепление) API и схемы БД: изменение таблицы автоматически ломает контракт клиента.

21) Чем @RestControllerAdvice отличается от @ControllerAdvice? Когда какой использовать?

* @RestControllerAdvice автоматически добавляет @ResponseBody к методам, возвращая ошибки в виде JSON/XML.

* @ControllerAdvice используется, если приложение возвращает HTML-страницы и при ошибке нужно перенаправить пользователя на кастомную веб-страницу.

22) Почему @Transactional рекомендуется ставить на сервисном слое, а не на контроллере?

* Контроллер должен только маршрутизировать запросы.

* Транзакция на контроллере держит соединение с БД открытым слишком долго (захватывая время на сериализацию JSON и сетевую передачу), что приводит к быстрому исчерпанию пула соединений (Connection Pool Starvation).

23) Что такое self-invocation в контексте @Transactional и почему это проблема?

* Вызов транзакционного метода из другого метода внутри того же самого класса. 
* 
* Вызов идет напрямую через ссылку this в обход Spring-прокси, из-за чего транзакционный аспект полностью игнорируется и транзакция не открывается.

24) Откатится ли транзакция по умолчанию, если метод бросает checked-исключение (например, IOException)?

* Нет.

* По умолчанию Spring откатывает транзакции только при выбросе непроверяемых (Unchecked) исключений (RuntimeException и его наследники) или ошибок (Error).

* Для отката проверяемых исключений нужно явно указывать @Transactional(rollbackFor = Exception.class).
