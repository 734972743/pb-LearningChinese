

 
 const path = require('path')
 // const CopyWebpackPlugin = require('copy-webpack-plugin') //最新版本copy-webpack-plugin插件暂不兼容，推荐v5.0.0
 let vars = path.resolve(__dirname, 'variables.less') ;
 
 module.exports = {
	 css: {
	         loaderOptions: {  
	             less: {  
	                 globalVars: {  
						 "hack": `true; @import "${vars}"`  
					 } 
	             }  
	         }  
	     }  
 }