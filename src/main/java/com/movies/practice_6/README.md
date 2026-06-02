### [Тест]

<details open>
    <summary>test</summary>
    <br>
    <img src="img_61.png"/>
    <br>
    <img src="img_29.png"/>
    <br>
    <img src="img_30.png"/>
    <br>
    <img src="img_31.png"/>
    <br>
    <img src="img_32.png"/>
    <br>
    <img src="img_33.png"/>
    <br>
    <img src="img_34.png"/>
    <br>
    <img src="img_35.png"/>
    <br>
    <img src="img_36.png"/>
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
    <br>
    <img src="img_42.png"/>
    <br>
    <img src="img_43.png"/>
    <br>
    <img src="img_44.png"/>
    <br>
    <img src="img_45.png"/>
    <br>
    <img src="img_46.png"/>
    <br>
    <img src="img_47.png"/>
    <br>
    <img src="img_48.png"/>
    <br>
    <img src="img_49.png"/>
    <br>
    <img src="img_50.png"/>
    <br>
    <img src="img_51.png"/>
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
    <br>
    <img src="img_58.png"/>
    <br>
    <img src="img_59.png"/>
    <br>
    <img src="img_60.png"/>
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

<details open>
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

<details open>
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

<details open>
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

Добавьте в pom.xml зависимости и создайте src/main/resources/hibernate.cfg.xml:

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

Изучите Entity-класс Movie. Объясните назначение аннотаций: @Entity, @Table, @Id, @GeneratedValue, @Column. Что произойдёт если убрать @Column(nullable = false) — на уровне кода или базы данных?

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

<details open>
    <summary>3.2</summary>
    <br>
    <img src="img_18.png"/>
</details>

<br>

### Задание 3.3

Изучите и запустите программу работы с Hibernate. 

Убедитесь, что все операции работают корректно. Объясните: 

(1) чем HQL отличается от SQL? 

* HQL оперирует именами Java-классов (сущностей) и их свойствами.

* SQL работает напрямую с таблицами и колонками в базе данных.

* HQL автоматически переводится Hibernate в SQL с учетом синтаксиса конкретной СУБД.

(2) когда предпочтительнее Criteria API вместо HQL?

* Criteria API предпочтительнее, когда нужно строить динамические запросы в зависимости от множества условий (например, сложные фильтры в поиске). 

* Собирает запрос через Java-код, что исключает опечатки в строках и проверяется на этапе компиляции (Type-safety).

(3) что такое сессия (Session) в Hibernate?

* Сессия (Session) — кратковременный интерфейс взаимодействия между Java-приложением и базой данных = обертку над JDBC-соединением, управляет транзакциями и кэшем первого уровня (Identity Map) для отслеживания состояний сущностей.

<details open>
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

<br>

### Задание 4.1

Реализуйте перевод средств между банковскими счетами с управлением транзакциями JDBC:

* Создайте таблицу accounts (id INT PRIMARY KEY, owner VARCHAR(100), balance DECIMAL(10,2)).

* Добавьте несколько счетов через INSERT.

* Реализуйте метод transfer(Connection conn, int fromId, int toId, double amount):

* Установите conn.setAutoCommit(false).

* Проверьте, что на счёте fromId достаточно средств.

* Если средств хватает: спишите с fromId, зачислите на toId, вызовите conn.commit().

* Если средств не хватает или произошла ошибка: вызовите conn.rollback().

* В блоке finally верните conn.setAutoCommit(true).

Протестируйте: корректный перевод и иначе.

```
package com.movies.task_4_1;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private int id;

    @Column(nullable = false, length = 100)
    private String owner;

    // Рекомендуется использовать BigDecimal для денежных операций
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    public Account() {}

    public Account(int id, String owner, BigDecimal balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }

    public int getId() { return id; }
    public String getOwner() { return owner; }
    public BigDecimal getBalance() { return balance; }

    public void setBalance(BigDecimal balance) { this.balance = balance; }

    @Override
    public String toString() {
        return String.format("ID: %d | Владелец: %s | Баланс: %s", id, owner, balance);
    }
}
```

