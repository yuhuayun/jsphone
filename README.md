### Js 软电话客户端实现 

####快速开始：
    
   1、代码位置
    
    git clone http://sinosoft.vicp.io:12916/cmp/jsphone.git

   2、启动项目 JsPhoneApplication main() 
   
   3、访问  http://localhost:8000/index.html
   
####修改端口
   application.properties port 端口
   
   修改 index.html 文件中  44 行 
   
   initParams() 
   
   //本地址是软电话状态测试界面
   var platServiceUrl = "http://localhost:8000/jsphone/testJsPhoneServlet"; //软电话中间件的服务基地址URL
   
####代码结构
   /resources/  资源文件
   
   soapclient.js  soap协议封装包
   