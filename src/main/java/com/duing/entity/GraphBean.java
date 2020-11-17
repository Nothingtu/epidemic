package com.duing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraphBean {

    private String date;
    private Integer confirm;
    private Integer heal;
    private Integer dead;

}
