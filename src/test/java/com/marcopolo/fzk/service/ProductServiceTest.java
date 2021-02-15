package com.marcopolo.fzk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcopolo.fzk.domain.Product;
import com.marcopolo.fzk.dominamapper.ProductDTOToProductMapper;
import com.marcopolo.fzk.dominamapper.ProductToProductDTOMapper;
import com.marcopolo.fzk.dto.ProductDTO;
import com.marcopolo.fzk.factory.DomainFactory;
import com.marcopolo.fzk.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {


    @InjectMocks
    private ProductService productService;
    @Mock
    private  ProductRepo productRepo;
    @Mock
    private ProductToProductDTOMapper productToProductDTOMapper;
    @Mock
    private ProductDTOToProductMapper productDTOToProductMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        reset(productDTOToProductMapper,productToProductDTOMapper);
    }

    @Test
    void fetchAllProducts() throws IOException {

        List<Product> listofProducts = Arrays.asList(
               DomainFactory.getTestProduct());
        ProductDTO productDTO = DomainFactory.getTestProductDTO();
        when(productRepo.findAll()).thenReturn(listofProducts);
        when(productToProductDTOMapper.apply(any(Product.class))).thenReturn(productDTO);
        assertThat((productService.fetchAllProducts().get(0))).isInstanceOf(ProductDTO.class);
    }



    @Test
    void fetchProductById() throws IOException {
        Product product = DomainFactory.getTestProduct();
        ProductDTO productDTO = DomainFactory.getTestProductDTO();
        when(productRepo.findById(any(UUID.class))).thenReturn(Optional.of(product));
        when(productToProductDTOMapper.apply(any(Product.class))).thenReturn(productDTO);
        assertThat((productService.fetchProductById(product.getId())));
    }

    @Test
    void deleteById() throws IOException {
        Product product = DomainFactory.getTestProduct();
        ProductDTO productDTO = DomainFactory.getTestProductDTO();
        when(productRepo.existsById(any(UUID.class))).thenReturn(true);
        when(productRepo.findById(any(UUID.class))).thenReturn(Optional.of(product));
         productService.deleteById(productDTO.getId());
        verify(productRepo,times(1)).deleteById(any(UUID.class));
    }


    @Test
    void getProductDTO() throws IOException {
        Product product = DomainFactory.getTestProduct();
        ProductDTO productDTO = DomainFactory.getTestProductDTO();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imageFile",productDTO.getFileName(),
                String.valueOf(MediaType.TEXT_PLAIN),productDTO.getImage());
        String productDTOasString  =  new ObjectMapper().writeValueAsString(productDTO);
        when(productDTOToProductMapper.apply(any(ProductDTO.class))).thenReturn(product);
        when(productRepo.save(any(Product.class))).thenReturn(product);
        when(productToProductDTOMapper.apply(any(Product.class))).thenReturn(productDTO);
        assertThat(productService.getProductDTO(productDTOasString,mockMultipartFile));
    }
}