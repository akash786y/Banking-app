package com.example.demo.Service;

import com.example.demo.DTO.LoanDTO;
import com.example.demo.Model.Account;
import com.example.demo.Model.Loan;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{


    @Autowired
    LoanRepository loanRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public String createLoan(LoanDTO loanDTO) {
        if(!accountRepository.existsById(loanDTO.getAccount_id())){
            return "Account does not exits!!";
        }
        Loan loan = toLoan(loanDTO);
        loan= loanRepository.save(loan);
        return "Loan created successfully";
    }

    @Override
    public List<LoanDTO> getAllLoans(Long account_id) {
        Account account=accountRepository.findById(account_id).orElseThrow();
        List<LoanDTO> list=new ArrayList<>();
        for(Loan loan:account.getLoans()){
            if(loan.getAccount().getId().equals(account_id)){
                list.add(toLoanDTO(loan));
            }
        }
        return list;
    }

    @Override
    public LoanDTO getLoanById(Long loan_id) {
        return toLoanDTO(loanRepository.findById(loan_id).orElseThrow());
    }

    @Override
    public String deleteLoan(Long loan_id) {
        Loan loan=loanRepository.findById(loan_id).orElseThrow();
        loanRepository.delete(loan);
        return "Loan deleted successfully";
    }

    public Loan toLoan(LoanDTO loanDTO){
        return Loan
                .builder()
                .startDate(loanDTO.getStartDate())
                .endDate(loanDTO.getEndDate())
                .amount(loanDTO.getAmount())
                .name(loanDTO.getName())
                .interestRate(loanDTO.getInterestRate())
                .account(accountRepository.findById(loanDTO.getAccount_id()).orElseThrow())
                .build();
    }

    public LoanDTO toLoanDTO(Loan loan){
        return LoanDTO
                .builder()
                .account_id(loan.getAccount().getId())
                .amount(loan.getAmount())
                .name(loan.getName())
                .startDate(loan.getStartDate())
                .endDate(loan.getEndDate())
                .interestRate(loan.getInterestRate())
                .build();
    }
}
