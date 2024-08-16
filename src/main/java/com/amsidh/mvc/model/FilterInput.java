package com.amsidh.mvc.model;

import lombok.Data;

import java.util.List;

@Data
public class FilterInput {
    private FilterType filterType;
    private List<String> filterValues;
}
