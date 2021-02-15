package com.marcopolo.fzk.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private UUID id;
    private String title;
    private String description;
    private String fileName;
    private  byte[] image;
}
