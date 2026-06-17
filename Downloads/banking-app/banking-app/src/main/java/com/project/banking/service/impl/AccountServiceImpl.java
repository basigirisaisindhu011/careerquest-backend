package com.project.banking.service.impl;

import com.project.banking.Entity.Account;
import com.project.banking.Entity.Transaction;
import com.project.banking.Repository.AccountRepository;
import com.project.banking.Repository.TransactionRepository;
import com.project.banking.dto.AccountDto;
import com.project.banking.dto.TransactionDto;
import com.project.banking.dto.TransferFundDto;
import com.project.banking.exception.AccountException;
import com.project.banking.mapper.AccountMapper;
import com.project.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;
    public AccountServiceImpl(AccountRepository accountRepository,TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository=transactionRepository;
    }

    @Override
    public AccountDto addAccount(AccountDto accountDto) {
        Account account= AccountMapper.maptoAccount(accountDto);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new AccountException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new AccountException("Account does not exists"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);

        Transaction transaction=new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType("DEPOSIT");
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new AccountException("Account does not exists"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total=account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        Transaction transaction=new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType("WITHDRAW");
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public AccountDto deleteAccountById(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new AccountException("Account does not exists"));
        accountRepository.deleteById(id);
        return null;
    }

    @Override
    public void transferFunds(TransferFundDto transferFundDto) {
        //Retrieve the account from which we send the amount
       Account fromAccount= accountRepository.findById(transferFundDto.fromAccountId())
                .orElseThrow(()->new AccountException("Account does not exists"));

       //Retrieve the amount to which we send the amount
        Account toAccount=accountRepository.findById(transferFundDto.toAccountId())
                .orElseThrow(()->new AccountException("Account does not exists"));

        if(fromAccount.getBalance()<transferFundDto.amount()){
            throw new RuntimeException("Insufficient Balance");

        }
        //debit amount from account obj
        fromAccount.setBalance(fromAccount.getBalance()-transferFundDto.amount());
        //credit
        toAccount.setBalance(toAccount.getBalance()+ transferFundDto.amount());
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction=new Transaction();
        transaction.setAccountId(transferFundDto.fromAccountId());
        transaction.setAmount(transferFundDto.amount());
        transaction.setTransactionType("TRANSFER");
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDto> getAccountTransacctions(Long accountId) {
        List<Transaction> transactions=transactionRepository.findByAccountIdOrderByTimestampDesc(accountId);

        return transactions.stream().map((transaction)->convertEntityToDto(transaction))
                .collect(Collectors.toList());

    }
    private TransactionDto convertEntityToDto(Transaction transaction){
        TransactionDto transactionDto=new TransactionDto(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTimestamp()
        );

        return transactionDto;
    }
}
