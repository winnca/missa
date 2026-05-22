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
    <br>
    <img src="img_7.png"/>
    <br>
    <img src="img_8.png"/>
    <br>
    <img src="img_9.png"/>
    <br>
    <img src="img_10.png"/>
</details>

<br>

### Задание 1.2

1. Сколько прямых зависимостей?

* 2: com.h2.database, org.hibernate.orm:hibernate-core:6.4.0.Final.

2. Сколько транзитивных зависимостей добавляет Hibernate?

* 13 транзитивных зависимостей (все, что находятся под hibernate-core в дереве).

3. Какие зависимости Hibernate потянул за собой?

* уровень 1 (прямые зависимости Hibernate): 

    * jakarta.persistence:jakarta.persistence-api:3.1.0 - Jakarta Persistence API 

    * jakarta.transaction:jakarta.transaction-api:2.0.1 - Jakarta Transaction API 

    * org.jboss.logging:jboss-logging:3.5.0.Final - библиотека логирования 

    * org.hibernate.common:hibernate-commons-annotations:6.0.6.Final - общие аннотации Hibernate 

    * io.smallrye:jandex:3.1.2 - индексация аннотаций

    * com.fasterxml:classmate:1.5.1 - анализ классов и типов 

    * net.bytebuddy:byte-buddy:1.14.7 - библиотека для генерации байт-кода 

    * jakarta.xml.bind:jakarta.xml.bind-api:4.0.0 - Jakarta XML Binding API 

    * org.glassfish.jaxb:jaxb-runtime:4.0.2 - реализация JAXB 

    * jakarta.inject:jakarta.inject-api:2.0.1 - Jakarta Inject API 

    * org.antlr:antlr4-runtime:4.13.0 - парсер для HQL запросов

+ Уровень 2 (зависимости зависимостей):

    + jakarta.activation:jakarta.activation-api:2.1.0 (через jakarta.xml.bind-api)

    + org.glassfish.jaxb:jaxb-core:4.0.2 (через jaxb-runtime)
  
- Уровень 3 (еще глубже):

    - org.eclipse.angus:angus-activation:2.0.0 (через jaxb-core)

    - org.glassfish.jaxb:txw2:4.0.2 (через jaxb-core)

    - com.sun.istack:istack-commons-runtime:4.1.1 (через jaxb-core)

<details>
    <summary>1.2</summary>
    <br>
    <img src="img_11.png"/>
</details>

<br>
<br>

### Задание 2.1

```
package com.movies;

import java.sql.*;
import java.util.*;

public class MovieJDBC {

    private static final String URL = "jdbc:h2:mem:moviedb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Подключение успешно!");

            dropAndCreateTable(conn);
            insertMovies(conn);
            updateMovie(conn, 1, "Матрица: Перезагрузка", "Фантастика", 2003);
            deleteMovie(conn, 2);

            System.out.println("\n=== Все фильмы ===");
            printAllMovies(conn);

            System.out.println("\n=== Фильмы после 2000 года ===");
            findByYear(conn, 2000);

            System.out.println("\n=== Фантастика ===");
            findByGenre(conn, "Фантастика");

            System.out.println("\n=== Поиск 'начало' ===");
            findByTitle(conn, "начало");
        }
    }

    static void dropAndCreateTable(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS movies");
            stmt.execute("""
                CREATE TABLE movies (
                    id    INT AUTO_INCREMENT PRIMARY KEY,
                    title VARCHAR(200) NOT NULL,
                    genre VARCHAR(100),
                    release_year  INT
                )
            """);
            System.out.println("Таблица movies создана");
        }
    }

    static void insertMovies(Connection conn) throws SQLException {
        String sql = "INSERT INTO movies (title, genre, release_year) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            Object[][] movies = {
                {"Матрица", "Фантастика", 1999},
                {"Начало", "Фантастика", 2010},
                {"Лев", "Драма", 2016},
                {"Джокер", "Триллер", 2019}
            };
            for (Object[] movie : movies) {
                pstmt.setString(1, (String) movie[0]);
                pstmt.setString(2, (String) movie[1]);
                pstmt.setInt(3, (Integer) movie[2]);
                pstmt.executeUpdate();
                System.out.println("Добавлен: " + movie[0]);
            }
        }
    }

    static void updateMovie(Connection conn, int id, String title, String genre, int releaseYear)
            throws SQLException {
        String sql = "UPDATE movies SET title=?, genre=?, release_year=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, genre);
            pstmt.setInt(3, releaseYear);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        }
        System.out.println("Обновлён фильм id=" + id);
    }

    static void deleteMovie(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        System.out.println("Удалён фильм id=" + id);
    }

    static void printAllMovies(Connection conn) throws SQLException {
        String sql = "SELECT * FROM movies ORDER BY id";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("id=%d | %-30s | %-15s | %d%n",
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getInt("release_year"));
            }
        }
    }

    static void findByYear(Connection conn, int minYear) throws SQLException {
        String sql = "SELECT * FROM movies WHERE release_year > ? ORDER BY release_year";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, minYear);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("id=%d | %-30s | %-15s | %d%n",
                        rs.getInt("id"), rs.getString("title"),
                        rs.getString("genre"), rs.getInt("release_year"));
                }
            }
        }
    }

    static void findByGenre(Connection conn, String genre) throws SQLException {
        String sql = "SELECT * FROM movies WHERE LOWER(genre) = LOWER(?) ORDER BY release_year";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genre);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("id=%d | %-30s | %-15s | %d%n",
                        rs.getInt("id"), rs.getString("title"),
                        rs.getString("genre"), rs.getInt("release_year"));
                }
            }
        }
    }

    static void findByTitle(Connection conn, String titlePart) throws SQLException {
        String sql = "SELECT * FROM movies WHERE LOWER(title) LIKE LOWER(?) ORDER BY id";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + titlePart + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("id=%d | %-30s | %-15s | %d%n",
                        rs.getInt("id"), rs.getString("title"),
                        rs.getString("genre"), rs.getInt("release_year"));
                }
            }
        }
    }
}
```

