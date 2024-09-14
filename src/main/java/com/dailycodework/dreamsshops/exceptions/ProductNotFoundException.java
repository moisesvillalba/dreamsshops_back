package com.dailycodework.dreamsshops.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private final Long productId;

    public ProductNotFoundException(Long productId) {
        super("Producto con ID " + productId + " no encontrado");
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
