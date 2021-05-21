package com.betterBanking.domain.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.betterBanking.domain.model.Transaction;
import com.betterBanking.domain.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@GetMapping("/transactions/{accountNumber}")
	public ResponseEntity<List<Transaction>> findTransactionsByNumber(@PathVariable final int accountNumber) {
		return ResponseEntity.ok(transactionService.findAllByAccountNumber(accountNumber));
	}

}
