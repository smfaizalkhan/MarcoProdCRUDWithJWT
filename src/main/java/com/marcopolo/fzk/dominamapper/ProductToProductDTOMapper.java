package com.marcopolo.fzk.dominamapper;

import com.marcopolo.fzk.domain.Product;
import com.marcopolo.fzk.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductToProductDTOMapper implements Function<Product,ProductDTO> {


    @Override
    public ProductDTO apply(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setFileName(product.getFileName());
        File file = null;
        System.out.println("product"+product);
        try {
        
             file = new File(product.getPath());
           if(!Objects.isNull(file) && file.exists()) productDTO.setImage(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            log.error("Error in mapping {}",e);
        }
        return productDTO;
    }
}
