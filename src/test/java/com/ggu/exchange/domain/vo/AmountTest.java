package com.ggu.exchange.domain.vo;

import com.ggu.exchange.domain.Amount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {

    private Amount amount;

    @BeforeEach
    void setup() {
        amount = new Amount(new BigDecimal("1119.93"));
    }

    @Test
    void 금액_convert() {
        final var actual = amount.convert();

        assertThat(actual).isEqualTo("1,119.93");
    }

    @Test
    void 금액_multiply() {
        final var sendingAmount = new BigDecimal(100);

        final var actual = amount.multiply(sendingAmount);

        assertThat(actual.getAmount()).isEqualTo(new BigDecimal("111993.00"));
    }

}