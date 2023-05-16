package com.ggu.exchange.domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Amount {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final BigDecimal amount;

    public Amount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String convert() {
        return decimalFormat.format(amount);
    }

    public Amount multiply(BigDecimal sendingAmount) {
        return new Amount(this.amount.multiply(sendingAmount));
    }
}
