package io.betterbanking.domain.service;

import java.util.Collections;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.betterbanking.apiclient.TransactionApiClient;
import io.betterbanking.domain.model.Transaction;
import io.betterbanking.repository.MerchantDetailsRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private TransactionApiClient transactionApiClient;
	
	private MerchantDetailsRepository merchantDetailsRepository;
	
	public TransactionServiceImpl(final TransactionApiClient transactionApiClient, final MerchantDetailsRepository merchantDetailsRepository) {
        this.transactionApiClient = transactionApiClient;
        this.merchantDetailsRepository = merchantDetailsRepository;
    }

	@Override
	@Cacheable(cacheNames = "transactions")
	@CircuitBreaker(name = "transactionService", fallbackMethod = "foundNone")
	public List<Transaction> findAllByAccountNumber(final int accountNumber) {
		var transactions = transactionApiClient.findAllByAccountNumber(accountNumber);
		transactions.forEach(t->t.setMerchantLogo(merchantDetailsRepository.findMerchantLogo(t.getMerchantName()).orElse(null)));	
		return transactions;
	}
	
	private List<Transaction> foundNone(final Integer accountNumber, final Throwable throwable) {
        return Collections.emptyList();
    }

}
