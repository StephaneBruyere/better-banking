package com.betterBanking.domain.service;

import java.util.List;

import com.betterBanking.domain.model.Transaction;

public interface TransactionService {
	
	public List<Transaction> findAllByAccountNumber(int accountNumber);

}
