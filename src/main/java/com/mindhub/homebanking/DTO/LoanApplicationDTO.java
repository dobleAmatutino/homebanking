package com.mindhub.homebanking.DTO;

public class LoanApplicationDTO {

    private long id;

    private double amount;

    private int payments;

    private String numberAccount;

    public LoanApplicationDTO(long id, double amount, int payments, String numberAccount ){

        this.id = id;

        this.amount =amount;

        this.payments = payments;

        this.numberAccount= numberAccount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }
}
