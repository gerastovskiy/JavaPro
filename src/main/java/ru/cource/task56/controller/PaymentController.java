package ru.cource.task56.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import ru.cource.task56.exception.ErrorMessage;
import ru.cource.task56.model.Payment;
import ru.cource.task56.model.Product;
import ru.cource.task56.service.PaymentService;
import java.util.List;

@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "True Payment Controller",
                version = "greatest",
                description = "My ultra API"
        ),
        servers = {
                @Server(
                        description = "test",
                        url = "localhost:8080"),
                @Server(
                        description = "prod",
                        url = "xxx.xxx:8080"),
        }
)
public class PaymentController {
    private final RestClient restClient;
    private final String getAllProductsUri;
    private final String getProductByIdUri;
    private final String debitProductUri;
    private final String creditProductUri;
    private final PaymentService paymentService;

    public PaymentController(RestClient restClient,
                             PaymentService paymentService,
                             @Value("${services-uri.get-all-products}") String getAllProductsUri,
                             @Value("${services-uri.get-product-by-id}") String getProductByIdUri,
                             @Value("${services-uri.debit-product}") String debitProductUri,
                             @Value("${services-uri.credit-product}") String creditProductUri) {
        this.restClient = restClient;
        this.paymentService = paymentService;
        this.getAllProductsUri = getAllProductsUri;
        this.getProductByIdUri = getProductByIdUri;
        this.debitProductUri = debitProductUri;
        this.creditProductUri = creditProductUri;
    }

    @GetMapping(value = "/payment/{id}/getAllProducts")
    public List<Product> getAllProducts(@PathVariable("id") Long id){
        var response = restClient
                .get()
                .uri(getAllProductsUri+id)
                .retrieve()
                .body(List.class);

        return response;
    }

    @GetMapping(value = "/payment/getProduct")
    public Product getProductbyId(@RequestParam("id") Long id){
        var response = restClient
                .get()
                .uri(getProductByIdUri+id)
                .retrieve()
                .toEntity(Product.class);

        return response.getBody();
    }

    @Operation(summary = "Операция списания",
            responses = {
                    @ApiResponse(description = "Продукт клиента после проведения операции",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Данные не найдены / The balance on the account is insufficient",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))}
    )
    @PostMapping(value = "/payment/debit")
    public Product debitPayment(@RequestBody @Valid Payment payment){
        var product = getProductbyId(payment.getProductId());
        paymentService.checkDebit(product, payment.getAmount());
        var response = restClient
                .put()
                .uri(String.format(debitProductUri+"?id=%d&amount=%s",payment.getProductId(),payment.getAmount()))
                .retrieve()
                .toEntity(Product.class);

        return getProductbyId(payment.getProductId());
    }

    @Operation(summary = "Операция зачисления",
            responses = {
                    @ApiResponse(description = "Продукт клиента после проведения операции",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Данные не найдены",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))}
    )
    @PostMapping(value = "/payment/credit")
    public Product creditPayment(@RequestBody @Valid Payment payment){
        var product = getProductbyId(payment.getProductId());
        paymentService.checkCredit(product, payment.getAmount());
        var response = restClient
                .put()
                .uri(String.format(creditProductUri+"?id=%d&amount=%s",payment.getProductId(),payment.getAmount()))
                .retrieve()
                .toEntity(Product.class);

        return getProductbyId(payment.getProductId());
    }
}