```
package com.movies.task_4_1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

public class DAOTest {
    private static SessionFactory sessionFactory;
    
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Account.class);
        sessionFactory = cfg.buildSessionFactory();
        
        initData();

        System.out.println("--- Исходное состояние ---");
        printBalances();
        
        System.out.println("\n--- Тест 1: Перевод 100.00 от Стефан к Бонни ---");
        transfer(1, 2, new BigDecimal("300.00"));
        printBalances();
        
        System.out.println("\n--- Тест 2: Перевод 900.00 от от Стефан к Бонни (баланс всего 700) ---");
        transfer(1, 2, new BigDecimal("800.00"));
        printBalances();
        
        sessionFactory.close();
    }
    public static void transfer(int fromId, int toId, BigDecimal amount) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            Account fromAccount = session.get(Account.class, fromId);
            Account toAccount = session.get(Account.class, toId);

            if (fromAccount == null || toAccount == null) {
                throw new IllegalArgumentException("Один из счетов не найден в базе данных!");
            }
            if (fromAccount.getBalance().compareTo(amount) < 0) {
                throw new IllegalStateException("Пополни счёт" + fromId);
            }
            
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));
            
            session.merge(fromAccount);
            session.merge(toAccount);

            tx.commit();
            System.out.println("Транзакция успешно завершена.");

        } catch (Exception e) {
            System.err.println("Ошибка транзакции: " + e.getMessage() + ". Выполнен Rollback.");
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
    private static void initData() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(new Account(1, "Стефан", new BigDecimal("700.00")));
            session.persist(new Account(2, "Бонни", new BigDecimal("500.00")));
            tx.commit();
        }
    }
    private static void printBalances() {
        try (Session session = sessionFactory.openSession()) {
            session.createQuery("from Account", Account.class).getResultList().forEach(System.out::println);
        }
    }
}
```

<details open>
    <summary>4.1</summary>
    <br>
    <img src="img_25.png"/>
    <br>
    <img src="img_26.png"/>
</details>

<br>

### Задание 4.2

Реализуйте метод поиска с пагинацией: findPage(SessionFactory sf, int pageNumber, int pageSize), pageNumber начинается с 1. Выведите страницу 1 (3 фильма), страницу 2 (3 фильма)

```
package com.movies.task_4_2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    private int id;
    @Column(nullable = false, length = 150)
    private String title;
    public Movie() {}
    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    @Override
    public String toString() {return String.format("ID: %2d | Название: %s", id, title);}
}
```

```
package com.movies.task_4_2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class MovieTest {

    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Movie.class);

        try (SessionFactory sf = cfg.buildSessionFactory()) {

            initMoviesData(sf);

            System.out.println("Страница 1 = размер 3");
            List<Movie> page1 = findPage(sf, 1, 3);
            page1.forEach(System.out::println);

            System.out.println("\nСтраница 2 = размер 3");
            List<Movie> page2 = findPage(sf, 2, 3);
            page2.forEach(System.out::println);
        }
    }
    static List<Movie> findPage(SessionFactory sf, int pageNumber, int pageSize) {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Movie ORDER BY id", Movie.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
        }
    }
    private static void initMoviesData(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            for (int i = 1; i <= 7; i++) {
                session.persist(new Movie(i, "Фильм №" + i));
            }
            tx.commit();
        }
    }
}
```

<details open>
    <summary>4.2</summary>
    <br>
    <img src="img_27.png"/>
</details>

<br>

### Задание 4.3

Изучите и запустите агрегационные HQL-запросы. Объясните: 

(1) что возвращает createQuery с SELECT genre, COUNT(*)?

(2) чем uniqueResult() отличается от .list()?

