package com.mindhub.homebanking.service.implement;

import com.mindhub.homebanking.DTO.TransactionDTO;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.service.Transactionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TransactionServiceImplement implements Transactionservice {
    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public List<TransactionDTO> getListTransactionDTO() {
        return transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO getTransactionDTOById(Long id) {
        return transactionRepository.findById(id).map(transaction -> new TransactionDTO(transaction)).orElse(null);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
         transactionRepository.save(transaction);

    }
}
