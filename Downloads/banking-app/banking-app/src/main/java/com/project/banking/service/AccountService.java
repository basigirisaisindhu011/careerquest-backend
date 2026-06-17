package com.project.banking.service;

import com.project.banking.dto.AccountDto;
import com.project.banking.dto.TransactionDto;
import com.project.banking.dto.TransferFundDto;

import java.util.List;

public interface AccountService {
    AccountDto addAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id,double amount);
    AccountDto withdraw(Long id,double amount);
    List<AccountDto> getAllAccounts();
    AccountDto deleteAccountById(Long id);
    void transferFunds(TransferFundDto transferFundDto);
    List<TransactionDto> getAccountTransacctions(Long accountId);
}
