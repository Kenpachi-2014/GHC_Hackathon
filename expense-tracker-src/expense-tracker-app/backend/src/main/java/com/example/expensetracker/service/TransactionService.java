package com.example.expensetracker.service;

import com.example.expensetracker.controller.model.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {

    public List<TransactionDTO> listAll() {
        return Collections.emptyList();
    }
}