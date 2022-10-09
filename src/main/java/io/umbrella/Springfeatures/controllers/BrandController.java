package io.umbrella.Springfeatures.controllers;

import io.umbrella.Springfeatures.models.Brand;
import io.umbrella.Springfeatures.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public List<Brand> getAll() {
        return brandService.getAllBrands();
    }

    @GetMapping("/brand/{id}")
    public Brand findOne(@PathVariable String id) {
        return brandService.findById(id);
    }

    @PostMapping("/brand")
    public Brand create(@RequestBody Brand brand) {
        if (brand.getId() != null) {
            throw new RuntimeException("new brand can not has an id");
        }
        return brandService.save(brand);
    }

    @PutMapping("/brand")
    public Brand update(@RequestBody Brand brand) {
        if (brand.getId() == null) {
            throw new RuntimeException("brand must have an id");
        }
        return brandService.save(brand);
    }

    @DeleteMapping("/brand/{id}")
    public void delete(@PathVariable String id) {
        brandService.deleteById(id);
    }
}
