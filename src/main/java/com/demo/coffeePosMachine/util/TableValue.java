package com.demo.coffeePosMachine.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableValue {
    Y("Y"),
    N("N");

    private final String value;
}
