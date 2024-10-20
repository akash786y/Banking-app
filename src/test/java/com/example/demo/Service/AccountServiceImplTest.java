package com.example.demo.Service;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.Model.Account;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;


//@SpringBootTest
//class AccountServiceImplTest {
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    AccountService accountService;
//
//    @Test
//    @Transactional
//    public void createAccount_userExistsTest(){
//
//        AccountDTO accountDTO=new AccountDTO();
//        accountDTO.setAccountNumber(103L);
//        accountDTO.setBalance(550.0);
//        accountDTO.setUserId(1L);
//        accountDTO.setLoans(new ArrayList<>());
//
//        String str=accountService.createAccount(accountDTO);
//
//        Assertions.assertEquals("Account created successfully", str);
//    }
//
//    @Test
//    public void createAccount_NullTest(){
//
//        AccountDTO accountDTO=null;
//        String result=null;
//        try{
//            result=accountService.createAccount(accountDTO);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        Assertions.assertNull(result);
//    }
//
//
//
//    public Account toAccount(AccountDTO accountDTO){
//        return Account.builder()
//                .id(accountDTO.getId())
//                .accountNumber(accountDTO.getAccountNumber())
//                .balance(accountDTO.getBalance())
//                .user(userRepository.findById(accountDTO.getUserId()).orElseThrow(RuntimeException::new))
//                .build();
//    }
//}