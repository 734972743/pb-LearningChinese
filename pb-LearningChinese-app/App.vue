<script>
	export default {
		onLaunch: function() {
		},
		onShow: function() {
		},
		onHide: function() {
		}
	}
</script>

<style lang="less">
	/*每个页面公共css */
	
	input::placeholder {
	        color: red;
	}
	input::-webkit-input-placeholder{
		color: red;
	}
	input::-moz-placeholder{   /* Mozilla Firefox 19+ */
		color:red;
	}
	input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
		color:red;
	}
	input:-ms-input-placeholder{  /* Internet Explorer 10-11 */
		color:red;
	}
	
	.pageBody{
		position: relative;
		left: 0;
		top: 0;
		width: 100vw;
		height: 100vh;
		overflow: hidden;
	}
	
	.submit{
		background-color: @uni-button-bg-color !important;
		font-size: 37.5rpx !important;
		line-height: 83.3rpx;
		
		&:hover{
			background-color: @uni-button-bg-color-hover !important;
		}
	}
	
	
	
	
</style>
