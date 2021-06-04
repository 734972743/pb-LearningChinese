<!-- 聊天详情页面 -->
<template>
	<view class="messageDetailBody pageBody">
		<scroll-view scroll-y="true" :scroll-top="scrollTop" class="messageList">
			<view class="message_item" v-for="(message,index) in messageList" :key="index">
				<!-- 时间日期 -->
				<view class="time" v-if="message.time_text">{{message.time_text}}</view>
				<!-- 不展示日期 -->
				<view class="message" v-else>
					<view  v-if="message.type == 'left'">
						<block v-if="message.infoType == 'img'">
							<image class="imgImgLeft" :src="left_img" mode=""></image>
							<image class="contentImgLeft" :src="message.content" mode="aspectFit" @click="openPopupImage(message.content)"></image>
						</block>
						<block v-else>
							<image class="textImgLeft" :src="left_img" mode="" ></image>
							<view class="contentLeft" >{{message.content}}</view>
						</block>
						
					</view>
					
					<view  v-else>
						<!-- <image class="imgRight" :src="right_img" mode=""></image> -->
						<block v-if="message.infoType == 'img'">
							<image class="imgImgRight" :src="right_img" mode=""></image>
							<image class="contentImgRight"  :src="message.content" mode="aspectFit" @click="openPopupImage(message.content)"></image>
						</block>
						<block v-else>
							<image class="textImgRight" :src="right_img" mode=""></image>
							<view class="contentRight" >{{message.content}}</view>
						</block>
						
					</view>
					
					<!-- <view class="clear"></view> -->
				</view>
				
			</view>
		</scroll-view>
		
		<view class="inputForm">
			<input class="inputValue" type="text" :value="inputValue"  @input="onInput" placeholder="请输入内容"  @confirm="submitMessage"/>
			<!-- <image  src="../../static/xhdpi/icon_jilupaizhao.png" mode=""></image> -->
			<my-photograph class="inputImg" pageName="messageDetail" :photos="photos" :showPhotograph="showPhotograph" :resource_ids="imgId"  @photograph="photograph"></my-photograph>
			<button class="submitBtn" type="default" @click="submitMessage" >发送</button>
		</view>
		
		<popupImage :imageProp="imagePropSrc" :isShowProp="isShowImageProp"  @closeImage="closeImage"></popupImage>
	</view>
</template>

<script>
let that;
import io from "@/components/pb/socket/weapp.socket.io";
import socket  from "@/components/pb/socket/socket.js";


