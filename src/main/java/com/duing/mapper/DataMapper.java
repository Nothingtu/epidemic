package com.duing.mapper;


import com.duing.entity.DataBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface DataMapper {

   @Select("SELECT ID ,NAME,CONFIRM,NOWCONFIRM,DEAD,HEAL FROM EPIDEMIC")
    List<DataBean> selectList();

   @Update("INSERT INTO EPIDEMIC VALUES(null,#{name},#{confirm},#{nowConfirm},#{dead},#{heal}) ")
    int insert(DataBean dataBean);

   @Delete("DELETE FROM EPIDEMIC")
    int delete();

}
