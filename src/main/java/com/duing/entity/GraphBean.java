package com.duing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class GraphBean implements Serializable {

    private String date;
    private Integer confirm;
    private Integer heal;
    private Integer dead;

}
