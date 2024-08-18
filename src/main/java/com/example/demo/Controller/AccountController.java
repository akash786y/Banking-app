package com.example.demo.Controller;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/account")
    public String createAccount(@RequestBody AccountDTO accountDTO){
        return accountService.createAccount(accountDTO);
    }

    @DeleteMapping("/account/{user_id}/{acc_id}")
    public String deleteAccount(@PathVariable Long user_id, @PathVariable Long acc_id){
        return accountService.deleteAccount(user_id,acc_id);
    }

    @GetMapping("/account/getAll/{user_id}")
    public List<AccountDTO> getAllAccount(@PathVariable Long user_id){
        return accountService.getAllAccounts(user_id);
    }

    @GetMapping("/account/getOne/{id}")
    public AccountDTO getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @PutMapping("/account/deposit/{id}")
    public void deposit(@PathVariable Long id, @RequestBody Double amount){
        accountService.deposit(id, amount);
    }

    @PutMapping("/account/withdraw/{id}")
    public void withdraw(@PathVariable Long id, @RequestBody Double amount){
        accountService.withdraw(id, amount);
    }

}
