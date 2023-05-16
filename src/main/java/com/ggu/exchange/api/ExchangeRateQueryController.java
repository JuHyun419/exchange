package com.ggu.exchange.api;

import com.ggu.exchange.application.service.ExchangeRateQueryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/v1")
public class ExchangeRateQueryController {

    private final ExchangeRateQueryService service;

    public ExchangeRateQueryController(ExchangeRateQueryService service) {
        this.service = service;
    }

    @GetMapping("/currency/exchange")
    public ResponseEntity<String> getExchangeRate(
            @Valid @ModelAttribute ExchangeRateRequest request
    ) {
        return ResponseEntity.ok(
                service.getExchangeRate(request.getReceiveCountry())
        );
    }

    @Getter
    @Setter
    static class ExchangeRateRequest {
        @NotBlank
        private String sendCountry;

        @NotBlank
        private String receiveCountry;
    }
}
