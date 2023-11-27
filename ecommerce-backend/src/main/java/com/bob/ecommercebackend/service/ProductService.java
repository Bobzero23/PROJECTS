package com.bob.ecommercebackend.service;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.model.Product;
import com.bob.ecommercebackend.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product createProduct (CreateProductRequest request);
    public void deleteProduct (Long productId) throws ProductException;
    public Product updateProduct (Long productId, Product request) throws ProductException;
    public Product findProductById (Long productId) throws ProductException;
    public List<Product> findProductByCategory (String category);
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes,
                                        Integer minPrice, Integer maxPrice, Integer minDiscount,
                                        String sort, String stock, Integer pageNumber, Integer pageSize);

    public List<Product> findAllProducts();
}
