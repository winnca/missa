package com.movies.task_2_2;

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