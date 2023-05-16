package com.ggu.exchange.api.external;

import com.ggu.exchange.config.CurrencyProperties;
import com.ggu.exchange.domain.Amount;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableRetry
public class LiveCurrencyApi {

    private static final String PREFIX = "USD";

    private final RestTemplate restTemplate;
    private final CurrencyProperties currencyProperties;

    public LiveCurrencyApi(RestTemplate restTemplate, CurrencyProperties currencyProperties) {
        this.restTemplate = restTemplate;
        this.currencyProperties = currencyProperties;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public Amount findExchangeRateByCountry(String country) {
        final var response = restTemplate.getForObject(createApiUrl(), LiveCurrencyResponse.class);
        final var quotes = response.getQuotes();
        final var exchangeRate = quotes.get(PREFIX + country);

        return new Amount(exchangeRate);
    }

    @Recover
    public Amount recover(Exception e, String country) {
        // TODO

        return null;
    }

    private String createApiUrl() {
        return currencyProperties.getUrl() +
                "?access_key=" + currencyProperties.getAccessKey() +
                "&source=" + currencyProperties.getSource() +
                "&currencies=" + currencyProperties.getCurrencies();
    }
}
