package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTO.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.service.Accountservice;
import com.mindhub.homebanking.service.Clientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ClientController {


    @Autowired
    private Accountservice accountservice;

    @Autowired
    private Clientservice clientservice;

    @Autowired

    private PasswordEncoder passwordEncoder;


    @GetMapping("/clients/current")
    public ClientDTO getClient(Authentication authentication) {


        return new ClientDTO(clientservice.findByEmail(authentication.getName()));
    }


    @PostMapping("/clients")


    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password) {


        if (firstName.isEmpty()) {

            return new ResponseEntity<>("missing firstName", HttpStatus.FORBIDDEN);

        }

        if (lastName.isEmpty()) {
            return new ResponseEntity<>("missing lastName", HttpStatus.FORBIDDEN);
        }

        if (email.isEmpty()) {
            return new ResponseEntity<>("missing email", HttpStatus.FORBIDDEN);
        }

        if (! email.contains("@")){
            return new ResponseEntity<>("the mails contains an @",HttpStatus.FORBIDDEN);
        }

        if (password.isEmpty()) {
            return new ResponseEntity<>("missing password", HttpStatus.FORBIDDEN);
        }


        if (clientservice.findByEmail(email) != null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }

        Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientservice.saveClient(newClient);


        Account newAccount = new Account("VIN" + getRandomNumber(10000000, 100000000), LocalDateTime.now(), 00.00, newClient);

        accountservice.saveAccount(newAccount);

        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    @GetMapping("/clients")


    public List<ClientDTO> getClients() {

        return clientservice.getListClientsDTO();
    }

    @GetMapping("clients/{id}")

    public ClientDTO getClient(@PathVariable Long id) {

        return clientservice.getClients(id);

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


}





