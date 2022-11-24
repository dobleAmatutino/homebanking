package com.mindhub.homebanking.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //Entity: crea una tabla en la base de datos
public class Transaction { //
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator = "native")
    @GenericGenerator(name="native",strategy = "native")//hace que no se repita el value
    private long id;

    private TransactionType type;

    private Double amount;

    private String description;

    private LocalDateTime date;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Account_id")
    private Account account;

    private Double balance;

    public Transaction() {}


    public Transaction(TransactionType type, Double amount, String description , LocalDateTime date, Account account,Double balance) {
    this.type=type;
    this.amount=amount;
    this.description=description;
    this.date=date;
    this.account=account;
    this.balance=balance;

    }

    public long getId() {
        return id;
    }


    public TransactionType getType() {
        return type;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @JsonIgnore
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
