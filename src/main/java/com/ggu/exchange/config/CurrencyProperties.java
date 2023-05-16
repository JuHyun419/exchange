package com.ggu.exchange.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Validated
@ConstructorBinding
@ConfigurationProperties("currency")
public class CurrencyProperties {

    @NotBlank
    private final String accessKey;

    @NotBlank
    private final String url;

    @NotBlank
    private final String source;

    @NotBlank
    private final String currencies;

    public CurrencyProperties(String accessKey, String url, String source, String currencies) {
        this.accessKey = accessKey;
        this.url = url;
        this.source = source;
        this.currencies = currencies;
    }
}
