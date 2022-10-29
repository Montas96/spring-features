package io.umbrella.Springfeatures.service;

import io.umbrella.Springfeatures.repository.BrandRepository;
import io.umbrella.Springfeatures.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandRepository brandRepository;

    @Test
    void getAllBrands() {
        brandService.getAllBrands();
        Mockito.verify(brandRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Throw exception if ID is equal to null")
    void verifyThatFindByIdWillThrowExceptionIfIdIsNull() {
        //brandService.findById(null);
        Assertions.assertThrows(RuntimeException.class, ()-> brandService.findById(null));
        Mockito.verify(brandRepository, Mockito.never()).findById(ArgumentMatchers.anyString());
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }
}