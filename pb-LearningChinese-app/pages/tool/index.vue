<template>
	<view class="toolBody pageBody">
		<view class="toolBtns">
			<button type="default" class="toolBtn" @click="goNav('getPinyin')">获取拼音</button>
			<button type="default" class="toolBtn" @click="goNav('CYJielong')">成语接龙</button> 
			<button type="default" class="toolBtn">成语小游戏</button>
		</view>
	</view>
</template>

<script>
export default{
	
	data() {
		return {
			
		}
	},
	
	
	methods: {
		goNav(nav) {
			console.log(nav);
			uni.navigateTo({
				url: `/pages/tool/${nav}`
			});
		}
	},
}
</script>

<style lang="scss" scoped>
.toolBody{
	overflow: hidden;
	
	.toolBtns{
		position: relative;
		left: 0;
		top: 0;
		width: 100%;
		height: 700px;
		
		.toolBtn{
			width: 416.66rpx;
			height: 104.16rpx;
			margin: 85.41rpx auto;
			
			&:nth-of-type(1){
				background-color: #4CD964;
			}
			
			&:nth-of-type(2){
				background-color: #87AC42;
			}
			
			&:nth-of-type(3){
				background-color: #9ECA4B;
			}
		}
	}
	
}
</style>
