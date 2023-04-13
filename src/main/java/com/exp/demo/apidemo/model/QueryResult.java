package com.exp.demo.apidemo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Getter
@Setter
public class QueryResult {
    int count;
    int page;
    int pageSize;
    List<?> lstResult;
}
