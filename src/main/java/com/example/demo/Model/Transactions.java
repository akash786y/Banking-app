package com.example.demo.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "transactions_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long senderId;
    @NonNull
    private Long receiverId;

    private String senderName;
    private String receiverName;
    private Double amount;
    private Date date;
}
