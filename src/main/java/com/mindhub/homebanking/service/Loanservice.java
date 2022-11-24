package com.mindhub.homebanking.service;

import com.mindhub.homebanking.DTO.LoanDTO;
import com.mindhub.homebanking.models.Loan;

import java.util.List;

public interface Loanservice {

    public List<LoanDTO> getLoanListDTO();

    public Loan findLoanById(Long id);

    public  void saveNewLoan(Loan loan);
}
