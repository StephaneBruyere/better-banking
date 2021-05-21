package com.betterBanking.domain.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.betterBanking.domain.model.controller.TransactionController;

@SpringBootTest
class TransactionComponentTest {
	
	@Autowired
	TransactionController transactionController;

	@Value("${server.port}")
    private int port;

    @Test
    public void testApplicationEndToEnd() {
        given().standaloneSetup(transactionController)
                .when()
                .get(String.format("http://localhost:%s/transactions/1234567", port))
                .then()
                .statusCode(Matchers.is(200));
    }

}
