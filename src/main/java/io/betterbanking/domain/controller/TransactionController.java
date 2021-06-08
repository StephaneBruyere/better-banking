package io.betterbanking.domain.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.betterbanking.domain.model.Transaction;
import io.betterbanking.domain.service.TransactionService;

@RestController
public class TransactionController {
	
	private TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@GetMapping("/transactions/{accountNumber}")
	@PostFilter(value = "hasAuthority(filterObject.accountNumber)")
	public ResponseEntity<List<Transaction>> findTransactionsByNumber(@PathVariable final int accountNumber, Principal principal) {
		System.err.println(principal);
		return ResponseEntity.ok(transactionService.findAllByAccountNumber(accountNumber));
	}

}
