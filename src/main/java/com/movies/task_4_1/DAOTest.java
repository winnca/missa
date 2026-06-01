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

        printBalances();

        System.out.println("\nПеревод 100.00 от Стефан к Бонни");
        transfer(1, 2, new BigDecimal("300.00"));
        printBalances();

        System.out.println("\nПеревод 900.00 от от Стефан к Бонни");
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