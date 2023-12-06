package com.quan.ailink.mapper;

import com.quan.ailink.entity.OfficeUser;
import com.quan.ailink.entity.OfficeUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeUserMapper {
    int countByExample(OfficeUserExample example);

    int deleteByExample(OfficeUserExample example);

    int deleteByPrimaryKey(Long ouid);

    int insert(OfficeUser record);

    int insertSelective(OfficeUser record);

    List<OfficeUser> selectByExample(OfficeUserExample example);

    OfficeUser selectByPrimaryKey(Long ouid);

    int updateByExampleSelective(@Param("record") OfficeUser record, @Param("example") OfficeUserExample example);

    int updateByExample(@Param("record") OfficeUser record, @Param("example") OfficeUserExample example);

    int updateByPrimaryKeySelective(OfficeUser record);

    int updateByPrimaryKey(OfficeUser record);
}