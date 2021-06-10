package io.betterbanking.domain.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 3248296685673130582L;
	
	private String type;
	private Date date;
    private int accountNumber;
    private String currency;
    private double amount;
    private String merchantName;
    private String merchantLogo;
}
	
	