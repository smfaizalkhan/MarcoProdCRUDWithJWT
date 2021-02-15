package com.marcopolo.fzk.factory;

import com.marcopolo.fzk.domain.Product;
import com.marcopolo.fzk.dto.ProductDTO;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class DomainFactory {

    private static final UUID uuid = UUID.randomUUID();

    public static ProductDTO getTestProductDTO() throws IOException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(uuid);
        File file = new File((getFileAbsPath())+ File.separator+"S20.jpg");
        productDTO.setFileName(file.getName());
        productDTO.setImage(FileUtils.readFileToByteArray(file));
        productDTO.setTitle("test Title");
        productDTO.setDescription("Test Description");
        return  productDTO;
    }

    private static String getFileAbsPath() {
        Path resourceDirectory = Paths.get("src","test","resources/images");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        return absolutePath;
    }

    public static Product getTestProduct() throws IOException {
        Product product = new Product();
        product.setId(uuid);
        product.setPath(getFileAbsPath());
        product.setDescription("Test Description");
        product.setTitle("test Title");
        return product;
    }
}
