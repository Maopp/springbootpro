# springbootpro
spring boot pro

------------------------------------------------------------------------------------------------------------------------
静态文件放到/resources/static目录下，可以直接访问，默认不会被拦截；添加资源映射，将/static文件下内容映射到/catpp/resources

LogBack读取配置文件的步骤
（1）尝试classpath下查找文件logback-test.xml
（2）如果文件不存在，尝试查找logback.xml
（3）如果两个文件都不存在，LogBack用BasicConfiguration自动对自己进行最小化配置

redis常用命令：
flushdb：清空当前数据库。
select [index]：选择索引数据库，index为索引值名，如：select 1。
del [key]：删除一条指定key的值。
keys *：查看数据库内所有的key。
flushall：清空所有数据库。
quit：退出客户端连接。

------------------------------------------------------------------------------------------------------------------------
JWT是一种用户双方之间传递安全信息的简洁的、URL安全的表述性声明规范。
JWT（Json Web Token）作为一个开放的标准（RFC 7519），定义了一种简洁的、自包含的方法
用于通信双方之间以Json对象的形式进行安全性信息传递，传递时有数字签名所以信息时安全的，
JWT使用RSA公钥密钥的形式进行签名。

JWT组成
JWT格式的输出是以.分隔的三段Base64编码，与SAML等基于XML的标准相比，JWT在HTTP和HTML环境中更容易传递。（形式：xxxxx.yyy.zzz）：

1、Header：头部
2、Payload：负载
3、Signature：签名

Header
在header中通常包含了两部分，Token类型以及采用加密的算法

Payload
Token的第二部分是负载，它包含了Claim，Claim是一些实体（一般都是用户）的状态和额外的数据组成。

Signature
创建签名需要使用编码后的header和payload以及一个秘钥，使用header中指定签名算法进行签名。
JWT工作流程图
JWT客户端发送请求到服务器端整体流程：mine/jwt.webp


------------------------------------------------------------------------------------------------------------------------
WebSocket为浏览器和服务端提供了双工异步通信的功能，浏览器可以向服务端发送消息，服务端也可以向浏览器发送消息。
WebSocket需要浏览器的支持，目前大多数主流的浏览器都是支持的。

WebSocket是通过socket来实现双工异步通信能力的。但是直接使用WebSocket协议开发程序闲的特别繁琐，
一般情况我们使用STOMP来实现交互。

SpringBoot在tomcat7内嵌版本就已经开始支持了WebSocket的支持，
配置源码存在于org.springframework.boot.autoconfigure.websocket下

------------------------------------------------------------------------------------------------------------------------
全局异常处理：
@ControllerAdvice注解是用来配置控制器通知的，我们可以配置过滤拦截具体一种或者多种类型的注解，添加annotations属性即可，
因为我们全局返回的都是Json格式的字符串，所以需要再类上配置@ResponseBody注解
# 使用系统日志功能，记录持久化到数据库


------------------------------------------------------------------------------------------------------------------------
CORS跨域请求：
CORS（Cross-Origin Resource Sharing）"跨域资源共享"，是一个W3C标准，它允许浏览器向跨域服务器发送Ajax请求，
打破了Ajax只能访问本站内的资源限制，CORS在很多地方都有被使用，微信支付的JS支付就是通过JS向微信服务器发送跨域请求。
开放Ajax访问可被跨域访问的服务器大大减少了后台开发的工作，前后台工作也可以得到很好的明确以及分工
# 配置跨域参数就可以实现跨域啦
