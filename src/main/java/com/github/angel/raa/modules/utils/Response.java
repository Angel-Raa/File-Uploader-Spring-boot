package com.github.angel.raa.modules.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    private String message;
    private int code;

}
