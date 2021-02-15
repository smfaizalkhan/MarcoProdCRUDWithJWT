package com.marcopolo.fzk.dominamapper;

import com.marcopolo.fzk.domain.Product;
import com.marcopolo.fzk.dto.ProductDTO;
import com.marcopolo.fzk.factory.DomainFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductToProductDTOMapperTest {

    @InjectMocks
    private ProductToProductDTOMapper productToProductDTOMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void apply() throws IOException {
        ProductDTO productDTO = DomainFactory.getTestProductDTO();
        Product product = DomainFactory.getTestProduct();
        assertThat(productToProductDTOMapper.apply(product)).isInstanceOf(ProductDTO.class);
    }
}