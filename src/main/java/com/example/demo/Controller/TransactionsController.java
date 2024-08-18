package com.example.demo.Controller;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.Model.Transactions;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsController {

    @Autowired
    TransactionsService transactionsService;

    @PostMapping("/transaction/send")
    public Transactions transferMoney(@RequestBody Transactions transaction){

        try {
            transactionsService.transferMoney(transaction.getSenderId(), transaction.getReceiverId(), transaction.getAmount());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return transaction;
    }

    @GetMapping("/transaction/getAll")
    public List<TransactionDTO> getAllTransactions(){

        return transactionsService.getAllTransactions();
    }

    @GetMapping("/transaction/getAll/{userId}")
    public List<TransactionDTO> getAllTransactionsByUser(@PathVariable Long userId){

        return transactionsService.getAllTransactionsByUser(userId);
    }
}
