package com.quan.ailink.mapper;

import com.quan.ailink.entity.DepartmentUser;
import com.quan.ailink.entity.DepartmentUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentUserMapper {
    int countByExample(DepartmentUserExample example);

    int deleteByExample(DepartmentUserExample example);

    int deleteByPrimaryKey(Long duid);

    int insert(DepartmentUser record);

    int insertSelective(DepartmentUser record);

    List<DepartmentUser> selectByExample(DepartmentUserExample example);

    DepartmentUser selectByPrimaryKey(Long duid);

    int updateByExampleSelective(@Param("record") DepartmentUser record, @Param("example") DepartmentUserExample example);

    int updateByExample(@Param("record") DepartmentUser record, @Param("example") DepartmentUserExample example);

    int updateByPrimaryKeySelective(DepartmentUser record);

    int updateByPrimaryKey(DepartmentUser record);
}