import axios from 'axios'
import QS from 'qs'
import store from '../store'
import router from '../router'

// 先根据环境切换请求地址
if (process.env.NODE_ENV == "development"){//开发环境
  axios.defaults.baseURL = 'http://localhost:8080/';//开发环境的路径
} else if (process.env.NODE_ENV == "production"){//生产环境
  axios.defaults.baseURL = 'http://localhost:8080/';//生产环境的路径
}

// 设置超时时间
axios.defaults.timeout = 10000;//单位是毫秒
//设置post请求的请求头
// axios.defaults.headers.post['Content-Type'] = "application/x-www-from-urlencoded;charset=utf-8";

//请求之前 拦截请求自动添加token进去
axios.interceptors.request.use(
  config=>{
    const token = store.state.token;
    if (token){//如果有token
      config.headers.token = token;//添加token到请求头中
    }
    return config;
  }
)

// 拦截响应
axios.interceptors.response.use(
  response=>{
    if (response.status == 200){
      if (response.data.code == 2){//未登录
        return Promise.reject(response);//拦截响应
      } else {
        return Promise.resolve(response);//放行响应
      }
    } else {
      return Promise.reject(response);//拦截响应
    }
  },
  response=>{// 拦截响应后的处理方式
    switch (response.status) {
      case 200:
        router.push("/login")
        break;
      default:
        router.push("/404")
    }

  }
)
//get请求统一处理
export function get(url, params) {
  return new Promise(((resolve,reject) => {
    axios.get(url,{params}).then(res=>{
      resolve(res);
    }).catch(err=>{
      reject(err);
    })
  }))
}
// post普通参数提交
export function post(url, params) {
  return new Promise(((resolve,reject) => {
      axios.post(url,QS.stringify(params),{
        headers:{"Content-Type":"application/x-www-from-urlencoded;charset=utf-8"}
      }).then(res=>{
      resolve(res);
    }).catch(err=>{
      reject(err);
    })
  }))
}
// post提交json参数
export function postJSON(url, params) {
  return new Promise(((resolve,reject) => {
    axios.post(url,params).then(res=>{
      resolve(res);
    }).catch(err=>{
      reject(err);
    })
  }))
}
// put 提交统一处理
export function put(url, params) {
  return new Promise(((resolve,reject) => {
    axios.put(url,{params}).then(res=>{
      resolve(res);
    }).catch(err=>{
      reject(err);
    })
  }))
}


