package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTO.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.service.Accountservice;
import com.mindhub.homebanking.service.Clientservice;
import com.mindhub.homebanking.service.Transactionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api")
public class TransactionController {


    @Autowired
    private Clientservice clientservice;

    @Autowired
    private Accountservice accountservice;

    @Autowired
    private Transactionservice transactionservice;

    @GetMapping("/transactions")

    public List<TransactionDTO> getTransactions() {

        return transactionservice.getListTransactionDTO();
    }

    @GetMapping("transactions/{id}")

    public TransactionDTO getTransaction(@PathVariable Long id) {

        return transactionservice.getTransactionDTOById(id);

    }

    @Transactional
    @PostMapping("/transactions")

    public ResponseEntity<?> createdTransaction(
            @RequestParam Double amount, @RequestParam String description,
            @RequestParam String originAccount, @RequestParam String destinyAccount, Authentication authentication) {

        if (amount == null || amount <= 0) {

            return new ResponseEntity<>("that is not a transferible amount ", HttpStatus.FORBIDDEN);

        }

            if (description.isEmpty()) {
                return new ResponseEntity<>("add a description please", HttpStatus.FORBIDDEN);
            }

        if (originAccount.isEmpty()) {
            return new ResponseEntity<>("did you mean this account number?", HttpStatus.FORBIDDEN);
        }


        if (destinyAccount.isEmpty()) {
            return new ResponseEntity<>("where am I going to transfer?", HttpStatus.FORBIDDEN);
        }

        if (originAccount.equals(destinyAccount)) {
            return new ResponseEntity<>("you can not have the same number accounts to transfer", HttpStatus.FORBIDDEN);
        }

        Account originAccount1 = accountservice.findByNumber(originAccount);

        Account destinyAccount1 = accountservice.findByNumber(destinyAccount);

        if (originAccount1 == null) {
            return new ResponseEntity<>("that account origin doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (destinyAccount1 == null) {
            return new ResponseEntity<>("that account destiny doesn't exist", HttpStatus.FORBIDDEN);
        }

        Client clientCurrent = clientservice.findByEmail(authentication.getName());

        if (!clientCurrent.getAccounts().contains(originAccount1)) {
            return new ResponseEntity<>("this is your account number", HttpStatus.FORBIDDEN);
        }



        if (amount > originAccount1.getBalance()) {
            return new ResponseEntity<>("INSUFFICENT BALANCEEE", HttpStatus.FORBIDDEN);
        }

        Transaction debitTransaction = new Transaction(TransactionType.DEBIT, -amount, description, LocalDateTime.now(), originAccount1, originAccount1.getBalance()-amount);


        Transaction creditTransaction = new Transaction(TransactionType.CREDIT, amount, description, LocalDateTime.now(), destinyAccount1, destinyAccount1.getBalance()+amount);

        originAccount1.setBalance(originAccount1.getBalance() - amount);
        destinyAccount1.setBalance(destinyAccount1.getBalance() + amount);

        transactionservice.saveTransaction(debitTransaction);
        transactionservice.saveTransaction(creditTransaction);

        return new ResponseEntity<>("your transaction had been created", HttpStatus.CREATED);
    }

}