import {get,post,postJSON,put} from "@/request/http";

// 验证码请求
export const kaptcha = ()=>{return get("user/kaptcha")}

//登陆
export const login = (user)=>{return postJSON("user/login",user)}
//获取菜单
export const menu = ()=>{return get("user/menu")}
//添加项目
export const addProject = (project)=>{return postJSON("project",project)}
//查询同部门的人
export const getUserByDepartment = ()=>{return get("user/getUserByDepartment")}
