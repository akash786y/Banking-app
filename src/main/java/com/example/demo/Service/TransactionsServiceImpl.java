package com.example.demo.Service;

import com.example.demo.DTO.TransactionDTO;
import com.example.demo.Model.Account;
import com.example.demo.Model.Transactions;
import com.example.demo.Model.User;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.TransactionsRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Override
    @Transactional
    public Transactions transferMoney(Long senderId, Long receiverId, Double amount) throws Exception {

        if(!accountRepository.existsById(senderId) || !accountRepository.existsById(receiverId)){
            throw new IllegalArgumentException("Sender or receiver not found!!");
        }

        if(accountRepository.findById(senderId).get().getBalance()<amount){
            throw new Exception("Insufficient balance");
        }

        Account senderAccount=accountRepository.findById(senderId).orElseThrow();
        Account receiverAccount=accountRepository.findById(receiverId).orElseThrow();

        senderAccount.setBalance(senderAccount.getBalance()-amount);
        receiverAccount.setBalance(receiverAccount.getBalance()+amount);

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        Transactions transaction = Transactions
                .builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .amount(amount)
                .senderName(senderAccount.getName())
                .receiverName(receiverAccount.getName())
                .date(new Date())
                .build();
        transactionsRepository.save(transaction);
        return transaction;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {

        return transactionsRepository.findAll()
                .stream()
                .map(transaction -> TransactionDTO.builder()
                        .amount(transaction.getAmount())
                        .senderName(transaction.getSenderName())
                        .receiverId(transaction.getReceiverId())
                        .receiverName(transaction.getReceiverName())
                        .senderId(transaction.getSenderId())
                        .date(transaction.getDate())
                        .build())
                .toList();
    }

    @Override
    public List<TransactionDTO> getAllTransactionsByUser(Long userId) {

        List<Transactions> list=new ArrayList<>();
        User user=userRepository.findById(userId).orElseThrow();
        List<Long> listAccountIds=user.getAccounts().stream().map(account -> account.getId()).toList();

        for(Transactions transaction:transactionsRepository.findAll()){
            if(listAccountIds.contains(transaction.getSenderId()) || listAccountIds.contains(transaction.getReceiverId()))
                list.add(transaction);
        }
        return list
                .stream()
                .map(transaction -> TransactionDTO.builder()
                        .amount(transaction.getAmount())
                        .senderName(transaction.getSenderName())
                        .receiverId(transaction.getReceiverId())
                        .receiverName(transaction.getReceiverName())
                        .senderId(transaction.getSenderId())
                        .date(transaction.getDate())
                        .build())
                .toList();
    }
}