1) createQuery с SELECT genre, COUNT(*) возвращает список массивов объектов — List<Object[]>. 

  * Каждый массив Object[] представляет строку результата: на индексе 0 лежит строка (String жанра), на индексе 1 лежит число (Long количества).

2) Метод .list() возвращает коллекцию (даже пустую), если строк может быть много. 

  * Метод uniqueResult() возвращает один конкретный объект или null, если данных нет. Если строк вернется больше одной, он выбросит исключение NonUniqueResultException.

```
package com.movies.task_4_3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    private int id;
    private String title;
    private String genre;
    @Column(name = "release_year")
    private int year;

    public Movie() {}

    public Movie(int id, String title, String genre, int year) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
}

```

```
package com.movies.task_4_3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class DAOTest {

    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Movie.class);

        try (SessionFactory sf = cfg.buildSessionFactory()) {
            initData(sf);
            runAggregationQueries(sf);
        }
    }

    static void runAggregationQueries(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            System.out.println("Количество фильмов по жанрам");
            List<Object[]> byGenre = session.createQuery("SELECT genre, COUNT(*) FROM Movie GROUP BY genre", Object[].class).list();
            byGenre.forEach(row -> System.out.println(row[0] + ": " + row[1]));

            System.out.println("\nСредний год выхода");
            Double avgYear = session.createQuery("SELECT AVG(year) FROM Movie", Double.class).uniqueResult();
            System.out.printf("Средний год: %.1f%n", avgYear);

            System.out.println("\nНовейший фильм каждого жанра");
            List<Object[]> newestByGenre = session.createQuery("SELECT genre, MAX(year) FROM Movie GROUP BY genre", Object[].class).list();
            newestByGenre.forEach(row -> System.out.println(row[0] + ": " + row[1]));
        }
    }
    private static void initData(SessionFactory sf) {
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(new Movie(1, "Матрица", "Sci-Fi", 2000));
            session.persist(new Movie(2, "Интерстеллар", "Sci-Fi", 2014));
            session.persist(new Movie(3, "Знакомьтесь, Джо Блэк", "Drama", 1998));
            session.persist(new Movie(4, "С любовью, Рози", "Drama", 2013));
            session.persist(new Movie(5, "Полицейский с рублёвки", "Comedy", 2015));
            tx.commit();
        }
    }
}
```

<details open>
    <summary>4.3</summary>
    <br>
    <img src="img_28.png"/>
</details>

<br>

### Контрольные вопросы

1. Что такое GAV-координаты в Maven? Для чего они используются?
   
GAV — это уникальный идентификатор артефакта (библиотеки), состоящий из трех частей: 

* GroupId (имя организации/пакета), 

* ArtifactId (название проекта) и Version (версия). 

* Используются Maven для однозначного поиска, скачивания и подключения нужной библиотеки из центрального репозитория (Maven Central).

2. В чём разница между <scope>compile</scope> и <scope>test</scope>?
   
* `compile` — область видимости по умолчанию; зависимость доступна на всех этапах сборки (компиляция, тестирование, запуск) и упаковывается в итоговый JAR/WAR. 

* `test` — зависимость доступна только во время компиляции и запуска тестов (например, JUnit); она не включается в финальную сборку.

3. Что такое транзитивные зависимости? Может ли это стать проблемой?
   
* Зависимости ваших зависимостей (библиотеки, которые нужны подключаемой вами библиотеке). Они скачиваются автоматически. 

* Проблема при конфликте версий (Jar Hell), когда две разные библиотеки требуют одну и ту же транзитивную библиотеку, но разных, несовместимых между собой версий.

4. Чем Gradle отличается от Maven? Назовите 2-3 преимущества каждого.
   
* Maven использует жесткую XML-структуру и фиксированный жизненный цикл сборки. Gradle использует скрипты на Groovy/Kotlin и подход на основе кастомных задач (Task).
   
