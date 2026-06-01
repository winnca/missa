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

