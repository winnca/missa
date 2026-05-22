package com.movies.task_2_2;

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