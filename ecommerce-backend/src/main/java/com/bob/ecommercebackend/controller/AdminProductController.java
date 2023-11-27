package com.bob.ecommercebackend.controller;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.model.Product;
import com.bob.ecommercebackend.request.CreateProductRequest;
import com.bob.ecommercebackend.response.ApiResponse;
import com.bob.ecommercebackend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest request) {
        Product product = productService.createProduct(request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException {
        productService.deleteProduct(productId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Product deleted successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProductsHandler() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProductHandler(@RequestBody Product request, @PathVariable Long productId) throws ProductException {
        Product product = productService.updateProduct(productId, request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProductHandler(@RequestBody CreateProductRequest[] requests) {
        for (CreateProductRequest request : requests) {
            productService.createProduct(request);
        }

        ApiResponse response = new ApiResponse();
        response.setMessage("Products created successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
