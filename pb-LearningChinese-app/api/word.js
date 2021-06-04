import request from "../utils/request.js";

/**
 * 单词api
 */
export default {
	
	
	//登录用户
	wordToPinyin(form,callback){
		let {text,tong,type} = form;
		return request({
		  url: `word/wordToPinyin`,
		  method: "POST",
		  data: {
			 text,tong,type
		  },
		  callback
		});
	},
	
 
 
};