* Преимущества Maven: строгий стандарт (понятен без изучения кода сборки), высокая стабильность, огромная экосистема плагинов.
   
* Преимущества Gradle: высокая скорость работы за счет инкрементальной сборки и демона, гибкость настройки логики, лаконичный код сборки без XML.

5. Что такое JDBC Driver? Почему для разных СУБД нужны разные драйверы?

* JDBC Driver — это программный адаптер (библиотека), который транслирует стандартные вызовы Java API в специфический сетевой протокол конкретной базы данных. 

* Разные драйверы нужны потому, что PostgreSQL, MySQL, Oracle и H2 используют абсолютно разные внутренние протоколы обмена данными и структуры команд.

6. Чем PreparedStatement отличается от Statement? Зачем нужен PreparedStatement?
   
* `Statement` отправляет запрос в БД в виде чистой строки каждый раз заново. 

* `PreparedStatement` предварительно компилирует шаблон запроса в БД, а параметры подставляет отдельно. Для высокой производительности (при частых повторах одного запроса) и для автоматического экранирования параметров (защита от SQL-инъекций).

7. Что такое SQL Injection? Как PreparedStatement защищает от неё?

* SQL-инъекция — это уязвимость, при которой злоумышленник внедряет вредоносный SQL-код через текстовые поля ввода, ломая логику запроса (например, обход авторизации). 

* `PreparedStatement` защищает от нее, так как база данных воспринимает параметры строго как литералы (данные), а не как исполняемый SQL-код, полностью экранируя любые кавычки и спецсимволы.

8. Что такое транзакция? Что означают свойства ACID?

Транзакция — это группа последовательных операций с базой данных, которая выполняется как единое целое (либо всё, либо ничего). Свойства ACID гарантируют надежность:

* Atomicity (Атомарность): транзакция фиксируется полностью (`commit`) или полностью откатывается (`rollback`).

* Consistency (Согласованность): транзакция переводит базу из одного валидного состояния в другое.

* Isolation (Изолированность): параллельные транзакции не должны влиять на результат друг друга.

* Durability (Стойкость): если транзакция закоммичена, ее изменения не пропадут даже при сбое питания БД.

9. Что такое ORM? Какие преимущества и недостатки по сравнению с чистым JDBC?
   
* ORM (Object-Relational Mapping) — технология, связывающая таблицы БД с Java-классами.

* +: избавляет от написания ручного SQL, автоматически переносит данные в объекты, снижает объем шаблонного кода.

* -: создает избыточную нагрузку на память и процессор, генерирует неоптимальный SQL для сложных аналитических запросов, требует долгого изучения.

10. Что такое @Entity и @Table в Hibernate? Что происходит если они отсутствуют?
    
* `@Entity` указывает Hibernate, что данный Java-класс является сущностью, отображаемой в БД. 

* `@Table` задает конкретное имя таблицы для маппинга. 

* Если отсутствует `@Entity`, Hibernate проигнорирует класс и вызовет ошибку при попытке работы с ним. 

* Если отсутствует `@Table`, Hibernate будет искать или создавать таблицу, имя которой полностью совпадает с именем самого Java-класса.

11. Чем HQL отличается от SQL? Чем HQL отличается от Criteria API?
    
* HQL отличается от SQL тем, что работает с классами и их полями, а не с таблицами и колонками. 

* HQL отличается от Criteria API формой написания: HQL пишется в виде строк (легко допустить опечатку), а Criteria API строится динамически с помощью объектного кода Java, что гарантирует проверку типов и синтаксиса на этапе компиляции.

12. Что означает hbm2ddl.auto = create в конфигурации Hibernate?
    
* Настройка указывает Hibernate при каждом запуске приложения (создании `SessionFactory`) принудительно удалять существующие таблицы, схемы которых совпадают с текущими `@Entity`, и создавать их заново с нуля.

* Все старые данные при этом полностью стираются.
