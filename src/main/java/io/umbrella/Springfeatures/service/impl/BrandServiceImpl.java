package io.umbrella.Springfeatures.service.impl;

import io.umbrella.Springfeatures.models.Brand;
import io.umbrella.Springfeatures.repository.BrandRepository;
import io.umbrella.Springfeatures.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(String ID) {
        if(ID == null) throw  new RuntimeException("ID can not null");
        Optional<Brand> optionalBrand = brandRepository.findById(ID);
        if (!optionalBrand.isPresent()) {
            throw new RuntimeException("Brand can not be found");
        }
        return optionalBrand.get();
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteById(String id) {
        brandRepository.deleteById(id);
    }
}
