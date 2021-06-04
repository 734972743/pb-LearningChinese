<!-- 成语接龙 -->
<template>
	<view class="CYJielongBody">
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
			<input type="text" class="inputValue"  :value="inputValue"  @input="onInput" placeholder="请输入成语"  @confirm="submitMessage" />
			<button class="submitBtn" type="default" @click="submitMessage" >确定</button>
		</view>
		
		<view class="tip">
			游戏规则： 请在输入框中输入一个成语，这个成语的第一个字必须是上面方框的成语的最后一个字，可以允许字不同，但音必须相同，可以声调不同。
			比如： 杂七杂八 --> 八拜之交 --> 交头接耳 
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
			wordList: ["料事如神","足智多谋","融会贯通","学贯中西","博古通今","才华横溢","出类拔萃","博大精深","举一反三","文质彬彬","相貌堂堂","落落大方","意气风发",
			 "容光焕发","能说会道","巧舌如簧","能言善辩","伶牙俐齿","欢呼雀跃","扶老携幼","手舞足蹈","促膝谈心","张牙舞爪","恩重如山","寒冬腊月","热火朝天","如火如荼","高朋满座","蔚为壮观","水天一色",
			 "出口成章","语惊四座","娓娓而谈","妙语连珠","三顾茅庐","铁杵成针","望梅止渴","完璧归赵","四面楚歌","精忠报国","手不释卷","悬梁刺股","凿壁偷光","走马观花",
			 "生死相依","发奋图强","开卷有益","自相矛盾","叶公好龙","亡羊补牢","画蛇添足","刻舟求剑","无懈可击","锐不可当","春暖花开","满园春色","秋风送爽","寒气袭人"],
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
		
		//根据汉字获取对应的拼音
		wordToPinyin(text){
			return new Promise((resolve, reject)=>{
				this.requestForm.text = text;
				 wordApi.wordToPinyin(this.requestForm, response=>{
					let resp = response.data;
					if(resp.flag){
						let pyObj = JSON.parse(resp.data.pyStr);
						let py = JSON.parse(pyObj.msg).join(" ");
						
						if(pyObj.status == 200 ){
							resolve(py.trim());
						}else{
						}
					}else{
					}
					reject(null);
				});
			});
			
		},
		
		
		async submitMessage(){
			let text = this.inputValue;  //用户输入成语
			if(text && text.length == 4){
				let wordPy = await this.wordToPinyin(this.word.substring(3));
				let textPy = await this.wordToPinyin(text.substring(0,1));
				if(wordPy == textPy){
					this.result = 1;
					this.getNextWord();
				}else{
					this.result = -1;
				}
			}else{
				this.showToast("请输入正确的成语");
			}
		},
	},
}	

</script>

<style lang="scss" scoped>
.CYJielongBody{
	.wordBody{
		position: relative;
		width: 416.66rpx;
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
