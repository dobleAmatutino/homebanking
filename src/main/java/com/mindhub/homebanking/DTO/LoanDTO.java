package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.Loan;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

public class LoanDTO {
    private long id;

    private String name;


    private List<Integer> payment ;

    private double maxAmount;

    private double interests;

    public LoanDTO(){}

    public LoanDTO(Loan loan){

        this.id=loan.getId();

        this.name= loan.getName();

        this.payment=loan.getPayment();

        this.maxAmount= loan.getMaxAmount();

        this.interests=loan.getInterests();


    }

    public double getInterests() {
        return interests;
    }

    public void setInterests(double interests) {
        this.interests = interests;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPayment() {
        return payment;
    }

    public void setPayment(List<Integer> payment) {
        this.payment = payment;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }
}
