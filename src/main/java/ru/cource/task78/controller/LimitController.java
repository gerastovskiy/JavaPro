package ru.cource.task78.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import ru.cource.task78.model.Limit;
import ru.cource.task78.model.Payment;
import ru.cource.task78.model.Product;
import ru.cource.task78.service.LimitService;

@RestController
public class LimitController {
    private final RestClient restClient;
    private final String getProductByIdUri;
    private final String debitPaymentUri;
    private final String creditPaymentUri;
    private final LimitService limitService;

    public LimitController(RestClient restClient,
                             LimitService limitService,
                             @Value("${services-uri.get-payment-product-by-id}") String getProductByIdUri,
                             @Value("${services-uri.debit-payment}") String debitPaymentUri,
                             @Value("${services-uri.credit-payment}") String creditPaymentUri) {
        this.restClient = restClient;
        this.limitService = limitService;
        this.getProductByIdUri = getProductByIdUri;
        this.debitPaymentUri = debitPaymentUri;
        this.creditPaymentUri = creditPaymentUri;
    }

    public Product getProductbyId(@RequestParam("id") Long id){
        var response = restClient
                .get()
                .uri(getProductByIdUri+"?id="+id)
                .retrieve()
                .toEntity(Product.class);

        return response.getBody();
    }

    @GetMapping(value = "/limit/getLimit")
    public Limit getLimitbyUserId(@RequestParam("user_id") Long userId){
        return limitService.getLimit(userId);
    }

    @PostMapping(value = "/limit/credit")
    @Transactional
    public Limit creditPayment(@RequestBody @Valid Payment payment){
        var product = getProductbyId(payment.getProductId());
        var limit = limitService.creditLimit(product.getUserId(), payment.getAmount());
        var response = restClient
                .post()
                .uri(creditPaymentUri)
                .body(payment)
                .retrieve()
                .toEntity(Product.class);

        return limit;
    }

    @PostMapping(value = "/limit/debit")
    @Transactional
    public Limit debitPayment(@RequestBody @Valid Payment payment){
        var product = getProductbyId(payment.getProductId());
        var limit = limitService.debitLimit(product.getUserId(), payment.getAmount());
        var response = restClient
                .post()
                .uri(debitPaymentUri)
                .body(payment)
                .retrieve()
                .toEntity(Product.class);

        return limit;
    }
}
