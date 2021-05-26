package io.betterbanking.domain.service;

import java.util.List;

import io.betterbanking.domain.model.Transaction;

public interface TransactionService {
	
	public List<Transaction> findAllByAccountNumber(int accountNumber);

}
