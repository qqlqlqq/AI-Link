<template>
    <div>
      <div>
        <span>账号</span><el-input v-model="user.username" placeholder="请输入账号"></el-input>
      </div>
      <div>
        <span>密码</span><el-input v-model="user.password" placeholder="请输入密码" show-password></el-input>
      </div>
      <div>
        <span>验证码</span><el-input v-model="user.captcha" placeholder="请输入验证码"></el-input>
        <img :src="img" @click="getKaptcha" alt="">
      </div>
      <div>
        <el-button type="primary" @click="login" round>登陆</el-button>
      </div>
    </div>
</template>

<script>
    import {kaptcha,login} from "../request/api";

    export default {
        name: "Login",
        data(){
          return {
            user: {
              username:"",
              password:"",
              captcha:""
            },
            img:""
          };
        },
      methods:{
          getKaptcha(){
            kaptcha().then(response => {
              let result = response.data;
              if (result.code == 0){
                this.img = result.data;
                //将token保存一下
                this.$store.commit("setToken",response.headers.token);
              }
            })
          },
          login(){
            let _this = this;
            login(_this.user).then(response => {
              let result = response.data;
              if (result.code == 0){
                //将token保存一下
                _this.$store.commit("setToken",response.headers.token);
                //保存一下当前用户的信息
                _this.$store.commit("updateCurUser",result.data);
                _this.$router.push("/main");

              }
            })
          }
      },
      created() {
          this.getKaptcha();
      }

    }
</script>

<style scoped>

</style>
