package com.mindhub.homebanking.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Entity
public class Client {


   @Id
   @GeneratedValue
   @GenericGenerator(name="native", strategy="native")
   private long id;


   private String firstName;
   private String lastName;
   private String email;

   private String password;

   @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
   Set<Account> accountss = new HashSet<>();



   @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
   Set<ClientLoan> clientLoans = new HashSet<>();


   @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
   Set<Card> cards = new HashSet<>();

   public Client(){
   }


   public Client(String firstName,String lastName,String email,String password) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password=password;
   }

   public Set<Account> getAccounts(){
      return accountss;
   }

   public void addAccount(Account accounts){
      accounts.setclient(this);
      accountss.add(accounts);
   }

   public long getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @JsonIgnore

   public List<ClientLoan> getLoans(){return clientLoans.stream().collect(toList());}


   public Set<Card> getCard(){return cards;}

   @Override
   public String toString() {
      return "Client{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              '}';
   }
}
