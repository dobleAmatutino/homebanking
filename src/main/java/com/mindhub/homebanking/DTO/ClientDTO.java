package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;

    private String password;

    Set<AccountDTO> accountssDTO = new HashSet<>();

    Set<ClientLoanDTO> clientLoansDTO = new HashSet<>();

    Set<CardDTO> cardDTO= new HashSet<>();



    public ClientDTO(){}

    public ClientDTO(Client client){

        this.id = client.getId();

        this.firstName = client.getFirstName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

        this.accountssDTO = client.getAccounts().stream().map(account ->new AccountDTO(account)).collect(Collectors.toSet());


        this.clientLoansDTO = client.getLoans().stream().map
                (loans -> new ClientLoanDTO(loans)).collect(Collectors.toSet());

        this.cardDTO = client.getCard().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());

        this.password = client.getPassword();

    }

    public Set<CardDTO> getCard() {
        return cardDTO;
    }

    public Set<ClientLoanDTO> getClientLoans() {
        return clientLoansDTO;
    }

    public Set<AccountDTO> getAccounts(){return accountssDTO;}


    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
