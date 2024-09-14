package com.dailycodework.dreamsshops.service.product;

import com.dailycodework.dreamsshops.exceptions.ProductNotFoundException;
import com.dailycodework.dreamsshops.model.Product;
import com.dailycodework.dreamsshops.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> {
            logger.warn("Producto no encontrado con ID: {}", id);
            return new ProductNotFoundException(id);
        });
    }
}
