package io.betterbanking.apiclient;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.acme.banking.model.OBReadTransaction6;
import com.fasterxml.jackson.databind.JsonNode;

import io.betterbanking.domain.model.Transaction;
import io.betterbanking.integration.OBTransactionAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RESTTransactionsAPIClient implements TransactionApiClient {
	
	@Value("${testnet.client}")
    private String clientId;
    @Value("${testnet.secret}")
    private String secret;

	private WebClient client;

	private OBTransactionAdapter adapter = new OBTransactionAdapter();
	
	public RESTTransactionsAPIClient(final WebClient client) {
        this.client = client;
    }

	@Override
	public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
		OBReadTransaction6 res = null;
		String encodedClientData = Base64Utils.encodeToString(String.format("%s:%s", clientId, secret).getBytes());
		try {
			res = client
                    .post()
                    .uri("/oauth/token")
                    .header("Authorization", "Basic " + encodedClientData)
                    .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .flatMap(tokenResponse -> {
                        String accessTokenValue = tokenResponse.get("access_token").textValue();
                        return client.get()
                                .uri("accounts/" + accountNumber + "/transactions")
                                .headers(h -> h.setBearerAuth(accessTokenValue))
                                .retrieve()
                                .bodyToMono(OBReadTransaction6.class);
                    })
                    .block();
		} catch (Exception ex) {
			log.error("failed to fetch account data from remote server due the following error {}", ex.getMessage());
		}
		if (res == null || res.getData() == null) {
			return Collections.emptyList();
		}
		
		log.info("getting data from the remote service");
		
		return res.getData()
					.getTransaction()
					.stream()
					.map(adapter::adapt)
					.collect(toList());
	}
}
