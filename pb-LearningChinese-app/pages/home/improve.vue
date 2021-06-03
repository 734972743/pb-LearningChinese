<!-- 完善企业信息 -->
<template>
	<view class="body">
		<button form-type="submit"  class="submit" @click="goAaddEnterpriseInformation()">完善企业信息</button>
	</view>
</template>

<script>
let that;
import companyApi from "@/api/company.js";
export default{
	
	data() {
		return {
		}
	},
	
	onLoad(e){
		that = this;
	},
	
	
	methods: {
		
		
		
		goAaddEnterpriseInformation() {
			uni.navigateTo({
				url: "/pages/home/addEnterpriseInformation"
			})
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
	
	.submit{
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%,-50%);
		width: 520.83rpx;
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
}
</style>
