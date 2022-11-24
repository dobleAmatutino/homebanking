package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.DTO.LoanApplicationDTO;
import com.mindhub.homebanking.DTO.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    ClientLoanservice clientLoanservice;

    @Autowired
    Transactionservice transactionservice;
    @Autowired
    Accountservice accountservice;

    @Autowired
    Clientservice clientservice;

    @Autowired
    Loanservice loanservice;

    @GetMapping( "/loans")
    public List<LoanDTO> getLoan(){

        return loanservice.getLoanListDTO();
    }

    @PostMapping("/loans")
    @Transactional
    public ResponseEntity<?> reqLoan(@RequestBody LoanApplicationDTO loanApp, Authentication authentication){


        if (loanApp.getId()==0){
            return new ResponseEntity<>("we need the id between [1,3] AND not empty", HttpStatus.FORBIDDEN);
        }
        if (loanApp.getAmount()<=0){
            return new ResponseEntity<>("the mount can not be lower or equal 0", HttpStatus.FORBIDDEN);
        }


        if (loanApp.getPayments()<=0){
            return new ResponseEntity<>("the payments shouldnt be smaller than 0",HttpStatus.FORBIDDEN);
        }
        if (loanApp.getNumberAccount().isEmpty()){
            return new ResponseEntity<>("i dont know who is requesting the loan, please get in", HttpStatus.FORBIDDEN);
        }

        Loan loan = loanservice.findLoanById(loanApp.getId());

        if(loan==null){
            return new ResponseEntity<>("that loan does not exist", HttpStatus.FORBIDDEN);
        }

        if(loanApp.getAmount()> loan.getMaxAmount()){
            return new ResponseEntity<>("you cannot ask for more money than the maximum amount",HttpStatus.FORBIDDEN);
        }

        if(!loan.getPayment().contains(loanApp.getPayments())){
            return new ResponseEntity<>("the payments that you are asking hasnt that payment",HttpStatus.FORBIDDEN);
        }


        Account destinyLoan = accountservice.findByNumber(loanApp.getNumberAccount());


        if( destinyLoan == null){
            return new ResponseEntity<>("the number account is invalid",HttpStatus.FORBIDDEN);
        }

        Client currentClient = clientservice.findByEmail(authentication.getName());

        if (currentClient.getLoans().size()>=3){
            return new ResponseEntity<>("you had requested more loans than you can pay",HttpStatus.FORBIDDEN);
        }

        List<ClientLoan> loanH = currentClient.getLoans().stream().filter(prestamo->prestamo.getLoan()==loan).collect(Collectors.toList());

        if (loanH.size()>=1){
            return new ResponseEntity<>("you ask for many loans, will you have money to pay it? better not",HttpStatus.FORBIDDEN);
        }


        if(!currentClient.getAccounts().contains(destinyLoan)){
            return new ResponseEntity<>("the entered account does not belong to the client",HttpStatus.FORBIDDEN);
        }


        Transaction transaction = new Transaction(TransactionType.CREDIT,loanApp.getAmount(),loan.getName()+ "loan approved",LocalDateTime.now(),destinyLoan,loanApp.getAmount()+destinyLoan.getBalance());
        transactionservice.saveTransaction(transaction);

        destinyLoan.setBalance(destinyLoan.getBalance()+loanApp.getAmount());

/*
        if(loan.getName().equals("Hipotecario")){

            loanApp.setAmount(loanApp.getAmount()+ 0.2*loanApp.getAmount());

        }

        if(loan.getName().equals("Personal")){
            loanApp.setAmount(loanApp.getAmount()+ 0.6*loanApp.getAmount());
        }

        if(loan.getName().equals("Automotriz")){
            loanApp.setAmount(loanApp.getAmount()+ loan.getInterests()*loanApp.getAmount());
        }*/

        ClientLoan loanToClient= new ClientLoan( loanApp.getAmount()* loan.getInterests(),currentClient,loan,loanApp.getPayments(),loan.getInterests());
        clientLoanservice.saveClientLoan(loanToClient);


        return new ResponseEntity<>("You credit has been approved PAY your payments THANKS", HttpStatus.CREATED);
    }


    @PostMapping  ("/createLoan")
    public ResponseEntity<?> createLoan(@RequestParam String name,@RequestParam List<Integer> payments, @RequestParam Double maxAmount,
                                        @RequestParam Double interests){

        /*if (loanCreatedDTO.getId()==0){
            return new ResponseEntity<>("the new credit id is not possible create it",HttpStatus.FORBIDDEN);
        }*/

        if (name.equals("")){
            return new ResponseEntity<>("All the new loans should have a name",HttpStatus.FORBIDDEN);
        }

        if (payments.size()==0){
            return new ResponseEntity<>("please put more payment",HttpStatus.FORBIDDEN);
        }

        if (maxAmount<=0){
            return new ResponseEntity<>("please get in a real max a mount",HttpStatus.FORBIDDEN);
        }

        if (interests==0){
            return new ResponseEntity<>("you should not make us lose money watch out",HttpStatus.FORBIDDEN);
        }

        Loan newLoan= new Loan(name, maxAmount,payments, interests);
        loanservice.saveNewLoan(newLoan);


        return new ResponseEntity<>("new Loan created",HttpStatus.CREATED);
    }


}
