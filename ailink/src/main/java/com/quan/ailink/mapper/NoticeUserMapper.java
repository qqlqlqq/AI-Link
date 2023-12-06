package com.quan.ailink.mapper;

import com.quan.ailink.entity.NoticeUser;
import com.quan.ailink.entity.NoticeUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeUserMapper {
    int countByExample(NoticeUserExample example);

    int deleteByExample(NoticeUserExample example);

    int deleteByPrimaryKey(Long nuid);

    int insert(NoticeUser record);

    int insertSelective(NoticeUser record);

    List<NoticeUser> selectByExample(NoticeUserExample example);

    NoticeUser selectByPrimaryKey(Long nuid);

    int updateByExampleSelective(@Param("record") NoticeUser record, @Param("example") NoticeUserExample example);

    int updateByExample(@Param("record") NoticeUser record, @Param("example") NoticeUserExample example);

    int updateByPrimaryKeySelective(NoticeUser record);

    int updateByPrimaryKey(NoticeUser record);
}