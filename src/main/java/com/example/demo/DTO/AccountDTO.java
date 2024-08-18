package com.example.demo.DTO;

import com.example.demo.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    private Long id;
    private Long accountNumber;
    private Double balance;
    private Long userId;
    private String name;
    private List<LoanDTO> loans;

}
