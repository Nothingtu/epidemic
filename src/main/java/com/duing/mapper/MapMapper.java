package com.duing.mapper;

import com.duing.entity.DataBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


public interface MapMapper {

    @Select("SELECT ID ,NAME,CONFIRM,NOWCONFIRM,DEAD,HEAL FROM EPIDEMIC")
    List<DataBean> selectList();
}
