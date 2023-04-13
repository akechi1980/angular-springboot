package com.exp.demo.apidemo.model;

import lombok.Getter;
import lombok.Setter;

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Getter
@Setter
public class InfoModel {
    private String version;
    private String datetime;
}
