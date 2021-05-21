package com.betterBanking.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betterBanking.domain.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Override
	public List<Transaction> findAllByAccountNumber(final int accountNumber) {
		return List.of(new Transaction("credit", 1234567, "USD", 100.00, "acme", "acme.png"));
	}

}
