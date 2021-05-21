package com.betterBanking.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceTest {
	
	@Autowired
	TransactionService transactionService;

	@Test
	void testTransactionCount() {
		assertEquals(1, transactionService.findAllByAccountNumber(1234567).size());
	}

}
