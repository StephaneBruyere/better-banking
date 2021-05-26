package io.betterbanking.domain.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.acme.banking.model.OBActiveOrHistoricCurrencyAndAmount9;
import com.acme.banking.model.OBCreditDebitCode1;
import com.acme.banking.model.OBMerchantDetails1;
import com.acme.banking.model.OBReadDataTransaction6;
import com.acme.banking.model.OBReadTransaction6;
import com.acme.banking.model.OBTransaction6;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.betterbanking.apiclient.RESTTransactionsAPIClient;
import io.betterbanking.apiclient.TransactionApiClient;
import io.betterbanking.domain.model.Transaction;
import io.betterbanking.domain.service.TransactionService;
import io.betterbanking.domain.service.TransactionServiceImpl;
import io.betterbanking.repository.InMemoryMerchantDetailsRepository;
import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;

@SpringBootTest
class TransactionComponentTest {

	private MockWebServer server = new MockWebServer();
	private MockResponse response = new MockResponse();
	private TransactionApiClient apiClient = new RESTTransactionsAPIClient(WebClient.create(server.url("/").toString()));
	private TransactionService transactionService = new TransactionServiceImpl(apiClient, new InMemoryMerchantDetailsRepository());

	@Value("${server.port}")
    private int port;

    @Test
    public void testApplicationEndToEnd() throws JsonProcessingException {
    	var json = new ObjectMapper().writeValueAsString(transaction());
    	server.enqueue(response
				.setResponseCode(200)
				.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(json));
  	
    	var t =	Arrays.stream(given().standaloneSetup(new TransactionController(transactionService))
                .when()
                .get(String.format("http://localhost:%s/transactions/1234567", port))
                .then()
                .extract()
                .body()
                .as(Transaction[].class))
                .findAny()
                .orElseThrow();
    	
    	assertEquals(100.0d, t.getAmount());
    }
    
    private OBReadTransaction6 transaction() {
        var t = new OBReadTransaction6();
        t.setData(new OBReadDataTransaction6());
        t.getData().addTransactionItem(transactions());
        return t;
    }

    private OBTransaction6 transactions() {
        var t = new OBTransaction6();
        t.setAccountId("1234567");
        t.setCreditDebitIndicator(OBCreditDebitCode1.DEBIT);
        t.setAmount(amount());
        t.setMerchantDetails(merchantDetails());
        return t;
    }

    private OBActiveOrHistoricCurrencyAndAmount9 amount() {
        var amount = new OBActiveOrHistoricCurrencyAndAmount9();
        amount.setAmount("100.00");
        amount.setCurrency("USD");
        return amount;
    }

    private OBMerchantDetails1 merchantDetails() {
        var m = new OBMerchantDetails1();
        m.setMerchantName("acme");
        m.setMerchantCategoryCode("25");
        return m;
    }
    
    
}
