package com.example.demo.Service;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.Model.Transactions;

import java.util.List;

public interface TransactionsService {

    public Transactions transferMoney(Long senderId,Long receiverId, Double amount) throws Exception;
    public List<TransactionDTO> getAllTransactionsByUser(Long userId);
    public List<TransactionDTO> getAllTransactions();

}
