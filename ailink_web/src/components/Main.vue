<template>
    <div>
      <el-container>
        <el-header>Header</el-header>
        <el-container>
          <el-aside width="200px">
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              :router="true"
            >
              <el-submenu v-for="(menu,index) in menus" :key="index"  :index="menu.pid + ''">
                <template slot="title">
                  <i class="el-icon-location"></i>
                  <span>{{menu.pname}}</span>
                </template>
                <el-menu-item v-for="(submenu,i) in menu.children" :key="i" :index="submenu.href">{{submenu.pname}}</el-menu-item>
              </el-submenu>
            </el-menu>
          </el-aside>
          <el-main>
            <router-view/>
          </el-main>
        </el-container>
      </el-container>




    </div>
</template>

<script>
    import {menu} from "../request/api";

    export default {
        name: "Main",
        data(){
          return {
            menus:[{pid:0,pname:"",children:[]}]
          };
        },
        methods:{
          getMenus(){
            menu().then(response => {
              let result = response.data;
              if (result.code == 0){
                this.menus = result.data;
              }
            })
          }
        },
      created() {
        this.getMenus();
      }
    }
</script>

<style scoped>

</style>
