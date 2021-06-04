<template>
	<view class="guessPinyinBody">
		<view class="wordBody">
			<view class="word">
				{{word}}
			</view>
		</view>
		
		
		<button type="default" class="getWordBtn" @click="getNextWord">下一个</button>
		
		<view class="result">
			<view class="result_ok" v-if="result == 1">
				正确
			</view>
			<view class="result_error" v-else-if="result == -1">
				错误
			</view>
		</view>
		
		<view class="inputForm">
			<input type="text" class="inputValue"  :value="inputValue"  @input="onInput" placeholder="请输入拼音"  @confirm="submitMessage" />
			<button class="submitBtn" type="default" @click="submitMessage" >确定</button>
		</view>
		
		<view class="tip">
			游戏规则： 在输入框中输入上面的方框里面的词语对应的拼音，可忽略拼音之间的空格
		</view>
		
	</view>
</template>

<script>
import wordApi from "@/api/word.js";
export default{
	
	data() {
		return {
			word: "", 
			result: 0,
			wordList: ["是的","一定","可是","在家","不对","好了","有人","和平","人民","这里","中国","大家","为了","上学","个人","国家","我们","所以",
			 "要好","他们","时间","来往","用功","你们","生气","到达","工作","地方","由于","出发","成就","分开","对门","成功","开会","可能","做主","发动",
			 "新年","运动","同意","工场","非也","能力","下海","过去","男子","说话","生产","有种","面包","因而","方向","前后","许多","肯定","行为","学习"],
			inputValue: "",
			
			requestForm: {
				text: "",
				tong: "0",
				type: "alt"
			}
		}
	},
	
	onShow() {
		this.getNextWord();
	},
	
	
	methods: {
			
		init(){
			
		},
		
		getNextWord(){  //获取下一个单词
			let length = this.wordList.length;
			let index = Math.ceil(Math.random() * length);
			this.word = this.wordList[index];
		},
		
		//获取输入框的内容
		onInput(e){
			this.inputValue = e.target.value;
		},
		
		submitMessage(){
			let text = this.inputValue;
			this.requestForm.text = this.word;
			if(text){
				wordApi.wordToPinyin(this.requestForm, response=>{
					let resp = response.data;
					if(resp.flag){
						let pyObj = JSON.parse(resp.data.pyStr);
						let py = JSON.parse(pyObj.msg).join(" ");
						
						if(pyObj.status == 200 && text.trim() == py.trim()){
							this.result = 1;
							setTimeout(()=>{
								this.getNextWord();
							}, 2000);
						}else{
							this.result = -1;
						}
					}else{
						this.result = -1;
					}
				});
			}
		},
	},
}	

</script>

<style lang="scss" scoped>
.guessPinyinBody{
	.wordBody{
		position: relative;
		width: 208.33rpx;
		height: 208.33rpx;
		margin: 0 auto;
		top: 83.33rpx;
		border: 1px solid #333;
		display: flex;
		justify-content: center;
		align-items: center;
		
		.word{
			font-size: 45.83rpx;
			font-weight: bold;
			color: #666;
		}
	}
	
	.getWordBtn{
		position: relative;
		top: 188.16rpx;
		width: 416.66rpx;
		height: 104.16rpx;
		margin: 0 auto;
		background-color: #87AC42;
	}
	
	.result{
		position: relative;
		width: 208.33rpx;
		height: 208.33rpx;
		margin: 0 auto;
		top: 268.33rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		
		.result_ok{
			color: #4CD964;
		}
		
		.result_error{
			color: red;
		}
	}
	
	
	.inputForm{
		position: relative;
		left: 0;
		top: 395rpx;
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
