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

