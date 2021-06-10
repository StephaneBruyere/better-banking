package io.betterbanking.domain.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import io.betterbanking.domain.model.Transaction;
import io.betterbanking.domain.service.TransactionService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerIntegrationTest {

	private final String uri = "http://localhost:8089/auth/realms/SpringBootKeycloak/protocol/openid-connect/token";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransactionService transactionService;

	@Test
	public void testTransactions() throws Exception {
		doReturn(List.of(new Transaction())).when(transactionService).findAllByAccountNumber(anyInt());
		mockMvc.perform(get("/transactions/123456")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testAuthenticatedTransactions() throws Exception {
		String accessToken = obtainAccessToken("bob", "cnam");

		mockMvc.perform(get("/transactions/123456").header("Authorization", "Bearer " + accessToken)
				.accept("application/json;charset=UTF-8")).andExpect(status().isOk())
				.andExpect(status().is2xxSuccessful());
	}

	private String obtainAccessToken(String username, String password) throws Exception {
		String client_id ="betterbanking-app";
		String grant_type = "password";
		String scope = "betterbanking-app";
//	    ResultActions result = mockMvc
//	                		.perform(post(new URI(uri))
//	                		.header("Content-Type", "application/x-www-form-urlencoded")
//	                		.content("grant_type="+grant_type+"&client_id="+client_id+"&username="+username+"&password="+password+"&scope="+scope)
//	                		.accept("application/json;charset=UTF-8"))
//	                		.andExpect(status().isOk());
//	    String resultString = result.andReturn().getResponse().getContentAsString();
	        
		HttpResponse<JsonNode> response = Unirest.post(uri)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.field("grant_type", grant_type)
				.field("client_id", client_id)
				.field("username", username)
				.field("password", password)
				.field("scope", scope)
				.asJson();
		String resultString = (String) response.getBody().toString();
		System.err.println(resultString);
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
	}

}
