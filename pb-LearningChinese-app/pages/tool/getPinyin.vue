<template>
	<view class="getPinyinBody">
		
		<view class="uni-list-cell uni-list-cell-pd switch">
			<view class="uni-list-cell-db">是否开启声调</view>
			<switch :checked="checked" @change="pinyinState"/>
		</view>
		
		<view class="inputForm">
			<input type="text" class="inputValue"  :value="inputValue"  @input="onInput" placeholder="请输入汉字或词语"  @confirm="submitMessage" />
			<button class="submitBtn" type="default" @click="submitMessage" >确定</button>
		</view>
		
		<view class="pinyinBody">
			<view class="pinyin">
				{{pinyin}}
			</view>
		</view>
		
		<view class="tip">
			提示： 在输入框中输入你想获取拼音的汉字或词语，成语
		</view>
		
	</view>
</template>

<script>
import wordApi from "@/api/word.js";
export default{
	
	data() {
		return {
			pinyin: "", 
			checked: true,
			inputValue: "",
			
			requestForm: {
				text: "",
				tong: "1",
				type: "alt"
			}
		}
	},
	
	onShow() {
	},
	
	
	methods: {
			
		init(){
			
		},
		
		pinyinState(e){
			let value = e.target.value;
			console.log(value);
			if(value){
				this.requestForm.tong = "1";
			}else{
				this.requestForm.tong = "0";
			}
		},
		
		//获取输入框的内容
		onInput(e){
			this.inputValue = e.target.value;
		},
		
		submitMessage(){
			let text = this.inputValue;
			if(text){
				this.requestForm.text = text;
				wordApi.wordToPinyin(this.requestForm, response=>{
					let resp = response.data;
					if(resp.flag){
						let pyObj = JSON.parse(resp.data.pyStr);
						let py = JSON.parse(pyObj.msg).join(" ");
						this.pinyin = py;
					}else{
						that.showToast(resp.message);
					}
				});
			}
		},
	},
}	

</script>

<style lang="scss" scoped>
.getPinyinBody{
	
	
	.switch{
		position: relative;
		left: 36.16rpx;
		top: 36.33rpx;
	}
	
	.inputForm{
		position: relative;
		left: 0;
		top: 83.33rpx;
		width: 100%;
		height: 89.58rpx;
		
		.inputValue{
			position: absolute;
			left: 29rpx;
			top: 15rpx;
			width: 513rpx;
			border-radius: 12rpx;
			height: 60.41rpx;
			background-color: #fff;
			padding-left: 30rpx;
			font-size: 34.91rpx;
			border: 1px solid #666;
		}
		
		.submitBtn{
			position: absolute;
			left: 593rpx;
			top: 21rpx;
			width: 126.5rpx;
			height: 50rpx;
			font-size: 30rpx;
			line-height: 50rpx;
			text-align: center;
			background-color: cadetblue;
		}
	}
	
	
	
	.pinyinBody{
		position: relative;
		width: 538.33rpx;
		height: 208.33rpx;
		top: 149.33rpx;
		left: 33rpx;
		border: 1px solid #333;
		display: flex;
		justify-content: center;
		align-items: center;
		
		.pinyin{
			font-size: 45.83rpx;
			font-weight: bold;
			color: #666;
			text-align: center;
		}
	}
	
	
	.tip{
		position: relative;
		left: 0;
		top: 540rpx;
		font-size: 31.5rpx;
		color: #666;
		width: 94%;
		margin: 0 auto;
	}
	
}
</style>
