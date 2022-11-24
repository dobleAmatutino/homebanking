package com.mindhub.homebanking.service.implement;

import com.mindhub.homebanking.DTO.LoanDTO;
import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.service.Loanservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class LoanServiceImplement implements Loanservice {
    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<LoanDTO> getLoanListDTO() {
        return loanRepository.findAll().stream().map(loan -> new LoanDTO(loan)).collect(Collectors.toList());
    }

    @Override
    public Loan findLoanById(Long id) {
         return loanRepository.findById(id).get();

    }

    @Override
    public void saveNewLoan(Loan loan) {
        loanRepository.save(loan);
    }
}
