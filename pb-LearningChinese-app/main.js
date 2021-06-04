import Vue from 'vue'
import App from './App'
import store from "@/store";   //状态vuex
import {STATIC_URL} from "@/utils/common.js";
import {showToast} from "@/utils/commonTools.js";
// import io from "./components/pb/socket/weapp.socket.io.js";

Vue.config.productionTip = false
Vue.prototype.STATIC_URL = STATIC_URL;
Vue.prototype.showToast = showToast;
// Vue.prototype.socket = io("http://127.0.0.1:7011/io/chat/index");


App.mpType = 'app'

const app = new Vue({
    ...App,
	store,
})
app.$mount()
