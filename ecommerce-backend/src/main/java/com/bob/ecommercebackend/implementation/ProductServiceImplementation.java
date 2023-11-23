package com.bob.ecommercebackend.implementation;

import com.bob.ecommercebackend.exception.ProductException;
import com.bob.ecommercebackend.model.Category;
import com.bob.ecommercebackend.model.Product;
import com.bob.ecommercebackend.repository.CategoryRepository;
import com.bob.ecommercebackend.repository.ProductRepository;
import com.bob.ecommercebackend.request.CreateProductRequest;
import com.bob.ecommercebackend.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImplementation implements ProductService {
    private ProductRepository productRepository;
    private CustomUserServiceImplementation userService;
    private CategoryRepository categoryRepository;

    public ProductServiceImplementation(ProductRepository productRepository, CustomUserServiceImplementation userService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest request) {
        Category topLavel = categoryRepository.findByName(request.getTopLavelCategory());

        if (topLavel == null) {
            Category  topLavelCategory = new Category();
            topLavelCategory.setName(request.getTopLavelCategory());
            topLavelCategory.setLevel(1);
            categoryRepository.save(topLavelCategory);
        }

        Category secondLavel = categoryRepository
                .findByNameAndParentCategory(request.getSecondLavelCategory(), topLavel.getName());

        if (secondLavel == null) {
            Category  secondLavelCategory = new Category();
            secondLavelCategory.setName(request.getTopLavelCategory());
            secondLavelCategory.setParentCategory(topLavel);
            secondLavelCategory.setLevel(2);
            categoryRepository.save(secondLavelCategory);
        }

        Category thirdLavel = categoryRepository
                .findByNameAndParentCategory(request.getSecondLavelCategory(), topLavel.getName());

        if (secondLavel == null) {
            Category  thirdLavelCategory = new Category();
            thirdLavelCategory.setName(request.getTopLavelCategory());
            thirdLavelCategory.setParentCategory(topLavel);
            thirdLavelCategory.setLevel(2);
            categoryRepository.save(thirdLavelCategory);
        }

        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setColor(request.getColor());
        product.setDescription(request.getDescription());
        product.setDiscountedPrice(request.getDiscountedPrice());
        product.setDiscountPercent(request.getDiscountPercent());
        product.setImageUrl(request.getImageUrl());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setSizes(request.getSizes());
        product.setQuantity(request.getQuantity());
        product.setCategory(thirdLavel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.deleteById(productId);
        return "Product deleted successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product request) throws ProductException {
        Product product = findProductById(productId);

        if (request.getQuantity() != 0) {
            product.setQuantity(request.getQuantity());
        }

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new ProductException("Couldn't find product with id - " + productId);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, Integer minPrice,     Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

        if (!colors.isEmpty()) {
            products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }

        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("aut_of_stock")) {
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }

        int startIndex =  (int) pageable.getOffset();
        int eneIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        return null;
    }
}
