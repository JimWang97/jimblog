package com.jimwang.jimblog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogException extends RuntimeException {

    private Integer code;
    private String msg;
}
