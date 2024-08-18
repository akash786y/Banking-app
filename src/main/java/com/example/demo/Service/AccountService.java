package com.example.demo.Service;

import com.example.demo.DTO.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    String createAccount(AccountDTO accountDTO);
    String deleteAccount(Long user_id, Long account_id);
    List<AccountDTO> getAllAccounts(Long id);
    AccountDTO getAccountById(Long account_id);
    void deposit(Long accountId, Double amount);
    void withdraw(Long accountId, Double amount);
}
