package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@DataJpaTest

@AutoConfigureTestDatabase(replace = NONE)

public class RepositoriesTest {

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    ClientRepository clientRepository;


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;



    @Autowired

    LoanRepository loanRepository;


   /* @Test
    public  void compareAccounts(){
        Account accounts1 = accountRepository.findByNumber("VIN001");
        Account accounts2 = accountRepository.findByNumber("VIN002");
        assertThat(accounts1.getBalance(), greaterThan( accounts2.getBalance()));

    }*/
/*
    @Test
    public void haveTransaction(){
        Account account = accountRepository.findByNumber("VIN001");

        assertThat(account.getTransaction().size(),greaterThan(1));
    }*/



    @Test

    public void existLoans(){

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans,is(not(empty())));

    }



    @Test

    public void existPersonalLoan(){

        List<Loan> loans = loanRepository.findAll();

        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));

    }

      @Test

    public void existCardColor(){
        List<Card> cards=cardRepository.findAll();

        assertThat(cards, hasItem(hasProperty("color",is(CardColor.TITANIUM))));
    }

    @Test
    public void isExpiredCard(){
        List<Card> cards=cardRepository.findAll();
        assertNotSame(cards.stream().map(card->card.getThruDate()),LocalDateTime.now());

    }

    @Test
    public void clientIndebted(){
        Client clients = clientRepository.findByEmail("mmaaron.17@gmail.com");


        assertThat(clients.getLoans().size(),greaterThan(0));
    }



}










