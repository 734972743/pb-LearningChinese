module.exports = app => {
    const mongoose = app.mongoose;
    const Schema = mongoose.Schema;
    let d = new Date();
   
    //轮播图模型
    const CarouselSchema = new Schema({

      title: { //轮播图标题
        type: String,
        default: ''
     },  

     link: { //链接地址
        type: String,
        default: ''
      },  

      img_url:{  //图片url
        type: String,
        default: ''
      },

      type:{  //轮播图类型
        type: Number,
      },

      status: {     //状态  1:显示，0隐藏
        type: Number,
        default: 1
      },

      add_time:{  //创建时间
        type: Number,
        default: d.getTime()
      },

      sort:{ //排序 
        type: Number,
        default: 100 
      }
  
    });
  
  
    
   
    return mongoose.model('Carousel', CarouselSchema, "carousel");
  }
  
  