export default{
	
	components:{
	},
	
	data() {
		return {
			socketTask: null, 
			c_uid: null,    // 当前用户id , 发帖子用户， 发送方
			uid: null,   	//接受人id ，  接受方
			rquesetForm:{
				send_uid: null, 
				receive_uid: null,
			},
			right_img: "",  //发送者图像
			left_img: "",   //接收者头像
			left_nickName: "",
			right_nickName: "",
			
			messageList: []  ,//消息列表
			timeList: [],  //这个是用来存放显示的聊天的日期时间
			inputValue: "",
			online: 0 , //判断是否在线： 0：不在线， 1：在线
			scrollTop: 0 ,  //设置竖向滚动条位置
			style: {
				pageHeight: 0,
				contentViewHeight: 0,
				footViewHeight: 90,
				mitemHeight: 0,
			},
			
			photos: [],
			showPhotograph: true,
			imgId: null, 
			
			changeForm:{  //改变未读消息状态参数对象
				send_uid: null,
				receive_uid: null,
			},
			
			imagePropSrc: "",
			isShowImageProp: false,
		}
	},
	
	created() {
		that = this;
		
		const res = uni.getSystemInfoSync(); //获取手机可使用窗口高度     api为获取系统信息同步接口
		this.style.pageHeight = res.windowHeight;
		this.style.contentViewHeight = res.windowHeight - uni.getSystemInfoSync().screenWidth / 750 * (300); //像素   因为给出的是像素高度 然后我们用的是upx  所以换算一下 
	},
	
	onLoad(e) {
		//获取用户id
		let userInfo  = this.$store.state.user.loginUserInfo;  // 
		if(!userInfo) return false;
		
		
		
		// this.initWebSocket();
		
		// this.init();
		// this.onMessage();
		
		 // 监听socket 是否连接成功
		//     socket.connect((status, ws) => {
		//       // 连接成功
		//       if (status) {
		//         // 向服务端发送消息
		//         ws.emit("connect", { msg: "Hello World" }); // 参数一：发送消息的socket名，参数二: 发送的数据
		//         // 接受服务端传来的消息
		//         ws.on("connect", (res) => { // 参数一：接收消息的socket名，参数二：返回的信息 function 
		//           console.log(res)
		//         });
		
		//       }else {
		//         // ...连接超时
		//       }
		//     })
			
		// uni.connectSocket({
		//     url: 'ws://127.0.0.1:7011/chat',
		//     data() {
		//         return {
		//             x: '',
		//             y: ''
		//         };
		//     },
		//     header: {
		//         'content-type': 'application/json'
		//     },
		//     protocols: ['protocol1'],
		//     method: 'GET'
		// });
		
		
		
		// uni.onSocketClose(function (res) {
		//   console.log('WebSocket 已关闭！');
		// });
	},
	
	onUnload(){
		
		this.closeSocket();
	},
	
	methods: {
		
		//图片添加
		photograph(data){
			// that.photos = data;
			let imgId = data[0].id;
			let imgUrl = data[0].imageUrl;
			
			if(imgId){
				var message = '{"data":"' + imgId + '","type":"say_img","fromid":"' + this.c_uid + '","toid":"' + this.uid + '"}';
				that.socketTask.send({
					data : message,
					success: (e)=>{
						// that.photos = null;
						//成功后，要调用接口，把图片数据保存到数据库中
						this.rquesetForm.info_img = imgId;
						newsApi.getImgAdd(this.rquesetForm, response=>{
							let resp = response.data;
							if(resp.code ==1){
								this.messageList.push({
									type: "right",
									infoType: "img",
									content: imgUrl
								});
							}else{
								this.showToast(resp.msg);
							}
						});
					}
				});
			}
		},
		
		onMessage(){
			// console.log("this.socketTask",  this.socketTask);
			this.socketTask.onMessage(response=>{
				let message = JSON.parse(response.data)
				let type = message.type;
				switch(type){
					case "init":
						var bild = '{"type":"bind","fromid":"' + that.c_uid + '"}';
						
						that.socketTask.send({
							data : bild,
							success: (e)=>{
								newsApi.initNews(this.rquesetForm, response2=>{
									let resp2 = response2.data;
									if(resp2.code ==1 ){
										this.right_img = resp2.data.right_img;
										this.left_img = resp2.data.left_img;
										this.left_nickName = resp2.data.left_nickname;
										this.right_nickname = resp2.data.right_nickname;
										uni.setNavigationBarTitle({
										    title: this.left_nickName
										});
										that.sendPost() //获取消息
									}else{
										this.showToast(resp2.msg);
									}
								})
								
							}
						});
					
					case "text":
						if (that.uid == message.fromid) {
							// //判断是不是第一次发消息
							
							this.messageList.push({
								type: "left",
								infoType: "text",
								content: message.data,
							});
							
							this.$nextTick(()=>{
								this.pageScrollTo();
							})
						
							this.changeNoRead();
						}
						break;
					case "save": //文本发送，如接受消息用户在线就,就存储消息到数据库
						this.rquesetForm.info = message.data;
						that.saveMessage(this.rquesetForm); //保存消息
						if (message.isread == 1) {
							this.online = 1;
							// uni.setNavigationBarTitle({
							//     title: this.left_nickName+ '(在线)'
							// });
						} else {
							this.online = 0;
							// uni.setNavigationBarTitle({
							//     title: this.left_nickName+ '（离线）'
							// });
						}
						break;
					case  "online": //判断发型消息用户是否在线
						if (message.status == 1) {
							this.online = 1;
							// uni.setNavigationBarTitle({
							// 	title: this.left_nickName+ '(在线)'
							// });
						} else {
							this.online = 0;
							// uni.setNavigationBarTitle({
							//     title: this.left_nickName+ '（离线）'
							// });
						
						}
						break;
					}
			});
			
			
			// that.socketTask.
		},
		
		
		//断开websocket连接
		closeSocket(){
			that.socketTask.close({
				code:1000,
				success:function(){
					console.log("socketTask close success ");
				},
				fail:function(res){
					console.log("socketTask close fail ",res);
				}
				
			});
		},
		
		//获取历史数据
		sendPost() {
			newsApi.getList(this.rquesetForm, response=>{
				let resp = response.data;
				if(resp.code ==1){
					let data = resp.data.list;
					if(!data || data.length ==0) return false;
					let nickname = data[0].nickname;
					uni.setNavigationBarTitle({
					    title: nickname
					});
					
					data.forEach((val, key) =>{
						//要判断与上一条消息的时间是否超过10分钟， 超过就显示时间，否则就不显示时间
						if(key == 0){   //第一个不判断, 直接显示日期时间
							this.pushCreateTime(val.create_time);
						}else{
							if( this.compareDateAndPrevious(val.create_time, data[key-1].create_time)){
								this.pushCreateTime(val.create_time);
							}
						}
						
						if (val.send_uid == this.c_uid) {
							//判断是否是图片
							if(val.info_img){
								this.messageList.push({
									type: "right",
									infoType: "img",
									content: val.info_img[0].url
								});
							}else{
								this.messageList.push({
									type: "right",
									infoType: "text",
									content: val.info
								});
							}
							
						} else {
							//判断是否是图片
							if(val.info_img){
								this.messageList.push({
									type: "left",
									infoType: "img",
									content: val.info_img[0].url
								});
							}else{
								this.messageList.push({
									type: "left",
									infoType: "text",
									content: val.info
								});
							}
						}
					});
					//等消息获取完后，滚动条回到底部
					this.$nextTick(()=>{
						this.pageScrollTo();
						
						this.changeNoRead();
					})
					
					
				}
			});
		},
		
		init(){
		},
		
		
		initWebSocket(){  //初始化websocket
		
			this.$socket.emit('chat', '111111111111');
			console.log("创建WebSocket")
			// this.websock = new WebSocket("ws://http://127.0.0.1:7011/io/chat/index")
			this.websock = new WebSocket("http://127.0.0.1:7011/io/chat/index")
			this.websock.onmessage = this.websocketonmessage
			this.websock.onerror = this.websocketonerror
			this.websock.onopen = this.websocketonopen
			this.websock.onclose = this.websocketclose
		},
		
		
		// 连接建立之后执行send方法发送数据
		websocketonopen () {
		   let data = {
		     code: 0,
		     msg: '这是client：初次连接'
		   }
		   this.websocketsend(data)
		},
		
		websocketonerror () {
		  console.log( 'WebSocket连接失败')
		},
		
		// 数据接收
		websocketonmessage (e) {
		   console.log('数据接收' + e.data)
		},
		
		// 数据发送
		websocketsend (Data) {
		   this.websock.send(Data)
		},
		
		// 关闭
		websocketclose (e) {
		   console.log('已关闭连接', e)
		},
		
		//获取输入框的内容
		onInput(e){
			this.inputValue = e.target.value;
		},
		
		submitMessage(){
			let text = this.inputValue;
			if(text){
				var message = '{"data":"' + text + '","type":"say","fromid":"' + this.c_uid + '","toid":"' + this.uid + '"}';
				that.socketTask.send({
					data : message,
					success: (e)=>{
						this.inputValue = "";
					},
					error: (e)=>{
						console.log(e);
					}
				});
			}
		},
		
		//保存数据
		saveMessage(form) {
			newsApi.getAdd(form, response=>{
				let resp = response.data;
				if(resp.code == 1){
					// this.showToast
					let data = resp.data;
					if(this.timeList.length > 0){
						let lastTime = this.timeList[this.timeList.length -1].time;  //最后一条对话的时间
						if(this.compareDateAndPrevious(data.create_time, lastTime)){
							this.pushCreateTime(data.create_time);
						}
					}else{
						// 第一次发消息， 则时间为当前时间
						let date = new Date().valueOf() / 1000 ;
						this.pushCreateTime(date);
					}
					
					
					this.messageList.push({
						type: "right",
						infoType: "text",
						content: form.info
					});
					
					this.$nextTick(()=>{
						this.pageScrollTo();
					})
					
				}else{
					this.showToast(resp.msg);
				}
			});
		},
		
		openPopupImage(url){
			this.isShowImageProp = true;
			this.imagePropSrc = url;
		},
		
		closeImage(){
			this.isShowImageProp = false;
			this.imagePropSrc = "";
		},
		
		
		//这个是用来存放日期时间
		pushCreateTime(create_time){
			this.messageList.push({
				time: create_time,
				time_text: chatTimestampToTime(create_time)
			});
			
			this.timeList.push({
				time: create_time,
				time_text: chatTimestampToTime(create_time)
			});
		},
		
		
		//屏幕滚动
		pageScrollTo(){
			
			let query = uni.createSelectorQuery();
			query.selectAll('.message_item').boundingClientRect();
			query.select('.messageList').boundingClientRect();
			query.exec((res) => {
				that.style.mitemHeight = 0;
				res[0].forEach((rect) => that.style.mitemHeight = that.style.mitemHeight + rect.height + 40) //获取所有内部子元素的高度
				// 因为vue的虚拟DOM 每次生成的新消息都是之前的，所以采用异步setTimeout    主要就是添加了这定时器
				setTimeout(() => {
					if (that.style.mitemHeight > (that.style.contentViewHeight - 100)) { //判断子元素高度是否大于显示高度
						that.scrollTop = that.style.mitemHeight - that.style.contentViewHeight //用子元素的高度减去显示的高度就获益获得序言滚动的高度
					}
				}, 100)
			})
		},
		
		
		//改变未读消息状态
		changeNoRead() {
			newsApi.changeNoRead(this.changeForm, response=>{
				let resp = response.data;
				if(resp.code ==1){
				}else{
					this.showToast(resp.msg);
				}
			});
		},
		
		
		
		
		
		/**  判断当前时间和上一个时间的间隔是否超过10分钟， 
		 * @param {Object} date  这个是当前时间
		 * @param {Object} previousDate  这个是当前上一个时间
		 */
		compareDateAndPrevious(date, previousDate){
			let flag = (date - previousDate) > 60 * 10;
			return flag;    //判断是否超过10分钟
		}
	},
	
	
	
}
</script>

