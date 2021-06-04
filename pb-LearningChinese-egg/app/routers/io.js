//这是socket io路由
module.exports = app => {

  const { router, controller, io } = app;

  //user
  io.of('/').route("chat", io.controller.chat.index); 

}