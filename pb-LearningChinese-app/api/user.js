import request from "../utils/request.js";

/**
 * 塘口人员api
 */
export default {
	
	
	//登录用户
	login(form,callback){
		let {nickname,avatarUrl,city,province,gender,language,code} = form;
		return request({
		  url: `user/login`,
		  method: "POST",
		  data: {
			 nickname,avatarUrl,city,province,gender,language,code
		  },
		  callback
		});
	},
	
 
 /**  新增人员
  * @param {Object} form  新增人员表单信息
  * @param {Object} callback  回调函数
  */
  addUser(form,callback) {
	  let {nickname,phone,signature_img} = form;
      return request({
        url: `Wxuser/create`,
        method: "POST",
		data: {
			nickname,phone,signature_img
		},
  	  callback
      });
    },
	
	/**  修改人员信息
	 * @param {Object} form  表单数据
	 * @param {Object} callback  回调函数
	 */
	updateUser(form,callback) {
		  let {id,nickname,phone,signature_img} = form;
	    return request({
	      url: `Wxuser/update`,
	      method: "POST",
			data: {
				id,nickname,phone,signature_img
			},
		  callback
	    });
	  },
	
	
	/** 根据id来删除人员
	 * @param {Object} id  人员id
	 * @param {Object} callback 回调函数
	 */ 
	deleteUser(id,callback) {
	    return request({
	      url: `Wxuser/delete`,
	      method: "POST",
			data: {
				id
			},
		  callback
	    });
	  },
	
	
	/**  查询所有的人员
	 * @param {Object} callback 回调函数
	 */
	getList(callback) {
	    return request({
	      url: `Wxuser/dataList`,
	      method: "GET",
		  callback
	    });
	  },
	  
	  
	  
	  /** 根据id来查询单个的人员
	   * @param {Object} id  人员id
	   * @param {Object} callback 回调函数
	   */
	getOneById(id,callback) {
	    return request({
	      url: `Wxuser/getOne?id=`+id,
	      method: "GET",
		  callback
	    });
	  }
};