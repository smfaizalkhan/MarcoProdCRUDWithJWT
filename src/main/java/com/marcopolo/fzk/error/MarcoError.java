package com.marcopolo.fzk.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarcoError {
    private String message;
    private String errorCode;
}
