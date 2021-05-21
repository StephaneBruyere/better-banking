package com.betterBanking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
	
	private String type;
    private int accountNumber;
    private String currency;
    private Double amount;
    private String merchantName;
    private String merchantLogo;
}
	
	