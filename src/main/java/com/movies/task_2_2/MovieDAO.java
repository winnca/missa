package com.movies.task_2_2;

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