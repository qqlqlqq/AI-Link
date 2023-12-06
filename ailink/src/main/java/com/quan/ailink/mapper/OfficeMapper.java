package com.quan.ailink.mapper;

import com.quan.ailink.entity.Office;
import com.quan.ailink.entity.OfficeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfficeMapper {
    int countByExample(OfficeExample example);

    int deleteByExample(OfficeExample example);

    int deleteByPrimaryKey(Long oid);

    int insert(Office record);

    int insertSelective(Office record);

    List<Office> selectByExample(OfficeExample example);

    Office selectByPrimaryKey(Long oid);

    int updateByExampleSelective(@Param("record") Office record, @Param("example") OfficeExample example);

    int updateByExample(@Param("record") Office record, @Param("example") OfficeExample example);

    int updateByPrimaryKeySelective(Office record);

    int updateByPrimaryKey(Office record);
}