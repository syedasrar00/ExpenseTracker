package com.expenseTracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private String title;
    private Double amount;
    private String description;
    private Date date;
}