<style lang="less" scoped>
.messageDetailBody{
	overflow: hidden;
	background-color: #e8e7e7;
	
	.messageList{
		position: relative;
		left: 0;
		top: 0;
		width: 100%;
		height: 90%;
		overflow-y: scroll;
		
		.message_item{
			position: relative;
			display: block;
			
			&:after{
				clear: both;
				content: " ";
				display: block;
				height: 0;
			}
			
			
			.time{
					position: relative;
					top: 33rpx;
					width: 100%;
					height: 75rpx;
					text-align: center;
					font-size: 29rpx;
					// padding-top: 148rpx;
					line-height: 75rpx;
					box-sizing: border-box;
				}
				
				.message{
					position: relative;
					// height: 136rpx;
					
					.imgImgLeft, .textImgLeft, .imgImgRight, .textImgRight{
						position: relative;
						top: 28rpx;
						width: 81.25rpx;
						height: 81.25rpx;
						border-radius: 50%;
						display: block;
						margin-top: 10.66rpx;
						display: inline-block;
					}
					
					.imgImgLeft{
						left: 10rpx;
						top: -44rpx;
					}
					
					.textImgLeft{
						left: 10rpx;
					}
					
					.imgImgRight{
						left: 667rpx;
						top: -44rpx;
					}
					
					.textImgRight{
						left: 667rpx;
					}
					
					.contentImgLeft, .contentImgRight{
						position: relative;
						top: 49rpx;
						padding: 12rpx;
						margin-top: 10.66rpx;
						width: 243rpx;
						height: 172rpx;
						background-color: rgba(0,0,0,0);
						display: inline-block;
					}
					
					.contentImgLeft{
						left: 11rpx;
						// float: left;
					}
					
					.contentImgRight{
						left: 325rpx;
						// float: right;
					}
					
					
					.contentLeft, .contentRight{
						position: relative;
						top: -71rpx;
						border-radius: 13rpx;
						padding: 12rpx;
						max-width: 387.5rpx;
						font-size: 38.91rpx;
						margin-top: 10.66rpx;
						width: fit-content;  // 宽度随内容自适应  、
						white-space: normal;  //换行显示
						word-break: break-word;  //在恰当的断字点进行换行：
					}
					
					.contentLeft{
						left: 100rpx;
						background-color: #fff;
						color: black;
						
					}
					
					.contentRight{
						background-color: #199ed8;
						color: #fff;
						right: 105rpx;
						float: right;
						top: -16rpx;
						margin-top: 76rpx;
					}
					
					.clear{
						clear: both;
					}
				}
			}
		}
		
		.inputForm{
			position: absolute;
			left: 0;
			bottom: 0;
			width: 100%;
			height: 89.58rpx;
			
			.inputValue{
				position: absolute;
				left: 18rpx;
				top: 15rpx;
				width: 513rpx;
				border-radius: 12rpx;
				height: 60.41rpx;
				background-color: #fff;
				padding-left: 14rpx;
				font-size: 34.91rpx;
			}
		
			/deep/ .inputImg{
				position: absolute;
				left: 551rpx;
				top: 21rpx;
				width: 62.5rpx;
				height: 50rpx;
				
				.photograph{
					top: -3rpx;
					left: -9rpx;
					width: 78rpx;
					height: 61rpx;
					background-size: 73rpx 58rpx;
				}
			}
			
			.submitBtn{
				position: absolute;
				left: 621rpx;
				top: 21rpx;
				width: 126.5rpx;
				height: 50rpx;
				font-size: 30rpx;
				line-height: 50rpx;
				text-align: center;
				background-color: cadetblue;
			}
		}
}
</style>
