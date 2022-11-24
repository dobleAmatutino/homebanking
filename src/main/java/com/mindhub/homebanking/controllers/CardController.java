package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.Cardservice;
import com.mindhub.homebanking.service.Clientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private Cardservice cardservice;

    @Autowired
    private Clientservice clientservice;

    public int getRandomNumber(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }
    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam CardType cardType, @RequestParam CardColor cardColor
            , Authentication authentication) {
        Client clientCurrenting = clientservice.findByEmail(authentication.getName());

        if (cardType == null) {
            return new ResponseEntity<>("missing card type", HttpStatus.FORBIDDEN);
        }

        if (cardColor == null) {
            return new ResponseEntity<>("missing card color", HttpStatus.FORBIDDEN);
        }


        Set <Card> cardDebit=clientCurrenting.getCard().stream().filter(card ->card.getType()== CardType.DEBIT ).collect(Collectors.toSet());
        Set <Card> cardCredit=clientCurrenting.getCard().stream().filter(card -> card.getType()== CardType.CREDIT).collect(Collectors.toSet());

        if (cardDebit.size()>= 3 && cardType == CardType.DEBIT){
            return new ResponseEntity<>("You can not have more than 3 debit cards",HttpStatus.FORBIDDEN);
        }
        if (cardCredit.size()>= 3 && cardType == CardType.CREDIT){
            return new ResponseEntity<>("You can not have more than 3 Credit cards",HttpStatus.FORBIDDEN);
        }

        else {
            Card card = new Card (cardColor, cardType,getRandomNumber(1000,10000)+"-"+getRandomNumber(1000,10000)+"-"+getRandomNumber(1000,10000)+"-"+getRandomNumber(1000,10000)
                    ,getRandomNumber(100,1000),LocalDate.now().plusYears(5),LocalDate.now(),clientCurrenting);
            cardservice.saveCard(card);
            return new ResponseEntity<>("your card is being created",HttpStatus.CREATED);
        }

    }

}
