package com.zc.dao;

import com.zc.model.MyTest;
import com.zc.model.MyTestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyTestMapper {
    int countByExample(MyTestExample example);

    int deleteByExample(MyTestExample example);

    int insert(MyTest record);

    int insertSelective(MyTest record);

    List<MyTest> selectByExample(MyTestExample example);

    int updateByExampleSelective(@Param("record") MyTest record, @Param("example") MyTestExample example);

    int updateByExample(@Param("record") MyTest record, @Param("example") MyTestExample example);
}