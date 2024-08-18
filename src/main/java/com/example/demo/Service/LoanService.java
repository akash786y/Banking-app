package com.example.demo.Service;


import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.LoanDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {

    public String createLoan(LoanDTO loanDTO);
    public List<LoanDTO> getAllLoans(Long account_id);
    public LoanDTO getLoanById(Long loan_id);
    public String deleteLoan(Long loan_id);
}
