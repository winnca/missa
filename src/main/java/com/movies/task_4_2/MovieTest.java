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