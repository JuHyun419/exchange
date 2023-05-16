package com.ggu.exchange.api.external;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@ToString
public class LiveCurrencyResponse {

    private boolean success;
    private String terms;
    private String privacy;
    private int timestamp;
    private String source;
    private Map<String, BigDecimal> quotes;
}
