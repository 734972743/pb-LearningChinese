import Vue from 'vue'
import App from './App'
import store from "@/store";   //状态vuex
import {STATIC_URL} from "@/utils/common.js";


Vue.config.productionTip = false
Vue.prototype.STATIC_URL = STATIC_URL;

App.mpType = 'app'

const app = new Vue({
    ...App,
	store 
})
app.$mount()
