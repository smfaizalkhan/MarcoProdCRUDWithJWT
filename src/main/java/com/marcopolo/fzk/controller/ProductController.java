package com.marcopolo.fzk.controller;

import com.marcopolo.fzk.dto.ProductDTO;
import com.marcopolo.fzk.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> fetchAllProducts(){
        return productService.fetchAllProducts();
    }


    @PostMapping("/products")
    public ProductDTO updateProduct(@RequestParam("imageFile") MultipartFile file, @RequestParam("product") String prod){
        return productService.update(prod,file);
    }

    @GetMapping("/products/{id}")
    public ProductDTO fethProductById(@PathVariable UUID id){
        return productService.fetchProductById(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable UUID id) {
          productService.deleteById(id);
    }
}
