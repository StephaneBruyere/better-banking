package io.betterbanking.domain.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import io.betterbanking.domain.model.Transaction;
import io.betterbanking.domain.service.TransactionService;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
//	@MockBean 
//	private TransactionService transactionService;

	@Test
	public void testTransactions() throws Exception {
//		doReturn(List.of(new Transaction())).when(transactionService).findAllByAccountNumber(anyInt());
		mockMvc.perform(get("/transactions/12345678")).andExpect(status().isOk());
	}

}
