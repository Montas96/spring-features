package io.umbrella.Springfeatures.service.impl;

import io.umbrella.Springfeatures.models.Product;
import io.umbrella.Springfeatures.repository.ProductRepository;
import org.assertj.core.api.BDDAssumptions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Tag("productService")
@DisplayName("product service implementation ")
class ProductServiceImplTest {

    static List<Product> products = new ArrayList<>();
    @BeforeAll
    static void  initialize() {
        System.out.println(" initializing data");
        Product product = new Product("P1");
        product.setCategoryId("C1");
        product.setBrandId("B1");
        products.add(product);

        product = new Product("P2");
        product.setCategoryId("C1");
        product.setBrandId("B2");
        products.add(product);

        product = new Product("P3");
        product.setCategoryId("C2");
        product.setBrandId("B2");
        products.add(product);
    }

    @Mock
    private ProductRepository productRepository;

    @Test
    void getAllProducts() {
        //given

        BDDMockito.given(productRepository.findAll()).willReturn(products);

        //when
        List<Product> productList = productRepository.findAll();

        //then
        assertArrayEquals(products.toArray(),productList.toArray());
        BDDMockito.then(productRepository).should(Mockito.times(1)).findAll();
        BDDMockito.then(productRepository).shouldHaveNoMoreInteractions();

    }

    @Test
    void getAllProductsByCategoryId() {
        //Given
        List<Product> productList = products.stream().filter(product -> product.getCategoryId().equals("C1")).collect(Collectors.toList());
        BDDMockito.given(productRepository.findAllByCategoryId(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .willReturn(new PageImpl<>(productList));

        //When
        Page<Product> allByCategoryId = productRepository.findAllByCategoryId("C1", Pageable.unpaged());

        //Then
        assertEquals(2, allByCategoryId.getSize());
        BDDMockito.then(productRepository).should(Mockito.times(1)).findAllByCategoryId(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        BDDMockito.then(productRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void getAllProductsByCategoryIdThrowingException() {
        //Given
        Mockito.doThrow(new RuntimeException("Argument Invalid")).when(productRepository).findAllByCategoryId(ArgumentMatchers.any(),ArgumentMatchers.any());
        Assertions.assertThrows(RuntimeException.class , ()-> productRepository.findAllByCategoryId(null, Pageable.unpaged()));
    }

    @Test
    void getAllProductsByBrandId() {

        //given
        List<Product> productList = products.stream().filter(product -> product.getBrandId().equals("B2")).collect(Collectors.toList());
        BDDMockito.given(productRepository.findAllByBrandId(ArgumentMatchers.anyString(), ArgumentMatchers.any())).willReturn(new PageImpl<>(productList));

        Page<Product> productPage = productRepository.findAllByBrandId("B2", Pageable.unpaged());

        //then
        assertEquals(2, productPage.getSize());
        BDDMockito.then(productRepository).should(Mockito.times(1)).findAllByBrandId(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        BDDMockito.then(productRepository).shouldHaveNoMoreInteractions();

    }

    @Test
    void getProductById() {

    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}