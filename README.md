# springbootpro
spring boot pro


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
