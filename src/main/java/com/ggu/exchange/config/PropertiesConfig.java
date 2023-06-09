package com.ggu.exchange.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
        CurrencyProperties.class,
})
public class PropertiesConfig {

}
