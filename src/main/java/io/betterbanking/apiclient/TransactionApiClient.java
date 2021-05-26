package io.betterbanking.apiclient;

import io.betterbanking.domain.model.Transaction;

import java.util.List;

public interface TransactionApiClient {
	
    List<Transaction> findAllByAccountNumber(final Integer accountNumber);
    
}
