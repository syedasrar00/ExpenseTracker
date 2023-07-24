package com.expenseTracker.services;

import com.expenseTracker.DTO.ExpenseDto;
import com.expenseTracker.entities.Expense;
import com.expenseTracker.entities.User;
import com.expenseTracker.repositories.ExpenseRepository;
import com.expenseTracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    public List<ExpenseDto> getAllExpensesOfUser(String email){
        User user = userRepository.findByEmail(email).orElse(null);
        if(user == null){
            return null;
        }else{
            List<ExpenseDto> expenseDtos = user.getExpenseList().stream().map(e -> {
                ExpenseDto expenseDto = new ExpenseDto();
                expenseDto.setDate(e.getDate());
                expenseDto.setTitle(e.getTitle());
                expenseDto.setAmount(e.getAmount());
                expenseDto.setDescription(e.getDescription());
                return expenseDto;
            }).collect(Collectors.toList());
            return  expenseDtos;
        }
    }
    public String saveExpense(String email, ExpenseDto expenseDto){
        User user = userRepository.findByEmail(email).orElse(null);
        if(user == null){
            return "User does not exist";
        }else{
            Expense expense = new Expense();
            expense.setDate(new Date());
            expense.setUser(user);
            expense.setAmount(expenseDto.getAmount());
            expense.setTitle(expenseDto.getTitle());
            expense.setDescription(expenseDto.getDescription());
            user.getExpenseList().add(expense);
            try{
                userRepository.save(user);
                return "Saved Successfully";
            }catch (Exception e){
                return "Some ERROR has Occured" + e.getMessage();
            }
        }
    }
}
