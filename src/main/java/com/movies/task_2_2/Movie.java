package com.movies.task_2_2;

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