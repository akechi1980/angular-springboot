package com.exp.demo.apidemo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Getter
@Setter
public class ResponseQuery {
    int code;
    int page;
    int size;
    int total;
    List<?> lst;
}
