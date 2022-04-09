package com.jwt.token.sample.loginApp.domain.model;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Data
public class ReportExcel<T> {

    private String sheetName;
    private String title;
    private Map<String, Object> headers;
    private Map<String, Object> footers;
    private Map<String, Function<T, ?>> dataColumnMappers;
    private List<T> data;
}
