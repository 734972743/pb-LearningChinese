<!-- 这是一个弹出框组件页面 -->
<template>
	<view class="popup_bg" v-if="isShow" >
		<view class="popup_body" >
			<view class="top">
				结束时间
				<e-picker :showValue="dateEnd" mode="dateTime"  @change="change">
				<!-- {{dateEnd||'结束时间'}} -->
					<view class="date">{{dateEnd}}
						<view class="xiala"></view>
					</view>
				</e-picker>
			</view>
			<view class="tip1">养殖结束后可在历史养殖中查看</view>
			 <view class="tip2">一旦结束后不可再次编辑</view>
			<view class="bottom">
				<view class="cancel" @click="cancel">取消</view>
				
				
				<view class="ok" :style="countdown === 0 ? 'background-color: #9ECA4B': 'background-color:#909399 '" @click="ok">{{okText}}</view>
			</view> 
		</view>
	</view>
	
	
</template>

<script>
	
import ePicker from "@/components/e-picker/e-picker.vue";
export default{
	
	name: "popupLayout",
	
	components:{
		ePicker
	},
	
	props:{
		
		width:{
			type: String,
			default: '472.91rpx'
		},
		
		height:{
			type: String,
			default: '341.66rpx'
		},
		
		show:{
			type: Boolean,
			default: false,
		}
	},
	
	
	
	data() {
		
		const currentDate = this.getDate({
		     format: true
		})
		
		return {
			isShow: this.show,
			
			dateEnd: currentDate,
			
			countdown: 5 ,// 确认键倒计时
			
			okText: "" , //确认键提示文字
		}
	},
	
	onLoad(e){
		let popupBody = uni.createSelectorQuery().select('.popup_body');
		popupBody.style.width = this.width;
		popupBody.style.height = this.height;
	},
	
	onShow(){
	},
	
	methods: {
		
		open(){
			this.isShow = true;
		},
		
		close() {
			this.isShow = false;
		},
		
		cancel(){
			this.close();
		},
		
		ok(){
			if(this.countdown > 0 ){
				return false;
			}
			this.$emit("bindDateChange", this.dateEnd);
			this.close();
		},
		
		getDate(type) {
			const date = new Date();
			let year = date.getFullYear();
			let month = date.getMonth() + 1;
			let day = date.getDate();
			let hour = date.getHours();
			let minute = date.getMinutes();
			let second = date.getSeconds();
			
		
		 	if (type === 'start') { 
				year = year - 60;
			} else if (type === 'end') {
				// year = year + 2;
			}
			month = month > 9 ? month : '0' + month;;
			day = day > 9 ? day : '0' + day;
			hour = hour > 9 ? hour : '0' + hour;
			minute = minute > 9 ? minute : '0' + minute;
			// second = second > 9 ? second : '0' + second;
			
			// return `${year}-${month}-${day} ${hour}:${minute}`;
			return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
		},
		
		bindDateChange(e) {
			this.dateEnd = e.target.value;
		},
		
		change(e){
			this.dateEnd = e;
		}
		
	},
	
	computed: {
		startDate() {
			return this.getDate('start');
		},
		endDate() {
			return this.getDate('end');
		}
	},
	
	watch:{
		isShow(newVal,oldVal){
			//当这个组件被显示时：
			let time = null;
			if(newVal){
				//5秒之后才可以点击确认
				 time =  setInterval(()=>{
					if(this.countdown == 0){
						this.okText = "确认"
						clearInterval(time);
					}else{
						this.countdown --;
						this.okText =  this.countdown +"";
					}
				}, 1000);
			}else{
				this.countdown = 5;
				time = null;
			}
			
			
		}
	}
	
}	
	
</script>

<style lang="less" scoped>
.popup_bg{
	position: fixed;
	z-index: 999999;
	top: 0;
	left: 0;
	background: rgba(0, 0, 0, .3);
	height: 100%;
	width: 100%;
	
	.popup_body{
		position: fixed;
		z-index: 1000000;
		left: 138.54rpx;
		top: 475.02rpx;
		// transform: translate(-50% -50%);
		background-color: #FFFFFF;
		width: 472.91rpx;
		height: 341.66rpx;
		
		.top{
			position: relative;
			top: 57.29rpx;
			left: 35.41rpx;
			width: 100%;
			font-family: Source Han Sans CN;
			font-size: 29.16rpx;
			color: #333333;
			
			.date{
				position: relative;
				left: 123.41rpx;
				top: -38rpx;
				width: 279.16rpx;
				height: 41.66rpx;
				line-height: 41.66rpx;
				border: 1.04rpx solid #999999;
				border-radius: 5.2rpx;
				padding-left: 8.91rpx;
				box-sizing: border-box;
				font-size: 25rpx;
				
				.xiala{
					position: absolute;
					left: 252.06rpx;
					top: 12.5rpx;
					width: 16.66rpx;
					height: 16.66rpx;
					background: url(../../../static/xhdpi/icon_xiala.png) no-repeat 100% 100%;
				}
			}
		}
		
		.tip1{
			position: relative;
			top: 50.62rpx;
			left: 61.45rpx;
			font-family: Source Han Sans CN;
			font-size: 25rpx;
			color: #999999;
		}
		
		.tip2{
			position: relative;
			top: 64.16rpx;
			left: 98.95rpx;
			font-family: Source Han Sans CN;
			font-size: 25rpx;
			color: #FF0000;
		}
		
		.bottom{
			position: absolute;
			bottom: 0;
			left: 0;
			width: 100%;
			height: 101.04rpx;
			font-family: Source Han Sans CN;
			font-size: 25rpx;
			color: #FF0000;
			line-height: 101.04rpx;
			
			.cancel{
				position: absolute;
				left: 0;
				top: 0;
				width: 236.45rpx;
				height: 100%;
				background-color: #F2F2F2;
				color: #333333;
				text-align: center;
			}
			
			.ok{
				position: relative;
				// left: 236.45rpx;
				top: 0;
				width: 236.45rpx;
				height: 100%;
				background-color: #9ECA4B;
				color: #FFFFFF;
				text-align: center;
				float: right;
			}
		}
	}
}	
	


</style>
