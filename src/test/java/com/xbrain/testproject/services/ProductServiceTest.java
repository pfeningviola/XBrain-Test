package com.xbrain.testproject.services;

import com.xbrain.testproject.models.entities.Product;
import com.xbrain.testproject.repositories.ProductRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    private static ProductService productService;

    private static ProductRepository productRepositoryMock = Mockito.mock(ProductRepository.class);

    @BeforeClass
    public static void init() {
        productService = new ProductService(productRepositoryMock);
    }

    @Test
    public void existProductShouldReturnFalse_when_ProductIdIsNotFound() {
        Long productId = 100L;

        when(productRepositoryMock.existsById(any())).thenReturn(false);

        boolean result = productService.existProduct(productId);
        assertFalse(result);

        verify(productRepositoryMock, Mockito.times(1)).existsById(productId);
    }

    @Test
    public void existProductShouldReturnFalse_when_ProductIdIsNull() {
        Long productId = null;

        boolean result = productService.existProduct(productId);
        assertFalse(result);
    }

    @Test
    public void existProductShouldReturnTrue_when_ProductIdIsFound() {
        Long productId = 1L;

        when(productRepositoryMock.existsById(any())).thenReturn(true);

        boolean result = productService.existProduct(productId);
        assertTrue(result);

        verify(productRepositoryMock, Mockito.times(1)).existsById(productId);
    }

    @Test
    public void findProductByIdShouldReturnProperResult_when_ProductIdIsFound() {
        Long productId = 1L;
        Product expectedProduct = new Product(500, "mesa");
        expectedProduct.setId(productId);
        Optional<Product> productOptional = Optional.of(expectedProduct);

        when(productRepositoryMock.findById(productId)).thenReturn(productOptional);

        Product resultProduct = productService.findProductById(productId);
        assertEquals(expectedProduct, resultProduct);

        verify(productRepositoryMock, Mockito.times(1)).findById(productId);
    }

    @Test
    public void findProductByIdShouldReturnNull_when_ProductIdIsNotFound() {
        Long productId = 100L;
        Product expectedProduct = null;

        when(productRepositoryMock.findById(productId)).thenReturn(Optional.empty());

        Product resultProduct = productService.findProductById(productId);
        assertEquals(expectedProduct, resultProduct);
        assertNull(resultProduct);

        verify(productRepositoryMock, Mockito.times(1)).findById(productId);
    }

    @Test
    public void findProductByIdShouldReturnNull_when_ProductIdIsNull() {
        Long productId = null;
        Product expectedProduct = null;

        Product resultProduct = productService.findProductById(productId);
        assertEquals(expectedProduct, resultProduct);
        assertNull(resultProduct);
    }
}
