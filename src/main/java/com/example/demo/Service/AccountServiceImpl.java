package com.example.demo.Service;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.Model.Account;
import com.example.demo.Model.User;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;


    @Override
    public String createAccount(AccountDTO accountDTO) {
        if(!userRepository.existsById(accountDTO.getUserId())){
            return "User does not exits!!";
        }
        Account account=toAccount(accountDTO);
        accountRepository.save(account);
        return "Account created successfully";
    }

    @Override
    public String deleteAccount(Long user_id, Long account_id) {
        User user=userRepository.findById(user_id).orElseThrow(RuntimeException::new);
        Account account = accountRepository.findById(account_id).orElseThrow(RuntimeException::new);
        if(account.getUser().getId().equals(user_id)){
            accountRepository.delete(account);
            return "Account deleted successfully";
        }
        return "Account not found!!";
    }

    @Override
    public List<AccountDTO> getAllAccounts(Long user_id) {
        List<AccountDTO> list=new ArrayList<>();
        for(Account account:accountRepository.findAll()){
            if(account.getUser().getId().equals(user_id)) {
                AccountDTO accountDTO=toAccountDTO(account);
                list.add(accountDTO);
            }
        }
        return list;
    }

    @Override
    public AccountDTO getAccountById(Long account_id) {
        Account account=accountRepository.findById(account_id).orElseThrow(RuntimeException::new);
        return toAccountDTO(account);
    }

    @Override
    @Transactional
    public void deposit(Long accountId, Double amount) {
        Account account=accountRepository.findById(accountId).orElseThrow(RuntimeException::new);
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void withdraw(Long accountId, Double amount) {
        Account account=accountRepository.findById(accountId).orElseThrow(RuntimeException::new);
        double curr_balance=account.getBalance();
        if(curr_balance<amount){
            throw new RuntimeException("Insufficient balance");
        }
        else{
            account.setBalance(account.getBalance()-amount);
        }
    }

    public AccountDTO toAccountDTO(Account account){
        return AccountDTO.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .name(account.getName())
                .id(account.getId())
                .userId(account.getUser().getId())
                .build();
    }

    public Account toAccount(AccountDTO accountDTO){
        return Account.builder()
                .id(accountDTO.getId())
                .accountNumber(accountDTO.getAccountNumber())
                .balance(accountDTO.getBalance())
                .name(accountDTO.getName())
                .user(userRepository.findById(accountDTO.getUserId()).orElseThrow(RuntimeException::new))
                .build();
    }
}
