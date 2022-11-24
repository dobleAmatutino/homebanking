package com.mindhub.homebanking.service;

import com.mindhub.homebanking.DTO.AccountDTO;
import com.mindhub.homebanking.models.Account;

import java.util.List;

public interface Accountservice {

    public List<AccountDTO> getListAccountsDTO();

    public void saveAccount(Account account);

    public Account findByNumber(String numberAccount);

    public AccountDTO findAccountById(Long id);


}
