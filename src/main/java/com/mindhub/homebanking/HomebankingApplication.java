package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);



	}
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository
			, TransactionRepository transactionRepository, LoanRepository loanRepository,
									  ClientLoanRepository clientLoanRepository, CardRepository cardRepository,PasswordEncoder passwordEncoder){
		return args ->{




			Client client1 = new Client("Aaron","Mujica","mmaaron.17@gmail.com",passwordEncoder.encode("1234"));

			clientRepository.save(client1);

			Client client2 = new Client("Luis","Ñao","luisñao@gmail.com",passwordEncoder.encode("1234"));

			clientRepository.save(client2);

			Client client3 = new Client("melba","morano","melba@gmail.com",passwordEncoder.encode("melba"));
			clientRepository.save(client3);

			Account acount1 = new Account("VIN001", LocalDateTime.now(), 5000.00, client1);

			accountRepository.save(acount1);

			Account acount2 = new Account("VIN002", LocalDateTime.now().plusDays(1),7500.00,client1);
			accountRepository.save(acount2);

			Account acount3 = new Account("VIN012", LocalDateTime.now().plusDays(1),7500.00,client1);
			accountRepository.save(acount3);





			List<Integer> paymentsH = List.of(12,24,36,48,60);
			List<Integer> paymentsP = List.of(6,12,24);
			List<Integer> paymentsA = List.of(6,12,24,36);

			Loan loan1 = new Loan("Hipotecary",500000.00,paymentsH,1.2);
			loanRepository.save(loan1);

			Loan loan2 = new Loan ("Personal",100000.00,paymentsP,1.6);
			loanRepository.save(loan2);

			Loan loan3 = new Loan ("Automotive",300000.00, paymentsA,1.0001);
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan(400000.00,client1,loan1,60,0.2);
			clientLoanRepository.save(clientLoan1);
			ClientLoan clientLoan2 = new ClientLoan(50000.00,client1,loan3, 12,0.6);
			clientLoanRepository.save(clientLoan2);
			ClientLoan clientLoan3 = new ClientLoan(100000.00,client2,loan3,24,0.0001);
			clientLoanRepository.save(clientLoan3);
			ClientLoan clientLoan4 = new ClientLoan(200000.00,client2,loan2,36,0.6);
			clientLoanRepository.save(clientLoan4);

			Card card1 = new Card(CardColor.GOLD,CardType.DEBIT,"1234 5432 6789 9010",123, LocalDate.now().plusYears(5),LocalDate.now(),client1);
			cardRepository.save(card1);

			Card card2 = new Card(CardColor.SILVER,CardType.CREDIT,"1094 0444 6743 8234",506, LocalDate.now().plusYears(5),LocalDate.now(),client1);
			cardRepository.save(card2);

			Card card3 = new Card(CardColor.TITANIUM,CardType.CREDIT,"1234 5002 3459 9010",145, LocalDate.now().plusYears(5),LocalDate.now(),client2);
			cardRepository.save(card3);
		};


	}

}
