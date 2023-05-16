package com.ggu.exchange.application.service;

import com.ggu.exchange.api.external.LiveCurrencyApi;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateQueryService {

    private final LiveCurrencyApi currencyApi;

    public ExchangeRateQueryService(LiveCurrencyApi currencyApi) {
        this.currencyApi = currencyApi;
    }

    public String getExchangeRate(String country) {
        final var exchangeRate = currencyApi.findExchangeRateByCountry(country);

        return exchangeRate.convert();
    }
}
