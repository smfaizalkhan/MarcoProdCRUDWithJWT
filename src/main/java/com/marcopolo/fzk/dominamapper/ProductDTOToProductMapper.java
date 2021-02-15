package com.marcopolo.fzk.dominamapper;

import com.marcopolo.fzk.domain.Product;
import com.marcopolo.fzk.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductDTOToProductMapper implements Function<ProductDTO, Product> {

    private final ResourceLoader resourceLoader;
    @Override
    public Product apply(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        try {
            Path resourceDirectory = Paths.get("src","main","resources/images");
                File file = new File((resourceDirectory) + File.separator + productDTO.getFileName());
                FileUtils.writeByteArrayToFile(file, productDTO.getImage());
                product.setPath(file.getAbsolutePath());
        } catch (IOException e) {
            log.error("error in mapping {}",e);
        }


        return product;
    }
}
