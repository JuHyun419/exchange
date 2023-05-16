package com.ggu.exchange.application.service;

import com.ggu.exchange.api.external.LiveCurrencyApi;
import com.ggu.exchange.domain.Amount;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ExchangeRateQueryServiceTest {

    @Mock
    private LiveCurrencyApi liveCurrencyApi;

    @InjectMocks
    private ExchangeRateQueryService service;

    @ParameterizedTest
    @CsvSource(value = {"KRW:1219.00:1,219.00", "JPY:512.14:512.14", "PHP:242.12:242.12"}, delimiter = ':')
    void 환율을_구한다(String country, String amount, String expected) {
        given(liveCurrencyApi.findExchangeRateByCountry(country))
                .willReturn(new Amount(new BigDecimal(amount)));

        final var actual = service.getExchangeRate(country);

        assertThat(actual).isEqualTo(expected);
    }
}
