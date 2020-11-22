package com.duing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MapBean implements Serializable {
    private String name;
    private int value;
}
