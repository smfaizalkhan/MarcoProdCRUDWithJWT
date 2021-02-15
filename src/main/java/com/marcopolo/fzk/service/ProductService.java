package com.marcopolo.fzk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcopolo.fzk.domain.Product;
import com.marcopolo.fzk.dominamapper.ProductDTOToProductMapper;
import com.marcopolo.fzk.dominamapper.ProductToProductDTOMapper;
import com.marcopolo.fzk.dto.ProductDTO;
import com.marcopolo.fzk.exception.ProductNotFoundException;
import com.marcopolo.fzk.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductToProductDTOMapper productToProductDTOMapper;
    private final ProductDTOToProductMapper productDTOToProductMapper;

    @PostConstruct
    public void initData() throws IOException {


        List<Product>   listofProducts = Arrays.asList(
                    new Product(UUID.randomUUID(),"SamsungS20","Samsung Mobile","S20.jpg",
                            Paths.get("src","main","resources/images/S20.jpg").toAbsolutePath().toString()),
                     new Product(UUID.randomUUID(),"SamsungS10","Samsung Mobile","S10.jpg",
                             Paths.get("src","main","resources/images/S10.jpg").toAbsolutePath().toString()),
                     new Product(UUID.randomUUID(),"Apple","Apple Mobile","iPhone.jpg",
                             Paths.get("src","main","resources/images/iPhone.jpg").toAbsolutePath().toString()));

        productRepo.saveAll(listofProducts);
    }
    public List<ProductDTO> fetchAllProducts() {
        List<ProductDTO> productList = productRepo.findAll().stream()
                .map(product -> productToProductDTOMapper
                        .apply(product))
                .collect(Collectors.toList());
        return productList;
    }


    public ProductDTO update(String prod, MultipartFile file) {
        return getProductDTO(prod, file);
    }



    public ProductDTO fetchProductById(UUID id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        return  productToProductDTOMapper.apply(product);
    }

    public void  deleteById(UUID id) {
        if(!productRepo.existsById(id))
        throw new ProductNotFoundException("Product Not Found Exception");
        Product product = productRepo.findById(id).get();
        String filePath = product.getPath();
        File file = Paths.get(filePath).toFile();
        if(file.exists()) file.delete();
        productRepo.deleteById(id);
    }

    public ProductDTO save(String prod,MultipartFile file) {
        return getProductDTO(prod, file);
    }

    public ProductDTO getProductDTO(String prod, MultipartFile file) {
        ProductDTO  productDTO = null;

        try {
            productDTO = new ObjectMapper().readValue(prod, ProductDTO.class);
           if(!file.getOriginalFilename().isEmpty()) productDTO.setFileName(file.getOriginalFilename());
            productDTO.setImage(file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        Product product = productDTOToProductMapper.apply(productDTO);
        Product savedProduct = productRepo.save(product);
        return productToProductDTOMapper.apply(savedProduct);
    }
}
