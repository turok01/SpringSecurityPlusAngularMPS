package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.entities.Option;
import org.springframework.core.convert.converter.Converter;

public class OptionConverter implements Converter<String, Option> {
    @Override
    public Option convert(String source) {
        return Option.valueOf(source); // на самом деле, посложнее, но это не суть
    }
}
