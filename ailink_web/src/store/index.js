import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state:{
    curUser:{},
    token:""
  },
  mutations:{
    /**
     *  修改方法一般有两个参数
     * @param state 对象
     * @param payload 要修改的数据
     */
    setToken(state,payload){
      state.token = payload;
    },
    updateCurUser(state,obj){
      state.curUser = obj;
    }
  }
})
