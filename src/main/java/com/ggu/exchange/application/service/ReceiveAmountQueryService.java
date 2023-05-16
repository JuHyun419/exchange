package com.ggu.exchange.application.service;

import com.ggu.exchange.api.external.LiveCurrencyApi;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReceiveAmountQueryService {

    private final LiveCurrencyApi currencyApi;

    public ReceiveAmountQueryService(LiveCurrencyApi currencyApi) {
        this.currencyApi = currencyApi;
    }

    public String receiveAmount(String country, BigDecimal amount) {
        final var exchangeRate = currencyApi.findExchangeRateByCountry(country);
        final var receiveAmount = exchangeRate.multiply(amount);

        return receiveAmount.convert();
    }
}
