package com.duing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor

public class GraphBarBean implements Serializable,Comparable<GraphBarBean> {
    private String name;
    private Integer confirm;

    @Override
    public int compareTo(GraphBarBean o) {
        return o.confirm - this.confirm;
    }
}
