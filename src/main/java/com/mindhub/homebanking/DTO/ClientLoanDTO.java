package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

public class ClientLoanDTO {

    private long id;

    private double amount;

    private long loan;

    private int payments;

    private String name;


    public ClientLoanDTO(){}

    public ClientLoanDTO(ClientLoan clientLoan){

        this.id = clientLoan.getId();

        this.amount = clientLoan.getAmount();

        this.name = clientLoan.getLoan().getName();

        this.loan = clientLoan.getLoan().getId();


        this.payments = clientLoan.getPayments();

    }

    public long getId() {
        return id;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getLoan() {
        return loan;
    }

    public void setLoan(long loan) {
        this.loan = loan;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
