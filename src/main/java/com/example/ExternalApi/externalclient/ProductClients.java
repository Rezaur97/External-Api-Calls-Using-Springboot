package com.example.ExternalApi.externalclient;

import com.example.ExternalApi.io.ProductClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "productclient",url = "https://fakestoreapi.com")
public interface ProductClients {

    @GetMapping("/products")
    List<ProductClientResponse> getAllProductByFeign();
}
