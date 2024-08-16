package com.amsidh.mvc.model;

import lombok.Data;

@Data
public class SortBy {
    private String key;
    private SortOrder order;
}
