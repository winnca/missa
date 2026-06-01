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

