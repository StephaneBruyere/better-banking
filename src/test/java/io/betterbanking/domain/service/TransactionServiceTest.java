package io.betterbanking.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import io.betterbanking.apiclient.TransactionApiClient;
import io.betterbanking.domain.model.Transaction;
import io.betterbanking.repository.MerchantDetailsRepository;

@SpringBootTest
class TransactionServiceTest {
	
	@Mock 
	private TransactionApiClient transactionApiClient;
	
	@Mock 
	private MerchantDetailsRepository merchantDetailsRepo;
    
    @InjectMocks 
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setup() {
        when(transactionApiClient.findAllByAccountNumber(any())).thenReturn(List.of(new Transaction()));
    }

    @DisplayName("test TransactionService with mock TransactionApiClient")
	@Test
	void testTransactionCount() {
		assertEquals(1, transactionService.findAllByAccountNumber(1234567).size());
	}

}
