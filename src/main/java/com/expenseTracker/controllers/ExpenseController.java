package com.expenseTracker.controllers;

import com.expenseTracker.DTO.ExpenseDto;
import com.expenseTracker.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @GetMapping()
    public List<ExpenseDto> getAllExpenses(@RequestParam String email){
        return expenseService.getAllExpensesOfUser(email);
    }
    @PostMapping()
    public String saveExpense(@RequestParam String email, @RequestBody ExpenseDto expenseDto){
        return expenseService.saveExpense(email, expenseDto);
    }
}
