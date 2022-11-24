package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.Loan;

import java.util.Collection;
import java.util.List;

public class LoanCreatedDTO {
    private long id;

    private String name;


    private List<Integer> payment ;

    private double maxAmount;

    private double interests;

    public LoanCreatedDTO(){}

    public LoanCreatedDTO(String name, List<Integer> payment,double maxAmount,double interests ){



        this.name= name;

        this.payment=payment;

        this.maxAmount= maxAmount;

        this.interests=interests;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getInterests() {
        return interests;
    }

    public void setInterests(double interests) {
        this.interests = interests;
    }

    public List<Integer> getPayment() {
        return payment;
    }

    public void setPayment(List<Integer> payment) {
        this.payment = payment;
    }
}
