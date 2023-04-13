package com.exp.demo.apidemo.model;

import lombok.Getter;
import lombok.Setter;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Getter
@Setter
public class ResponseObject {
    int code;
    String message;
    Object data;
}
