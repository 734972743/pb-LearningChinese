import Vue from "vue";
import Vuex from "vuex";
import company from "./modules/company";
import user from "./modules/user.js";

Vue.use(Vuex);

//进行模块化
const store = new Vuex.Store({
  modules: {
    company,
	user
  }
});
export default store;