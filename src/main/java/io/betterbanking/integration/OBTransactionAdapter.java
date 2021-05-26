package io.betterbanking.integration;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.acme.banking.model.OBTransaction6;

import io.betterbanking.domain.model.Transaction;

@Component
public class OBTransactionAdapter {

    public Transaction adapt(final OBTransaction6 transaction6) {
        return transactionFunction.apply(transaction6);
    }

    private Function<OBTransaction6, Transaction> transactionFunction = obTransaction6 -> {
        var t = new Transaction();
        t.setAccountNumber(Integer.valueOf(obTransaction6.getAccountId()));

        var amount = lift(obTransaction6, o -> Double.valueOf(o.getAmount().getAmount()));
        var fx = lift(obTransaction6, o -> o.getCurrencyExchange().getExchangeRate().doubleValue());
        t.setAmount(amount.orElse(0.0) * fx.orElse(1.0));

        lift(obTransaction6, o -> obTransaction6.getCurrencyExchange().getUnitCurrency())
                .ifPresent(t::setCurrency);
        lift(obTransaction6, o -> obTransaction6.getCreditDebitIndicator().toString())
                .ifPresent(t::setType);
        lift(obTransaction6, o -> obTransaction6.getMerchantDetails().getMerchantName())
                .ifPresent(t::setMerchantName);
        return t;
    };

    private <T> Optional<T> lift(final OBTransaction6 transaction6, Function<OBTransaction6, T> func) {
        try {
            return Optional.of(func.apply(transaction6));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
