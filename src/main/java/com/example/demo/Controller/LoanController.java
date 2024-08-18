package com.example.demo.Controller;

import com.example.demo.DTO.LoanDTO;
import com.example.demo.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    LoanService loanService;


    @PostMapping("/loan")
    public String createLoan(@RequestBody LoanDTO loanDTO){
        return loanService.createLoan(loanDTO);
    }

    @GetMapping("/loan/getAll/{account_id}")
    public List<LoanDTO> getAllLoans(@PathVariable Long account_id){
        return loanService.getAllLoans(account_id);
    }

    @GetMapping("/loan/getOne/{loan_id}")
    public LoanDTO getLoanById(@PathVariable Long loan_id){
        return loanService.getLoanById(loan_id);
    }

    @DeleteMapping("/loan/del/{loan_id}")
    public String deleteLoan(@PathVariable Long loan_id){
        return loanService.deleteLoan(loan_id);
    }
}