Объясните: 

(1) почему в findByTitle используется LIKE с %? 

* LIKE с % используется для поиска по подстроке (частичному совпадению). 

    * % — wildcard, заменяет любую последовательность символов

    * "%" + titlePart + "%" — ищет, где titlePart встречается в любом месте названия

Пример: поиск "начало" найдёт: "Начало", "Предначало", "Начало фильма"

* Без % — только точное совпадение (с учётом LOWER — регистронезависимое)

* В нашем случае поиск не дал результатов, потому что фильм "Начало" был удалён, но если бы он существовал — мы бы его нашли.

(2) что такое PreparedStatement и чем он безопаснее Statement?

* PreparedStatement — предкомпилированный SQL-запрос с параметрами (?). 

* Защищён от SQL-инъекций (в отличик от Statement), компилируется 1 раз (компилируется при каждом вызове), используется парамеры и сеттеры (в Statement конкатенация строкс ручным форматированием).

<details>
    <summary>2.1</summary>
    <br>
    <img src="img_12.png"/>
    <br>
    <img src="img_13.png"/>
</details>

<br>

### Задание 2.2

Реализуйте DAO-паттерн для работы с таблицей movies:

* Класс Movie: поля id (Integer), title (String), genre (String), year (Integer); конструкторы (без аргументов и с title, genre, year); геттеры и сеттеры для всех полей; toString().

* Интерфейс MovieDAO: методы createTable(), dropTable(), insert(Movie), delete(int id), updateTitle(int id, String newTitle), findById(int id) → Optional<Movie>, findAll() → List<Movie>, findByTitle(String part), findByGenre(String genre), findByYear(int year).

* Класс MovieDAOImpl implements MovieDAO: принимает Connection в конструкторе. Все методы используют PreparedStatement. Метод insert получает сгенерированный id через getGeneratedKeys(). Вспомогательный метод mapRow(ResultSet rs) → Movie.

* Класс DAOTest: создайте H2 in-memory соединение (jdbc:h2:mem:movietest;DB_CLOSE_DELAY=-1), вставьте 4 фильма, обновите заголовок, удалите один, выведите все, найдите по id, жанру, году, части названия


```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.movies</groupId>
    <artifactId>movie-app</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                    <mainClass>com.movies.task_2_2.DAOTest</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

```
package com.movies;

public class Movie {
    private Integer id;
    private String title;
    private String genre;
    private Integer year;

    // Конструктор без аргументов
    public Movie() {
    }

