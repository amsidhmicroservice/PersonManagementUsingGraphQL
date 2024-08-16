package com.amsidh.mvc.model;

public enum FilterType {
    eq, // Equals
    nq, // Not Equals
    contains,
    notContains,
    gte, // Greater Than or Equal To
    lte, // Less Than or Equal To
    eqIgnoreCase // Equals Ignore Case
}
