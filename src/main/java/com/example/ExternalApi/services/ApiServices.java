package com.example.ExternalApi.services;


import com.example.ExternalApi.externalclient.ProductClients;
import com.example.ExternalApi.io.ProductClientResponse;
import com.example.ExternalApi.io.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ApiServices {

    @Autowired
    private WebClient webClient;

    @Autowired
    private ProductClients productClients;

    private final String MY_API_KEY = "key";

    public ProductResponse getApiResponse(Long id){
        Mono<ProductResponse> apiResponseMono = webClient.get()
              .uri(uriBuilder ->
                uriBuilder.path("URL")
                        .queryParam("KEY",MY_API_KEY)
                        .build())
                .retrieve().
                bodyToMono(ProductResponse.class);

        return apiResponseMono.block(); // Use `.block()` to make it synchronous
    }

    public List<ProductResponse> getAllProducts(){
        Mono<List<ProductResponse>> allProduct = webClient.get()
                .uri("https://fakestoreapi.com/products")
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList();

        return allProduct.block(); // sync call
    }

    public List<ProductClientResponse> getAllProductByFeign(){
        return productClients.getAllProductByFeign();
    }
}