    // Конструктор с title, genre, year
    public Movie(String title, String genre, Integer year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }
}
```

```
package com.movies;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
    void createTable();
    void dropTable();
    Movie insert(Movie movie);
    int delete(int id);
    int updateTitle(int id, String newTitle);
    Optional<Movie> findById(int id);
    List<Movie> findAll();
    List<Movie> findByTitle(String part);
    List<Movie> findByGenre(String genre);
    List<Movie> findByYear(int year);
}
```

```
package com.movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO {
    private final Connection connection;

    public MovieDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS movies (
                id    INT AUTO_INCREMENT PRIMARY KEY,
                title VARCHAR(200) NOT NULL,
                genre VARCHAR(100),
                release_year  INT
            )
        """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица movies создана");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы", e);
        }
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE IF EXISTS movies";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица movies удалена");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении таблицы", e);
        }
    }

    @Override
    public Movie insert(Movie movie) {
        String sql = "INSERT INTO movies (title, genre, release_year) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, movie.getTitle());
            pstmt.setString(2, movie.getGenre());
            pstmt.setInt(3, movie.getYear());
            pstmt.executeUpdate();

            // Получаем сгенерированный id
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movie.setId(generatedKeys.getInt(1));
                }
            }
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при вставке фильма", e);
        }
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении фильма", e);
        }
    }

    @Override
    public int updateTitle(int id, String newTitle) {
        String sql = "UPDATE movies SET title = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newTitle);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении названия", e);
        }
    }

    @Override
    public Optional<Movie> findById(int id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске фильма по id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies ORDER BY id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                movies.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении всех фильмов", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findByTitle(String part) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE LOWER(title) LIKE LOWER(?) ORDER BY id";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + part + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    movies.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске по названию", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE LOWER(genre) = LOWER(?) ORDER BY release_year";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, genre);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    movies.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске по жанру", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findByYear(int year) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE release_year = ? ORDER BY title";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, year);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    movies.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске по году", e);
        }
        return movies;
    }

    // Вспомогательный метод для маппинга ResultSet в Movie
    private Movie mapRow(ResultSet rs) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setTitle(rs.getString("title"));
        movie.setGenre(rs.getString("genre"));
        movie.setYear(rs.getInt("release_year"));
        return movie;
    }
}
```

```
package com.movies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DAOTest {
    private static final String URL = "jdbc:h2:mem:movietest;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("=== Тестирование DAO-паттерна ===\n");

            MovieDAO movieDAO = new MovieDAOImpl(connection);

            // 1. Создаём таблицу
            movieDAO.createTable();
            System.out.println();

            // 2. Вставляем 4 фильма
            System.out.println("--- Вставка фильмов ---");
            Movie m1 = movieDAO.insert(new Movie("Матрица", "Фантастика", 1999));
            Movie m2 = movieDAO.insert(new Movie("Начало", "Фантастика", 2010));
            Movie m3 = movieDAO.insert(new Movie("Лев", "Драма", 2016));
            Movie m4 = movieDAO.insert(new Movie("Джокер", "Триллер", 2019));

            System.out.println("Добавлены фильмы с id: " + 
                m1.getId() + ", " + m2.getId() + ", " + m3.getId() + ", " + m4.getId());
            System.out.println();

            // 3. Обновляем заголовок
            System.out.println("--- Обновление заголовка ---");
            int updated = movieDAO.updateTitle(m1.getId(), "Матрица: Перезагрузка");
            System.out.println("Обновлено записей: " + updated);
            System.out.println();

            // 4. Удаляем один фильм
            System.out.println("--- Удаление фильма ---");
            int deleted = movieDAO.delete(m2.getId());
            System.out.println("Удалено записей: " + deleted + " (id=" + m2.getId() + ")");
            System.out.println();

            // 5. Выводим все фильмы
            System.out.println("--- Все фильмы ---");
            List<Movie> allMovies = movieDAO.findAll();
            allMovies.forEach(System.out::println);
            System.out.println();

            // 6. Поиск по id
            System.out.println("--- Поиск по id=1 ---");
            Optional<Movie> movie = movieDAO.findById(1);
            movie.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Фильм не найден")
            );
            System.out.println();

            // 7. Поиск по жанру
            System.out.println("--- Поиск по жанру 'Фантастика' ---");
            List<Movie> sciFiMovies = movieDAO.findByGenre("Фантастика");
            sciFiMovies.forEach(System.out::println);
            System.out.println();

            // 8. Поиск по году
            System.out.println("--- Поиск по году 2019 ---");
            List<Movie> movies2019 = movieDAO.findByYear(2019);
            movies2019.forEach(System.out::println);
            System.out.println();

            // 9. Поиск по части названия
            System.out.println("--- Поиск по 'ев' в названии ---");
            List<Movie> moviesWithTitle = movieDAO.findByTitle("ев");
            moviesWithTitle.forEach(System.out::println);
            System.out.println();

            System.out.println("=== Тестирование завершено ===");

        } catch (SQLException e) {
            System.err.println("Ошибка подключения к БД: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

<details>
    <summary>2.2</summary>
    <br>
    <img src="img_14.png"/>
    <br>
    <img src="img_15.png"/>
    <br>
    <img src="img_16.png"/>
    <br>
    <img src="img_17.png"/>
</details>

<br>
<br>

<br>

### Задание 3.1

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.movies</groupId>
    <artifactId>missa_maven</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.hibernate.orm</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>6.4.0.Final</version>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                    <mainClass>com.movies.task_2_2.DAOTest</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

<br>

### Задание 3.2

`@Entity`:

* Назначение = указывает Hibernate, что этот класс является сущностью (entity), которая должна быть сопоставлена с таблицей в базе данных.

* Что делает: Каждый объект этого класса соответствует одной строке в таблице.

* Обязательна: Да, без неё Hibernate не будет управлять классом.

`@Table(name = "movies")`:

* Назначение: Задаёт имя таблицы в БД, с которой связана сущность.

* Что делает: Указывает, что сущность Movie будет храниться в таблице movies.

* Необязательна: Если убрать, Hibernate будет использовать имя класса как имя таблицы (Movie → movie).

* Что произойдёт если убрать: Таблица будет называться movie (в нижнем регистре) вместо movies

`@Id`:

* Назначение: Указывает первичный ключ (PRIMARY KEY) сущности.

* Что делает: Помечает поле как уникальный идентификатор записи.

* Обязательна: Да, каждая сущность должна иметь первичный ключ.

* Что произойдёт если убрать: Hibernate выбросит исключение AnnotationException: No identifier specified for entity.

`@GeneratedValue(strategy = GenerationType.IDENTITY)`:

* Назначение: Определяет стратегию автоматической генерации значений первичного ключа.

* GenerationType.IDENTITY: Полагается на автоинкремент в БД (AUTO_INCREMENT в H2/MySQL).

* Что делает: База данных автоматически присваивает следующий id при вставке.

* Необязательна: Если убрать, нужно будет вручную задавать id при создании объектов.

Другие стратегии:

* AUTO — Hibernate выбирает сам

* SEQUENCE — использует sequence (PostgreSQL, Oracle)

* TABLE — отдельная таблица для генерации id

Что произойдёт если убрать: Нужно будет вручную устанавливать id перед сохранением, иначе будет null и может возникнуть ошибка.

`@Column(name = "title", nullable = false, length = 200)`

* Назначение: Настраивает сопоставление поля с колонкой таблицы.

Атрибуты:

* name = "title" — имя колонки в БД

* nullable = false — колонка NOT NULL (обязательное поле)

* length = 200 — максимальная длина строки (VARCHAR(200))

Необязательна: Если убрать всю аннотацию, Hibernate использует настройки по умолчанию (имя поля = имя колонки, nullable = true, length = 255).

**Что произойдёт если убрать nullable = false?**

* Схема БД = Разрешает NULL

* Валидация Hibernate = Не проверяет

* Целостность данных = Допускает отсутствие данных

* Ошибки = На уровне SQL или позже

Вывод: 

* @Column(nullable = false) обеспечивает целостность данных как на уровне БД (через DDL constraint), 

* так и на уровне приложения (через валидацию Hibernate перед выполнением SQL). 

* Без неё приложение становится уязвимым для ошибок, связанных с отсутствием обязательных данных.

```
package com.movies.practice_6.task_3_1_2;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "genre", length = 100)
    private String genre;

    @Column(name = "year")
    private Integer year;

    public Movie() {}

    public Movie(String title, String genre, int year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    @Override
    public String toString() {
        return String.format("Movie{id=%d, title='%s', genre='%s', year=%d}",
                id, title, genre, year);
    }
}
```

<details>
    <summary>3.2</summary>
    <br>
    <img src="img_18.png"/>
</details>

<br>

### Задание 3.3

Изучите и запустите программу работы с Hibernate. 

Убедитесь, что все операции работают корректно. Объясните: 

(1) чем HQL отличается от SQL? 

(2) когда предпочтительнее Criteria API вместо HQL? 

(3) что такое сессия (Session) в Hibernate?

<details>
    <summary>3.3</summary>
    <br>
    <img src="img_19.png"/>
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
</details>
