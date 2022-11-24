package com.mindhub.homebanking.service;

import com.mindhub.homebanking.DTO.TransactionDTO;
import com.mindhub.homebanking.models.Transaction;

import java.util.List;

public interface Transactionservice {

    public List<TransactionDTO> getListTransactionDTO();

    public TransactionDTO getTransactionDTOById(Long id);

    public void saveTransaction(Transaction transaction);
}
