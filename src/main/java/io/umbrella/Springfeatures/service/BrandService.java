package io.umbrella.Springfeatures.service;

import io.umbrella.Springfeatures.models.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrands();

    Brand findById(String ID);

    Brand save(Brand brand);

    void deleteById(String id);
}
