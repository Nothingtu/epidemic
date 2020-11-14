package com.duing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataBean {

    private Integer id;
    private String name;
    private Integer confirm;
    private Integer nowConfirm;
    private Integer dead;
    private Integer heal;
}
