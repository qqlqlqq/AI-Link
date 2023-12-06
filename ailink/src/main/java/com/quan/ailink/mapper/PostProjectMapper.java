package com.quan.ailink.mapper;

import com.quan.ailink.entity.PostProject;
import com.quan.ailink.entity.PostProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostProjectMapper {
    int countByExample(PostProjectExample example);

    int deleteByExample(PostProjectExample example);

    int deleteByPrimaryKey(Long ppid);

    int insert(PostProject record);

    int insertSelective(PostProject record);

    List<PostProject> selectByExample(PostProjectExample example);

    PostProject selectByPrimaryKey(Long ppid);

    int updateByExampleSelective(@Param("record") PostProject record, @Param("example") PostProjectExample example);

    int updateByExample(@Param("record") PostProject record, @Param("example") PostProjectExample example);

    int updateByPrimaryKeySelective(PostProject record);

    int updateByPrimaryKey(PostProject record);
}