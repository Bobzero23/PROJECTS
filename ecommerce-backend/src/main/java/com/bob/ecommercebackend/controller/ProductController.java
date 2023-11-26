package com.bob.ecommercebackend.controller;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.model.Product;
import com.bob.ecommercebackend.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,
            @RequestParam List<String> color, @RequestParam List<String> size, @RequestParam Integer minPrice,
            @RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String sort,
            @RequestParam String stock, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

        Page<Product> response = productService .getAllProducts(
                category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);

        System.out.println("Complete products");

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String q) {
//        List<Product> products = productService.searchProduct(q);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
}
