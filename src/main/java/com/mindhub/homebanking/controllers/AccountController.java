package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTO.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.service.Accountservice;
import com.mindhub.homebanking.service.Clientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api")
public class
AccountController {

    @Autowired
    private Clientservice clientservice;

    @Autowired
    private Accountservice accountservice;

    @GetMapping("/accounts")

    public List<AccountDTO> getAccounts(){

        return  accountservice.getListAccountsDTO();
    }


    @GetMapping("accounts/{id}")

    public AccountDTO getTransaction(@PathVariable Long id){

        return accountservice.findAccountById(id);

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    //tarea desde aqui:

    @PostMapping("/clients/current/accounts")

    public ResponseEntity<Object> createAccount(Authentication authentication){

        Client clientCurrenting = clientservice.findByEmail(authentication.getName());

        if (clientCurrenting.getAccounts().size()>=3) {
            return new ResponseEntity<>("you have reached the cap of accounts per client", HttpStatus.FORBIDDEN);
        }

        else {
            Account account = new Account("VIN"+getRandomNumber(10000000,100000000), LocalDateTime.now(),00.00,clientCurrenting);
            accountservice.saveAccount(account);

            return new ResponseEntity<>("your account has been created succefully",HttpStatus.CREATED);
        }
    }

}
