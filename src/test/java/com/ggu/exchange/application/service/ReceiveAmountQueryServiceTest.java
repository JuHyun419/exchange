package com.ggu.exchange.application.service;

import com.ggu.exchange.api.external.LiveCurrencyApi;
import com.ggu.exchange.domain.Amount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReceiveAmountQueryServiceTest {

    @Mock
    private LiveCurrencyApi liveCurrencyApi;

    @InjectMocks
    private ReceiveAmountQueryService service;

    @Test
    void 수취금액을_구한다() {
        final var amount = createMockAmount();

        given(liveCurrencyApi.findExchangeRateByCountry(any()))
                .willReturn(amount);

        final var actual = service.receiveAmount("KRW", new BigDecimal(100));

        assertThat(actual).isEqualTo("134,251.00");
    }

    private Amount createMockAmount() {
        return new Amount(new BigDecimal("1342.51"));
    }
}
