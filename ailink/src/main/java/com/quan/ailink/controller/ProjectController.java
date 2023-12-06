package com.quan.ailink.controller;


import com.quan.ailink.dto.ProjectDTO;
import com.quan.ailink.service.ProjectService;
import com.quan.ailink.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Result addProject(@RequestBody ProjectDTO projectDTO){
        boolean success = projectService.addProject(projectDTO);
        return success ? Result.success("添加成功") : Result.error("添加失败");
    }
}
