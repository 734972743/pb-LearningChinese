//这个文件是保存一些公共的资源文件


import {getUrlObj, saveUrlObj } from "./auth.js";

let STATIC_URL = "http://192.168.1.120:8089/learningChinese/";   //这个服务器图片地址;
let VUE_SERVER_URL = "http://192.168.1.120:8089/learningChinese/";  //服务器端api地址
let VUE_SERVER_URL_JL = "";  // 这个是登录的账号中用户名包含jl时所用的url,



export {STATIC_URL,VUE_SERVER_URL,VUE_SERVER_URL_JL } ;  
 
