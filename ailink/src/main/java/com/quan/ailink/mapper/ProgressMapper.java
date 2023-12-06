package com.quan.ailink.mapper;

import com.quan.ailink.entity.Progress;
import com.quan.ailink.entity.ProgressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgressMapper {
    int countByExample(ProgressExample example);

    int deleteByExample(ProgressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Progress record);

    int insertSelective(Progress record);

    List<Progress> selectByExample(ProgressExample example);

    Progress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Progress record, @Param("example") ProgressExample example);

    int updateByExample(@Param("record") Progress record, @Param("example") ProgressExample example);

    int updateByPrimaryKeySelective(Progress record);

    int updateByPrimaryKey(Progress record);
}