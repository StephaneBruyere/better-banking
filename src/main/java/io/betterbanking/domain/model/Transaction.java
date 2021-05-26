package io.betterbanking.domain.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
	
	private String type;
	private Date date;
    private int accountNumber;
    private String currency;
    private double amount;
    private String merchantName;
    private String merchantLogo;
}
	
	