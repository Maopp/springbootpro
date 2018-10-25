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
跨域请求测试html文件路径：src/main/webapp/WEB-INF/mine/cors-test.html


------------------------------------------------------------------------------------------------------------------------
使用@Scheduled注解完成定时任务
定时任务一般会存在中大型企业级项目中，为了减少服务器、数据库的压力往往会采用时间段性的去完成某些业务逻辑。
比较常见的就是金融服务系统推送回调，一般支付系统订单在没有收到成功的回调返回内容时会持续性的回调，
这种回调一般都是定时任务来完成的。还有就是报表的生成，我们一般会在客户访问量过小的时候来完成这个操作，那往往都是在凌晨。
这时我们也可以采用定时任务来完成逻辑。SpringBoot为我们内置了定时任务，我们只需要一个注解就可以开启定时为我们所用了

# 定时任务类需要添加@Component注解

@Scheduled注解属性：
1、cron属性
这是一个时间表达式，可以通过简单的配置就能完成各种时间的配置，我们通过CRON表达式几乎可以完成任意的时间搭配，它包含了六或七个域：

Seconds : 可出现", - * /"四个字符，有效范围为0-59的整数
Minutes : 可出现", - * /"四个字符，有效范围为0-59的整数
Hours : 可出现", - * /"四个字符，有效范围为0-23的整数
DayofMonth : 可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
Month : 可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
DayofWeek : 可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推
Year : 可出现", - * /"四个字符，有效范围为1970-2099年

下面简单举几个例子：

"0 0 12 * * ?"    每天中午十二点触发
"0 15 10 ? * *"    每天早上10：15触发
"0 15 10 * * ?"    每天早上10：15触发
"0 15 10 * * ? *"    每天早上10：15触发
"0 15 10 * * ? 2005"    2005年的每天早上10：15触发
"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发

2、fixedRate属性
该属性的含义是上一个调用开始后再次调用的延时（不用等待上一次调用完成），这样就会存在重复执行的问题，所以不是建议使用，
但数据量如果不大时在配置的间隔时间内可以执行完也是可以使用的。

3、fixedDelay属性
该属性的功效与上面的fixedRate则是相反的，配置了该属性后会等到方法执行完成后延迟配置的时间再次执行该方法。

4、initialDelay属性
该属性跟上面的fixedDelay、fixedRate有着密切的关系，为什么这么说呢？该属性的作用是第一次执行延迟时间，
只是做延迟的设定，并不会控制其他逻辑，所以要配合fixedDelay或者fixedRate来使用


------------------------------------------------------------------------------------------------------------------------
使用ApplicationEvent&Listener完成业务解耦：
ApplicationEvent以及Listener是Spring为我们提供的一个事件监听、订阅的实现，内部实现原理是观察者设计模式，
设计初衷也是为了系统业务逻辑之间的解耦，提高可扩展性以及可维护性。事件发布者并不需要考虑谁去监听，
监听具体的实现内容是什么，发布者的工作只是为了发布事件而已。

# 在Spring内部中有多种方式实现监听如：@EventListener注解、实现ApplicationListener泛型接口、实现SmartApplicationListener接口等
1、@EventListener实现监听
注解方式比较简单，并不需要实现任何接口
只需要让我们的监听类被Spring所管理即可，在我们用户注册监听实现方法上添加@EventListener注解，该注解会根据方法内配置的事件完成监听。

2、实现接口ApplicationListener实现监听
这种方式也是Spring之前比较常用的监听事件方式，在实现ApplicationListener接口时需要将监听事件作为泛型传递
实现接口后需要使用@Component注解来声明该监听需要被Spring注入管理，
本例中，当有UserRegisterEvent事件发布时监听程序会自动调用onApplicationEvent方法并且将UserRegisterEvent对象作为参数传递。

3、实现接口SmartApplicationListener实现有序监听
SmartApplicationListener接口继承了全局监听ApplicationListener，并且泛型对象使用的ApplicationEvent来作为全局监听，
可以理解为使用SmartApplicationListener作为监听父接口的实现，监听所有事件发布。

既然是监听所有的事件发布，那么SmartApplicationListener接口添加了两个方法supportsEventType、supportsSourceType来作为区分是否是我们监听的事件，
只有这两个方法同时返回true时才会执行onApplicationEvent方法。

***提供了一个getOrder方法，这个方法就可以解决执行监听的顺序问题，return的数值越小证明优先级越高，执行顺序越靠前。

# 使用@Async实现异步监听
@Aysnc其实是Spring内的一个组件，可以完成对类内单个或者多个方法实现异步调用，这样可以大大的节省等待耗时。
内部实现机制是线程池任务ThreadPoolTaskExecutor，通过线程池来对配置@Async的方法或者类做出执行动作。


------------------------------------------------------------------------------------------------------------------------
使用AutoConfiguration自定义starter：
新建maven工程，打包，在本工程中引入
@ConfigurationProperties，该注解可以完成将application.properties配置文件内的有规则的配置参数映射到实体内的field内，不过需要提供setter方法
1、实体bean
2、业务代码
3、自定义starter自动化配置

# 相关注解说明：
@EnableConfigurationProperties：这是一个开启使用配置参数的注解，value值就是我们配置实体参数映射的ClassType，将配置实体作为配置来源。
SpringBoot内置条件注解，以下注解都是元注解@Conditional演变而来的，根据不同的条件对应创建以上的具体条件注解：
    @ConditionalOnBean：当SpringIoc容器内存在指定Bean的条件
    @ConditionalOnClass：当SpringIoc容器内存在指定Class的条件
    @ConditionalOnExpression：基于SpEL表达式作为判断条件
    @ConditionalOnJava：基于JVM版本作为判断条件
    @ConditionalOnJndi：在JNDI存在时查找指定的位置
    @ConditionalOnMissingBean：当SpringIoc容器内不存在指定Bean的条件
    @ConditionalOnMissingClass：当SpringIoc容器内不存在指定Class的条件
    @ConditionalOnNotWebApplication：当前项目不是Web项目的条件
    @ConditionalOnProperty：指定的属性是否有指定的值
    @ConditionalOnResource：类路径是否有指定的值
    @ConditionalOnSingleCandidate：当指定Bean在SpringIoc容器内只有一个，或者虽然有多个但是指定首选的Bean
    @ConditionalOnWebApplication：当前项目是Web项目的条件