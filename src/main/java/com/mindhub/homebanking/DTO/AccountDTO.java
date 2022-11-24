package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private long id;
    private String number;
    private LocalDateTime creationDate;
    private Double balance;

    public AccountDTO(){}

    Set<TransactionDTO> transactionDTO = new HashSet<>();
    public AccountDTO(Account account){

        this.id=account.getId();
        this.number=account.getNumber();
        this.creationDate=account.getCreationDate();
        this.balance=account.getBalance();
        this.transactionDTO = account.getTransaction().stream().map
                (transaction -> new TransactionDTO(transaction)).collect(Collectors.toSet());


    }
    public Set<TransactionDTO> getTransactions(){return transactionDTO;}

    public long getId() {
        return id;
    }


    public String getNumber() {
        return number;
    }



    public LocalDateTime getCreationDate() {
        return creationDate;
    }



    public Double getBalance() {
        return balance;
    }


}
