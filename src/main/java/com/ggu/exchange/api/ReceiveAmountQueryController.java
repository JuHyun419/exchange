package com.ggu.exchange.api;

import com.ggu.exchange.application.service.ReceiveAmountQueryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@RestController
@RequestMapping("/v1")
public class ReceiveAmountQueryController {

    private final ReceiveAmountQueryService service;

    public ReceiveAmountQueryController(ReceiveAmountQueryService service) {
        this.service = service;
    }

    @GetMapping("/currency/amount")
    public ResponseEntity<String> receiveAmount(
            @Valid @ModelAttribute ReceivingAmountRequest request
    ) {
        return ResponseEntity.ok(
                service.receiveAmount(request.getReceiveCountry(), request.getAmount())
        );
    }

    @Getter
    @Setter
    static class ReceivingAmountRequest {
        @NotBlank
        private String sendCountry;

        @NotBlank
        private String receiveCountry;

        @Min(0)
        @Max(10000)
        private BigDecimal amount;
    }
}
