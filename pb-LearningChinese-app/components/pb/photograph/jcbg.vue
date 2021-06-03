<!-- 这个用于检测报告上传图片-->
<template>
	<view class="jcbgBody" >
		<!-- :style="{left:style.left, top:style.top}" -->
		<view class="imgbox" name="resource_ids" >
			<view  v-for="(item,index) in photos2" :key="index">
				<image class="img" :src="item.imageUrl" mode=""></image>
				<view class="deleteImg" @click="deleteImg(index)"></view>
			</view>
		</view>
		<!-- :style="{left:style.left, top:style.top}" -->
		<view class="photograph" @click="photograph" v-if="showPhotograph2" ></view>
		
	</view>
</template>2

<script>
let that;
import companyApi from "@/api/company.js";
export default{
	
	name:"photographJcbg",
	
	props:{
		
		//图片数组
		photos:{
			type: Array,
			required: true
		},
		
		
		//上传按钮是否可见
		showPhotograph:{
			type: Boolean,
			default: true,
			required: true
		},
		
		resource_ids:{
			type: String,
			required: true
		},
		
		// style:{
		// 	type: Object
		// }
	},
	
	data() {
		
		return {
			photos2: this.photos,
			showPhotograph2: this.showPhotograph,
			resource_ids2: this.resource_ids
		}
	},
	
	onLoad(e){
		
	},
	
	
	created() {
		that = this;
	},
	
	mounted() {
	},
	
	
	methods: {
		
		//上传图片
		photograph(){
			uni.chooseImage({
			    count: 1,
			    sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
			    sourceType: ['album'], //从相册选择
			    success: function (res) {
					let imgStrs = res.tempFilePaths;
					//如果有照片，则拍照的图片就消失
					if(imgStrs.size > 0){
						that.showPhotograph2 = false ;
					}
					
					//为了防止在有图片时，再次添加图片，而导致resource_ids字符串少了 ","号
					if(that.resource_ids2 != ""){
						that.resource_ids2 += ",";
					}
					
					for(let str of imgStrs){
						that.photos2.push({
							imageUrl: str
						});
						
						companyApi.upload("resource_ids", str, response=>{
							let resp = JSON.parse(response.data);
							if(resp.code === 1){
								that.resource_ids2 = that.resource_ids2 + resp.data ;
								
								//图片最多上传6张
								if(that.photos2.length>=6){
									that.showPhotograph2 = false;
								}else{
									that.showPhotograph2 = true;
								}
								
								that.$emit("photograph", that.resource_ids2);
								
							}
						});
						
					}
					
			    }
			});
		},
		
		
		//删除图片
		deleteImg(index){
			
			let id = that.photos2[index].id;
			//去掉被删除id
			
			//从图片数组中删除这个图片
			that.photos2.splice(index,1);
			
			//图片最多上传6张
			if(that.photos2.length>=6){
				that.showPhotograph2 = false;
			}else{
				that.showPhotograph2 = true;
			}
			
			that.resource_ids2 = "";
			//重新获取这个that.resource_ids2 字符串
			let photo2Ids =  that.photos2.map(item=>{
				return item.id;
			});
			
			that.resource_ids2 = photo2Ids.join(",");
			
			
			// for(let i=0; i< that.photos2.length; i++){
			// 	if(i == that.photos2.length-1){
			// 		//最后一个数据
			// 		that.resource_ids2 += that.photos2[i].id;
			// 	}else{
			// 		that.resource_ids2 += that.photos2[i].id + ",";
			// 	}
			// }
			
			that.$emit("photograph", that.resource_ids2);
			
		},
	},
	
	
	watch:{
	    photos:function(val){
	      this.photos2=val
	    },
		showPhotograph:function(val){
		  this.showPhotograph2=val
		},
		resource_ids:function(val){
		  this.resource_ids2=val
		},
		// resource_ids:function(val){
		//   this.resource_ids2=val
		// },
	  },
}	
	
</script>

<style lang="less" scoped>
.jcbgBody{
	
	position: relative;
	z-index: 9999;
	
	
	.photograph{
		position: relative;
		top: 12rpx;
		left: 0;
		// top: 430.25rpx;
		width: 125rpx;
		height: 125rpx;
		background: url(../../../static/xhdpi/icon_photograph.png) no-repeat ;
		background-size: 125rpx 125rpx;
		
		&:hover{
			background-image: url(../../../static/xhdpi/icon_photograph_select.png);
		}
		
	}
	
	.imgbox{
		position: relative;
		left: 0;
		top: 12rpx;
		// top: 387.25rpx;
		width: 683.33rpx;
		max-height: 376.66rpx;
		height: auto;
		// display: flex;
		// justify-content: start;
		// flex-wrap: wrap;
		
		display: grid;
		// grid-template-rows: repeat(2,1fr);
		grid-template-columns: repeat(3,1fr);
		text-align: center;
		
		.img{
			width: 208.33rpx;
			height: 156.25rpx;
		}
		
		.deleteImg{
			position: relative;
			top: -154.75rpx;
			left: 175.5rpx;
			width: 37.5rpx;
			height: 37.5rpx;
			background: url(../../../static/xhdpi/icon_guanbi1.png) no-repeat ;
			background-size: 37.5rpx 37.5rpx;
			
			&:hover{
				background-image: url(../../../static/xhdpi/icon_guanbi1_select.png);
			}
		}
	}
	
}
</style>
