//这个文件是用来保存数据到本地的

const TOKEN_KEY = "accessToken";    //token
const expires = 1000 * 60 * 60 * 24 * 1 ; // 设置过期时间为 1天

const CHARGE_PERSON_KEY = "chargePerson";  //联系人对象

const MATCH_BLE_KEY =  "matchBle"  //匹配的蓝牙数据

const UNIT_KEY = "unit";  //单位

const URL_OBJ_KEY ="urlObj";  //后台返回url对象


//保存token
export function saveToken(token,cookie, api_url){
	  let obj = {};   
	  obj.startTime = new Date().getTime();  // 记录当前时间
	  obj.expires = expires ;  //设置过期时间，毫秒级
	  obj.token = token;
	  obj.cookie =  cookie;
	  obj.api_url =  api_url;
	  
	  //同步保存数据
	  try {
	      uni.setStorageSync(TOKEN_KEY, obj);  //同步接口
	  } catch (e) {
	      // error
	  }
}

//获取token
export function getToken(){
	try {
	    const value = uni.getStorageSync(TOKEN_KEY);
		
		if(value && value !== 'undefined'){
		    // let obj = JSON.parse(value);
			let obj = value;
		    let startTime = obj.startTime;
		    let expires = obj.expires;
		    let endTime = new Date().getTime();
		    if(startTime + expires >= endTime){  
		      //在过期时间内
		      return obj;
		    }else{
		      return undefined;
		    }
		  }else{
		      return undefined;
		    }
	} catch (e) {
	    // error
	}
}


export function saveChargePerson(chargePerson){
	uni.setStorage({
	    key: CHARGE_PERSON_KEY,
	    data: chargePerson,
	    success: function () {
	    }
	});
}

export function getChargePerson(){
	try {
	    const value = uni.getStorageSync(CHARGE_PERSON_KEY);
		return value;
	} catch (e) {
	    // error
	}
}

export function saveMatchBle(matchBle){
	uni.setStorage({
	    key: MATCH_BLE_KEY,
	    data: matchBle,
	    success: function () {
	    }
	});
}

export function getMatchBle(){
	try {
	    const value = uni.getStorageSync(MATCH_BLE_KEY);
		return value;
	} catch (e) {
	    // error
	}
}

//保存数量的单位
export function saveUnit(unit){
	uni.setStorage({
	    key: UNIT_KEY,
	    data: unit,
	    success: function () {
	    }
	});
}

//获取数量的单位
export function getUnit(){
	try {
	    const value = uni.getStorageSync(UNIT_KEY);
		return value;
	} catch (e) {
	    // error
	}
}


//保存url
export function saveUrlObj(urlObj){
	uni.setStorage({
	    key: URL_OBJ_KEY,
	    data: urlObj,
	    success: function () {
	    }
	});
}

//获取url
export function getUrlObj(){
	try {
	    const value = uni.getStorageSync(URL_OBJ_KEY);
		return value;
	} catch (e) {
	    // error
	}
}