package com.marcopolo.fzk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcopolo.fzk.dto.ProductDTO;
import com.marcopolo.fzk.factory.DomainFactory;
import com.marcopolo.fzk.service.ProductService;
import com.marcopolo.fzk.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
@ActiveProfiles(value = "tst")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @MockBean
    private JwtUtil jwtUtil;



    @Test
    void should_return_200_and_ProductDTO_AllProducts() throws Exception {

        ProductDTO productDTO = DomainFactory.getTestProductDTO();
        when(productService.fetchAllProducts()).thenReturn(Arrays.asList(productDTO));
        this.mockMvc.perform(get("/api/products")).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].title", is(productDTO.getTitle())));
    }


    @Test
    void should_return_200_and_ProductDTO_updateProduct() throws Exception {
        ProductDTO productDTO = DomainFactory.getTestProductDTO();
        productDTO.setTitle("changedTitle");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("imageFile",productDTO.getFileName(),
                String.valueOf(MediaType.TEXT_PLAIN),productDTO.getImage());
        when(productService.update(anyString(),any(MultipartFile.class))).thenReturn(productDTO);
         String productDTOasString  =  new ObjectMapper().writeValueAsString(productDTO);
        this.mockMvc.perform(multipart("/api/products")
                             .file(mockMultipartFile).param("product",productDTOasString))
                 .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.title", is(productDTO.getTitle())));

    }

    @Test
    void should_return_200_and_ProductDTO_fethProductById() throws Exception {
        ProductDTO productDTO = DomainFactory.getTestProductDTO();
        when(productService.fetchProductById(any(UUID.class))).thenReturn(productDTO);
        this.mockMvc.perform(get("/api/products/"+UUID.randomUUID())).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.title", is(productDTO.getTitle())));
    }

    @Test
    void should_return_200_and_ProductDTO_deleteProduct() throws Exception {
        doNothing().when(productService).deleteById(any(UUID.class));
        this.mockMvc.perform(delete("/api/products/"+UUID.randomUUID()))
                .andExpect(status().is2xxSuccessful());
    }
}