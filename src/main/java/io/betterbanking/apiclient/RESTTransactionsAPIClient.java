package io.betterbanking.apiclient;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.acme.banking.model.OBReadTransaction6;

import io.betterbanking.domain.model.Transaction;
import io.betterbanking.integration.OBTransactionAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RESTTransactionsAPIClient implements TransactionApiClient {

	private WebClient client;

	private OBTransactionAdapter adapter = new OBTransactionAdapter();
	
	public RESTTransactionsAPIClient(final WebClient client) {
        this.client = client;
    }

	@Override
	public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
		OBReadTransaction6 res = null;
		try {
			res = client.get()
					.uri("accounts/" + accountNumber + "/transactions").retrieve()
					.bodyToMono(OBReadTransaction6.class)
					.block();
		} catch (Exception ex) {
			log.error("failed to retrieve account information due to the following reason {}", ex.getMessage());
		}
		if (res == null || res.getData() == null) {
			return Collections.emptyList();
		}
		return res.getData()
					.getTransaction()
					.stream()
					.map(adapter::adapt)
					.collect(toList());
	}
}
