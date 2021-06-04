import {STATIC_URL,VUE_SERVER_URL,VUE_SERVER_URL_JL} from "./common.js";

import {getToken} from "./auth.js";



//封装request请求
let request = function(url){
	let baseUrl = "";
	
	// 用来获取token和cookie
	let obj = getToken();
	
	let token = "";
	// if(obj){
	// 	if(obj.token && obj.api_url){
	// 		 token = obj.token;
	// 		 //判断当前页面是否是登录页面
	// 		 let routes = getCurrentPages(); // 获取当前打开过的页面路由数组
	// 		 let curRoute = routes[routes.length - 1].route //获取当前页面路由
	// 		 if(curRoute !== "pages/home/login"){  //如果是不是登录页面，则基础url是VUE_SERVER_URL ，否则是 obj.api_url
					
	// 		 	baseUrl = obj.api_url;
	// 		 }else{
	// 			 //单独判断获取公司的这个api， 因为这个接口是在login页面中调用的
	// 			if(url.url.indexOf("Wxcompany/getOne") > -1 ){
	// 				baseUrl = obj.api_url;
	// 			}else{
	// 				baseUrl = VUE_SERVER_URL;	
	// 			}
				
	// 		 }
			 
	// 	}else{
	// 		baseUrl = VUE_SERVER_URL;
	// 		uni.reLaunch({
	// 			url: '/pages/home/login'
	// 		});
	// 	}
		
	// }else{
	// 	//token 不存在或过期了 ,并且不是在登录页发出的请求时
	// 	//则让其重新登录
	// 	let routes = getCurrentPages(); // 获取当前打开过的页面路由数组
	// 	let curRoute = routes[routes.length - 1].route //获取当前页面路由
	// 	if(curRoute !== "pages/home/login"){
			
	// 		uni.reLaunch({
	// 		    url: '/pages/home/login'
	// 		});
	// 		return false;
	// 	}
	// }
	
	
	let cookie = "";
	if(obj && obj.cookie){
		cookie = obj.cookie;
	}
	
	
	//封装request请求
	return new Promise((resolve, reject)=>{
		
		let requestUrl = "";  //请求的url
		// if(url.url.indexOf("Wxlogin/login") > -1  && url.data && url.data.username && url.data && url.data.username.indexOf("jl") > -1){  //针对于登录接口
		// 	//如果登录的账号中用户名包含jl的话，访问的url就为: https://www.wewinx.com/sc_jl/
		// 	requestUrl = VUE_SERVER_URL_JL + url.url;
		// }else{
		// 	requestUrl = baseUrl + url.url;
		// }
		baseUrl = VUE_SERVER_URL;
		requestUrl = baseUrl + url.url;
		 uni.request({
			timeout: 30000,    //超时时间
			url: requestUrl,    //url
			method: url.method,    //请求方式
			data: url.data,     //参数
			dataType: 'json',
			// header: {
			// 		"Cookie": `${cookie}`,
			// 		'Authorization': `${token}`
			//     },
			// headers: {'Cache-Control': 'no-cache',  'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',},
			
			success: (response)=>{
				let resp = response.data;
				
				if(resp.code == 401){
					//未登录
					uni.reLaunch({
					    url: '/pages/home/login'
					});
					return false;
				}else if(resp.code == 403){
					//没有权限
				}else{
					//没有问题时，
					resolve( url.callback(response));
				}
			} ,
			 fail: (error)=>{
				 reject(error);
			 }
		});
		
		
	});
	
	
}

export default request ;