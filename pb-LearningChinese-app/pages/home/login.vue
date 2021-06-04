<!-- 用户登录页面 -->
<template>
	<view class="body">
		<button form-type="submit"  class="submit" @tap="getUserProfile">微信一键登录</button>
		
		<view class="tips">
			点击登录即代表同意水产品生产安全管理<text @click="goServiceAgreement">《服务协议》</text> <text @click="goPrivacyPolicy">《隐私政策》</text>
		</view>
		
	</view>
</template>

<script>
let that;
import userApi from "@/api/user.js";

import {saveToken} from "@/utils/auth.js";
import {VUE_SERVER_URL, VUE_SERVER_URL_JL} from "@/utils/common.js";
export default{
	
	data() {
		return {
			errorTips:"",
			username: "",
			password: "",
		}
	},
	
	onLoad(e){
		that = this;
	},
	
	
	
	methods: {
		
		goServiceAgreement(){
			uni.navigateTo({
				url: "/pages/home/serviceAgreement"
			})
		},
		
		goPrivacyPolicy(){
			uni.navigateTo({
				url: "/pages/home/privacyPolicy"
			})
		},
		
		
		async getUserProfile(){
			 
			let code = null;
			//登录
			await uni.login({
	            success (res2) {
	             	 if (res2.code) {
							code = res2.code;
		              } else {
						that.showToast('登录失败！' + res2.errMsg);
		              }
		        },
				fail: error=>{
					this.showToast(error);
				}
	         });
			//获取用户信息
			
			let param = null;
			uni.getUserProfile({
				    desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
					lang: "zh_CN" ,
				    success: (res1) => {
					console.log("res1",res1);
				        let userInfo = res1.userInfo;
				        if(userInfo){
							//登录
				         	param = {
							  nickname: userInfo.nickName,
			                  avatarUrl: userInfo.avatarUrl,
			                  city: userInfo.city,
			                  province: userInfo.province,
			                  gender: userInfo.gender,
			                  language: userInfo.language,
			                  code: code
							}
						
							that.loginFun(param);
				        }
			     	 }
			    })
		},
		
		
		
		//调用后台接口登录
		loginFun(param){
			userApi.login(param, response=>{
				let resp = response.data;
				if(resp.flag){
					//保存用户信息
					that.$store.commit("SET_LOGIN_USER_INFO", resp.data);
					uni.switchTab({
						url: "/pages/home/index"
					});
				}else{
					that.showToast(resp.message);
				}
			});
		}
		
		
			
			
	},
}
</script>

<style lang="less" scoped>
.body{
	position: relative;
	left: 0;
	top: 0;
	width: 100vw;
	height: 100vh;
	overflow: hidden;
	
	
	.submit{
		position: relative;
		top: 490.62rpx;
		width: 604.16rpx;
		height: 83.33rpx;
		background-color: @uni-button-bg-color;
		font-family: Source Han Sans CN;
		font-size: 33.33rpx;
		color: #fff;
		text-align: center;
		
		&:hover{
			background-color: @uni-button-bg-color-hover;
		}
	}
	
	
	
	.tips{
		position: absolute;
		left: 72.91rpx;
		// top: 1090.62rpx;
		bottom: 93.75rpx;
		font-family: Source Han Sans CN;
		font-size: 20.83rpx;
		color: #666666;
		letter-spacing: 1.25rpx;
		
		text{
			font-family: Source Han Sans CN;
			font-size: 20.83rpx;
			color: #76A8FF;
		}
	}
	
}
</style>
.