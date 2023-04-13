package com.exp.demo.apidemo.model;

import lombok.Getter;
import lombok.Setter;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Getter
@Setter
public class OperateResult {
    int errorCode;
    String message;
}
