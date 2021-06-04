let http = require("http");
let fs = require("fs");

let app = http.createServer((req, res)=>{
  fs.readFile("app.html", (err,data)=>{
    res.writeHead(200, {"Content-Type":"text/html;charset='utf-8'"});
    res.end(data)
  })
});

app.listen(3000)


console.log("http://127.0.0.1:3000");

//1 安装 socket.io


//2. 引入socket.io
let io = require("socket.io")(app);


io.on("connection" ,socket=>{

  console.log("服务器建立连接");
});

