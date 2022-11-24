package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;

import java.time.LocalDate;

public class CardDTO {

    private CardColor color;

    private CardType type;

    private String number;

    private int cvv;

    private LocalDate thruDate;

    private LocalDate fromDate;

    private String cardHolder;


    public CardDTO(){}

    public CardDTO(Card card) {
        this.color =card.getColor();
        this.type = card.getType();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
        this.cardHolder = card.getCardHolder();

    }

    public CardColor getColor() {
        return color;
    }

    public CardType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }


}
