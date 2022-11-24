package com.mindhub.homebanking.models;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import org.hibernate.annotations.GenericGenerator;

    import javax.persistence.*;
    import java.time.LocalDateTime;
    import java.util.HashSet;
    import java.util.Set;

@Entity
    public class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")

        private long id;
        private String number;
        private LocalDateTime creationDate;
        private Double balance;


        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "client_id")
        private  Client client;



        @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
        Set<Transaction> transaction = new HashSet<>();

        public Account() {}

        public Account(String number, LocalDateTime creationDate, Double balance, Client client) {

            this.number = number;
            this.creationDate = creationDate;
            this.balance = balance;
            this.client = client;

        }

        public Set<Transaction> getTransaction(){
        return transaction;
    }

        public void addTransaction(Transaction transactions){
        transactions.setAccount(this);
        transaction.add(transactions);

        }

        public long getId() {
            return id;
        }

        public String getNumber() {
            return number;
        }


        public LocalDateTime getCreationDate() {
            return creationDate;
        }


        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }
        @JsonIgnore
        public Client getclient(){
            return client;
        }

        public void setclient(Client client) {
        }

    }
