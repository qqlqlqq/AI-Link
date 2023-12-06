package com.quan.ailink.service.impl;


import com.quan.ailink.dto.ProjectDTO;
import com.quan.ailink.entity.Attachment;
import com.quan.ailink.entity.Groups;
import com.quan.ailink.entity.Project;
import com.quan.ailink.mapper.AttachmentMapper;
import com.quan.ailink.mapper.GroupsMapper;
import com.quan.ailink.mapper.ProjectMapper;
import com.quan.ailink.service.ProjectService;
import com.quan.ailink.utils.StringUtil;
import com.quan.ailink.vo.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private GroupsMapper groupsMapper;

    @Override
    @Transactional
    public boolean addProject(ProjectDTO projectDTO) {
        ActiveUser principal = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Project project = new Project();
        BeanUtils.copyProperties(projectDTO,project);
        //设置一下没有填写的属性
        project.setBeginTime(new Date());
        project.setCreateUser(principal.getUid());
        project.setState((byte) 0);
        project.setStatus((byte) 0);

        int result = projectMapper.insertProject(project);
        //有附件还需要添加附件
        if (StringUtil.isNotNull(projectDTO.getPath())){
            Attachment attachment = new Attachment();
            BeanUtils.copyProperties(projectDTO,attachment);
            attachment.setUid(principal.getUid());
            attachment.setPid(project.getPid());
            //添加附件
            attachmentMapper.insertSelective(attachment);
        }
        //添加项目的负责人
        List<Object> collect = projectDTO.getSelectUsers().stream().map(uid ->
            new Groups(null, uid, project.getPid())
        ).collect(Collectors.toList());
        //添加到groups表中
        int r = groupsMapper.insertGroupsSelective(collect);
        return result > 0;
    }
}
