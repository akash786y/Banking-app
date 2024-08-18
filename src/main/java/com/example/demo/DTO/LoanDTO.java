package com.example.demo.DTO;

import com.example.demo.Model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDTO {

    private Date startDate;
    private Date endDate;
    private Double amount;
    private Double interestRate;
    private String name;

    private Long account_id;
}
