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


------------------------------------------------------------------------------------------------------------------------
自定义spring boot启动banner：
1、在src/main/resource下添加一个名叫banner.txt的文件，将需要修改的内容写入到该文件内就可以啦
2、配置字体颜色：${AnsiColor.BRIGHT_RED}

3、配置背景颜色：${AnsiBackground.BRIGHT_RED}

# 添加的文字内容可以通过banner在线生成工具来生成：
    http://www.bootschool.net/ascii
    http://patorjk.com/software/taag/#p=display&f=%E6%B6%82%E9%B8%A6&t=Type%20Something%20
    https://www.degraeve.com/img2txt.php
    http://www.network-science.de/ascii/

${AnsiColor.BRIGHT_RED}：设置控制台中输出内容的颜色
${application.version}：用来获取MANIFEST.MF文件中的版本号
${application.formatted-version}：格式化后的${application.version}版本信息
${spring-boot.version}：Spring Boot的版本号
${spring-boot.formatted-version}：格式化后的${spring-boot.version}版本信息


------------------------------------------------------------------------------------------------------------------------
基于SpringBoot架构重写springMVC请求参数装载：


------------------------------------------------------------------------------------------------------------------------
基于SpringBoot架构以及参数装载完成接口安全认证：
实现思路是采用SpringMvc拦截器来完成指定注解的拦截，并且根据拦截做出安全属性的处理，再结合自定义的参数装载完成对应参数的赋值
https://blog.csdn.net/weixin_42033269/article/details/80035814

前端对数据进行加密（参考测试方法）发起请求，配置拦截器对数据进行解密和重新封装到request的attribute中（因为request的parameter不允许修改），
然后在参数校验处理中对数据重新进行封装返回（resolveArgument方法）

加密方法做成多套，通过枚举选择使用哪种加密方式，使用配套的解密方法进行解密，本例做了两套（AES/DES）


------------------------------------------------------------------------------------------------------------------------
基于SpringBoot架构适当使用Profile完成打包环境分离：
说明：
    在中大型企业项目开发中，环境分离是必不可少的一步，然而现在的开发人员也只是有这个概念，还是有很多项目采用普通的方式，
    每次打包发布部署的时候改动一大堆的配置文件，有一个地方忘记改就相当于白更新了一次系统，这种修改配置文件完成环境更换的
    方式给我们带来了很多的困扰，浪费了我们很多宝贵的时间！早在Spring 3.1版本就已经为我们提供了环境分离的相关注解配置方式，
    不过在传统的Spring项目中配置Profile确实有点麻烦，在Spring版本的不断更新直到后来SpringBoot成长起来后Profile已经能够
    很好支持项目配置环境分离。

配置Profile环境：
    在SpringBoot内已经为了约定好了Profile配置文件的命名规则，即：application-xxx.properties或者application-xxx.yml，
    我们只需要将对应环境的配置文件放到resources目录下即可，也就是classpath下

激活Profile：
    在applicatio.properties文件中配置spring.profiles.active=dev/beta/prod就可以啦


------------------------------------------------------------------------------------------------------------------------
基于Spring Boot 和 Quartz完成定时任务分布式单节点持久化：
1、为什么要持久化定时任务？
    在一些项目中定时任务可能是必不可少的，由于某种特殊的原因定时任务可能丢失，如重启定时任务服务项目后，原内存中的定时
    任务就会被完全释放！那对于我们来说可能是致命的问题。当然也有强制的办法解决这类问题，但是如果我们把定时任务持久化到
    数据库，像维护普通逻辑数据那样维护任务，就会避免项目中遇到的种种的特殊情况。

quartz与Spring相关框架的整合方式有很多种，本例采用jobDetail使用Spring Ioc托管方式来完成整合，我们可以在定时任务实例
中使用Spring注入注解完成业务逻辑处理

QuartzConfiguration配置类说明：
    1、AutowiringSpringBeanJobFactory我们继承了SpringBeanJobFactory类，并且通过实现ApplicationContextAware接口
    获取ApplicationContext设置方法，通过外部实例化时设置ApplicationContext实例对象，在createJobInstance方法内，
    我们采用AutowireCapableBeanFactory来托管SpringBeanJobFactory类中createJobInstance方法返回的定时任务实例，
    这样我们就可以在定时任务类内使用Spring Ioc相关的注解进行注入业务逻辑实例了

    2、任务工厂是在本章配置调度器时所需要的实例，我们通过jobFactory方法注入ApplicationContext实例，来创建
    一个AutowiringSpringBeanJobFactory对象，并且将对象实例托管到Spring Ioc容器内。

    3、本例采用的是项目内部数据源的方式来设置调度器的jobSotre，官方quartz有两种持久化的配置方案。

    第一种：采用quartz.properties配置文件配置独立的定时任务数据源，可以与使用项目的数据库完全独立。
    第二种：采用与创建项目统一个数据源，定时任务持久化相关的表与业务逻辑在同一个数据库内。

    可以根据实际的项目需求采取不同的方案，本例主要是通过第二种方案来进行讲解，在上面配置类中可以看到
    方法schedulerFactoryBean内自动注入了JobFactory实例，也就是我们自定义的AutowiringSpringBeanJobFactory任务工厂实例，
    另外一个参数就是DataSource（在引入spring-starter-data-jpa依赖后会根据application.yml文件内的数据源相关配置自动
    实例化DataSource实例，这里直接注入是没有问题的）。

    我们通过调用SchedulerFactoryBean对象的setConfigLocation方法来设置quartz定时任务框架的基本配置，
    配置文件所在位置：resources/quartz.properties => classpath:/quartz.properties下。

# qrtz_job_details表中存放的时待执行的定时任务


------------------------------------------------------------------------------------------------------------------------
基于SpringBoot 和 Quartz完成定时任务分布式多节点负载持久化：
1. org.quartz.scheduler.instanceId ： 定时任务的实例编号，如果手动指定需要保证每个节点的唯一性，因为quartz不允许出现两个
相同instanceId的节点，我们这里指定为Auto就可以了，我们把生成编号的任务交给quartz。

2. org.quartz.jobStore.isClustered： 这个属性才是真正的开启了定时任务的分布式配置，当我们配置为true时quartz框架就会
调用ClusterManager来初始化分布式节点。

3. org.quartz.jobStore.clusterCheckinInterval：配置了分布式节点的检查时间间隔，单位：毫秒。

# 开启分布式定时任务之后修改打印信息和项目端口号，可以看到任务自动漂移到其他其他节点上啦。

模拟商品秒杀提醒业务：
    1、当我们添加商品后自动添加一个商品提前五分钟的秒杀提醒，为关注该商品的用户发送提醒消息。
    2、在秒杀提醒任务逻辑中，我们通过获取JobDetail的JobDataMap集合来获取在创建任务的时候传递的参数集合，我们这里约定了goodId
    为商品的编号，在创建任务的时候传递到JobDataMap内，这样quartz在执行该任务的时候就会自动将参数传递到任务逻辑中，我们也
    就可以通过JobDataMap获取到对应的参数值。
    3、我们模拟秒杀提醒时间是添加商品后的5分钟，我们通过调用jobDetail实例的getJobDataMap方法就可以获取该任务数据集合，直
    接调用put方法就可以进行设置指定key的值，该集合继承了StringKeyDirtyFlagMap并且实现了Serializable序列化，因为需要将数据
    序列化到数据库的qrtz_job_details表内。


------------------------------------------------------------------------------------------------------------------------
基于Spring Boot & RabbitMQ完成DirectExchange分布式消息消费：
消息队列目前流行的有KafKa、RabbitMQ、ActiveMQ等，它们的诞生无非就是为了解决消息的分布式消费，完成项目、服务之间的解耦动作。4
消息队列提供者与消费者之间完全采用异步通信方式，极力的提高了系统的响应能力，从而提高系统的网络请求吞吐量。
每一种的消息队列都有它在设计上的独一无二的优势，在实际的项目技术选型时根据项目的需求来确定。

https://blog.csdn.net/weixin_42033269/article/details/80035839

Exchange
在RabbitMQ中有三种常用的转发方式，分别是：
    DirectExchange：路由键方式转发消息；
    FanoutExchange：广播方式转发消息；
    TopicExchange：主题匹配方式转发消息

因为RabbitMQ是跨平台的分布式消息队列服务，可以部署在任意的操作系统上，下面我们分别介绍在不同的系统下该怎么去安装RabbitMQ服务。

Windows下安装
我们先去RabbitMQ官方网站下载最新版的安装包，下载地址：https://www.rabbitmq.com/download.html，可以根据不同的操作系统选择下载。
我们在安装RabbitMQ服务端时需要Erlang环境的支持，所以我们需要先安装Erlang。
1. 我们通过Erlang官方网站http://www.erlang.org/downloads下载最新的安装包
    我们访问RabiitmQ官方下载地址https://www.rabbitmq.com/download.html下载最新安装包。

启动插件：
     rabbitmq_server-3.7.8\sbin目录执行如下命令：
     .\rabbitmq-plugins.bat enable rabbitmq_management
     ---
     Enabling plugins on node rabbit@DESKTOP-RPROTA7:
     rabbitmq_management
     The following plugins have been configured:
       rabbitmq_management
       rabbitmq_management_agent
       rabbitmq_web_dispatch
     Applying plugin configuration to rabbit@DESKTOP-RPROTA7...
     The following plugins have been enabled:
       rabbitmq_management
       rabbitmq_management_agent
       rabbitmq_web_dispatch

     started 3 plugins.
     ---
     访问http://127.0.0.1:15672地址可以直接打开RabbitMQ的界面管理平台，而默认的用户名/密码分别为：guest/guest，
     通过该用户可以直接登录管理平台。
禁用界面管理插件：
    rabbitmq-plugins.bat disable rabbitmq_management

消息队列配置类内容：UserRegisterQueueCongiguration
    配置交换实例：
    配置DirectExchange实例对象，为交换设置一个名称，引用ExchangeEnum枚举配置的交换名称，消息提供者与消息消费者的交换名称
    必须一致才具备的第一步的通讯基础。
    配置队列实例：
    配置Queue实例对象，为消息队列设置一个名称，引用QueueEnum枚举配置的队列名称，当然队列的名称同样也是提供者与消费者之间
    的通讯基础。
    绑定队列实例到交换实例：
    配置Binding实例对象，消息绑定的目的就是将Queue实例绑定到Exchange上，并且通过设置的路由Key进行消息转发，配置了路由Key
    后，只有符合该路由配置的消息才会被转发到绑定交换上的消息队列。

# 目前需要在可视化平台进行手动维护exchanges/queue/routingKey绑定
# 修改工程目录结构，是启动类扫描到common工程的com.catpp.rabbitmq.common.config.UserRegisterQueueCongiguration配置类，初始化
rabbitmq队列/exchange信息，就不用再管理平台手动维护队列数据啦


------------------------------------------------------------------------------------------------------------------------
基于Spring Boot & RabbitMQ完成TopicExchange分布式消息消费：
TopicExchange类型消息队列可以根据路径信息配置多个消息消费者，而转发的匹配规则信息则是我们定义的队列的路  由信息。

在发送消息到队列时，需要我们传递一个路由相关的配置信息，RabbitMQ会根据发送时的消息路由规则信息与定义消息队列时的路由信息
进行匹配，如果可以匹配则调用该队列的消费者完成消息的消费：com.catpp.rabbitmq.common.enums.topic.TopicEnum

路由特殊字符 #
    我们在com.catpp.rabbitmq.common.enums.topic.QueueEnum内配置的路由键有个特殊的符号：#，在RabbitMQ消息队列内路由配置#时
    表示可以匹配零个或多个字符，TopicEnum枚举内定义的register.user，则是可以匹配QueueEnum枚举定义register.#队列的路由规则。
    当然发送消息时如果路由传递：register.user.account也是可以同样匹配register.#的路由规则。

路由特殊字符 *
    除此之外比较常用到的特殊字符还有一个*，在RabbitMQ消息队列内路由配置*时表示可以匹配一个字符，我们QueueEnum定义路由键
    如果修改成register.*时，发送消息时路由为register.user则是可以接受到消息的。但如果发送时的路由为register.user.account时，
    则是无法匹配该消息。

# @RabbitListener监听注解一定要注解在方法上，不然消费出错，会循环消费，内存就炸啦


------------------------------------------------------------------------------------------------------------------------
基于Spring Boot & RabbitMQ完成消息延迟消费：
将消息先发送到ttl延迟队列内，当消息到达过期时间后会自动转发到ttl队列内配置的转发Exchange以及RouteKey绑定的队列内完成消息消费。

# 配置交换和队列的时候要使用@Qualifier/使用不同变量名，不然实例化单例bean会报错

配置延迟队列的时候配置两个参数值：
    x-dead-letter-exchange：配置到期后转发的交换
    x-dead-letter-routing-key：配置到期后转发的路由键


------------------------------------------------------------------------------------------------------------------------
Spring Boot 2.0新特性-RabbitMQ信任package设置：
SpringBoot升级后，之前的系统内使用实体传输受到了限制，如果使用SpringBoot默认的序列化方式不会出现信任package的问题，之所以
出现这个问题是因为项目使用fastjson方式进行类的序列化和反序列化，在之前SpringBoot 1.5.10版本的时候 RabbitMQ依赖内的
DefaultClassMapper类在构造函数内配置*，表示信任项目内的所有package，在SpringBoot 2.0.0版本时，DefaultClassMapper类源码
构造函数进行了修改，不再信任全部package，而是仅仅信任java.util、java.lang。

之前都是采用的Enum方式来配置队列相关的Exchange、Name、 RouteKey等相关的信息，使用枚举有个弊端，无法在注解内作为属性的值使用，
所以之前的Consumer类配置监听的队列时都是字符串的形式，这样后期修改时还要修改多个地方（当然队列信息很少变动），本例使用
Constants常量的形式进行配置


------------------------------------------------------------------------------------------------------------------------
SpringBoot2.0新特性-Quartz自动化配置集成：
com.catpp.springbootpro.config.QuartzConfiguration配置类不需要手动配置啦，quartz自动配置啦。

我们找到Idea的External Libraries并且展开spring-boot-autoconfigure-2.0.0.RELEASE.jar，找到
org.springframework.boot.autoconfigure.quartz，该目录就是SpringBoot为我们提供的Quartz自动化配置源码实现，在该目录下有如下所示几个类：
- AutowireCapableBeanJobFactory
该类替代了我们之前在QuartzConfiguration配置类的AutowiringSpringBeanJobFactory内部类实现，主要作用是我们自定义的
QuartzJobBean子类被Spring IOC进行托管，可以在定时任务类内使用注入任意被Spring IOC托管的类。
- JobStoreType
该类是一个枚举类型，定义了对应application.yml、application.properties文件内spring.quartz.job-store-type配置，其目的是配置
quartz任务的数据存储方式，分别为：MEMORY（内存方式：默认）、JDBC（数据库方式）。
- QuartzAutoConfiguration
该类是自动配置的主类，内部配置了SchedulerFactoryBean以及JdbcStoreTypeConfiguration，使用QuartzProperties作为属性自动化配置条件。
- QuartzDataSourceInitializer
该类主要用于数据源初始化后的一些操作，根据不同平台类型的数据库进行选择不同的数据库脚本。
- QuartzProperties
该类对应了spring.quartz在application.yml、application.properties文件内开头的相关配置。
- SchedulerFactoryBeanCustomizer
这是一个接口，我们实现该接口后并且将实现类使用Spring IOC托管，可以完成SchedulerFactoryBean的个性化设置，这里的设置完全
可以对SchedulerFactoryBean做出全部的设置变更。

----------------------------
注释掉com.catpp.springbootpro.config.QuartzConfiguration配置类的@Configuration注解，在application-dev.properties配置文件
中添加quartz相关属性/持久化方式配置（取代quartz.properties配置文件），调用/goods/save方法，完成自动化配置

###########################################################################################
# External Libraries目录下：spring-boot-autoconfigure:2.0.0.RELEASE工程完成各种自动化配置 #
###########################################################################################


------------------------------------------------------------------------------------------------------------------------
Spring Boot2.0 快速集成Redis：
注释掉com.catpp.springbootpro.config.RedisConfiguration配置类的@configuration注解，在application.properties配置文件中添加
redis相关配置，可以省略配置，默认已经有了相关配置，在项目启动类上添加@EanbleCaching注解，在需要缓存的业务方法上添加
@Cacheable(cacheNames = "spring2.0.service.user")注解就可以了哦。


------------------------------------------------------------------------------------------------------------------------
基于SpringBoot2 & MongoDB完成自动化配置：
MongoDB在企业级项目中一般用于存储文档信息、图片资源等，MongoDB的内容完全是以 JSON字符串的形式进行存储的，所以我们在获取
数据时通过简单的 反序列化就可以完成与项目内的实体类转换，不过这个过程是自动的，不需要我们手动进行反序列化处理。

打开mongo.exe命令面板：
    创建数据库：use test_db
        创建成功：switched to db test_db
    创建数据库所有者角色的用户：db.createUser({user:"test",pwd:"123qwe",roles:[{role:"dbOwner",db:"test_db"}]})
        创建成功：Successfully added user: {
                         "user" : "test",
                         "roles" : [
                                 {
                                         "role" : "dbOwner",
                                         "db" : "test_db"
                                 }
                         ]
                 }

spring-boot-starter-data-mongodb确实采用了跟spring-boot-starter-data-jpa同样的方式来完成接口代理类的生成，并且提供了一些
常用的单个对象操作的公共方法，MongoRepository接口作用与JPARepository一致，继承了该接口的业务数据接口就可以提供一个被
Spring IOC托管的代理实现类，这样我们在注入业务数据接口时就会完成代理实现类的注入。


------------------------------------------------------------------------------------------------------------------------
基于Springboot2使用Rest访问MongoDB数据：
添加依赖：spring-boot-starter-data-rest
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-rest</artifactId>
    </dependency>

spring-boot-starter-data-rest会自动扫描添加@RepositoryRestResource注解的接口，自动将该接口映射为一系列可通过rest访问的请求路径
注解内需要提供两个参数，
    collectionResourceRel：该参数配置映射MongoDB内的Collection名称。
    path：该参数配置映射完成rest后访问的路径前缀。

访问http://localhost:8085/customer
{
    "_embedded": {
        "customer": [
            {
                "firstName": "名字",
                "lastName": "姓氏",
                "_links": {
                    "self": {
                        "href": "http://localhost:8085/customer/5bebb495fb62452c8c74df62"
                    },
                    "customer": {
                        "href": "http://localhost:8085/customer/5bebb495fb62452c8c74df62"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8085/customer{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:8085/profile/customer"
        }
    },
    "page": {
        "size": 20,
        "totalElements": 1,
        "totalPages": 1,
        "number": 0
    }
}
接口返回MongoDB.collection集合内的数据，还提供了分页信息，接下来可以通过id进行访问：http://localhost:8085/customer/5bebb495fb62452c8c74df62
{
    "firstName": "名字",
    "lastName": "姓氏",
    "_links": {
        "self": {
            "href": "http://localhost:8085/customer/5bebb495fb62452c8c74df62"
        },
        "customer": {
            "href": "http://localhost:8085/customer/5bebb495fb62452c8c74df62"
        }
    }
}

在CustomerRepository中添加自定义方法，可以调用接口：http://localhost:8085/customer/search/findByFirstName?firstName=名字
{
    "_embedded": {
        "customer": [
            {
                "firstName": "名字",
                "lastName": "姓氏",
                "_links": {
                    "self": {
                        "href": "http://localhost:8085/customer/5bebb495fb62452c8c74df62"
                    },
                    "customer": {
                        "href": "http://localhost:8085/customer/5bebb495fb62452c8c74df62"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8085/customer/search/findByFirstName?firstName=%E5%90%8D%E5%AD%97"
        }
    }
}

# 调用http://localhost:8085/profile/customer查询接口详情：
{
    "alps": {
        "version": "1.0",
        "descriptors": [
            {
                "id": "customer-representation",
                "href": "http://localhost:8085/profile/customer",
                "descriptors": [
                    {
                        "name": "firstName",
                        "type": "SEMANTIC"
                    },
                    {
                        "name": "lastName",
                        "type": "SEMANTIC"
                    }
                ]
            },
            {
                "id": "create-customer",
                "name": "customer",
                "type": "UNSAFE",
                "rt": "#customer-representation"
            },
            {
                "id": "get-customer",
                "name": "customer",
                "type": "SAFE",
                "rt": "#customer-representation",
                "descriptors": [
                    {
                        "name": "page",
                        "doc": {
                            "value": "The page to return.",
                            "format": "TEXT"
                        },
                        "type": "SEMANTIC"
                    },
                    {
                        "name": "size",
                        "doc": {
                            "value": "The size of the page to return.",
                            "format": "TEXT"
                        },
                        "type": "SEMANTIC"
                    },
                    {
                        "name": "sort",
                        "doc": {
                            "value": "The sorting criteria to use to calculate the content of the page.",
                            "format": "TEXT"
                        },
                        "type": "SEMANTIC"
                    }
                ]
            },
            {
                "id": "delete-customer",
                "name": "customer",
                "type": "IDEMPOTENT",
                "rt": "#customer-representation"
            },
            {
                "id": "patch-customer",
                "name": "customer",
                "type": "UNSAFE",
                "rt": "#customer-representation"
            },
            {
                "id": "update-customer",
                "name": "customer",
                "type": "IDEMPOTENT",
                "rt": "#customer-representation"
            },
            {
                "id": "get-customer",
                "name": "customer",
                "type": "SAFE",
                "rt": "#customer-representation"
            },
            {
                "name": "findByFirstName",
                "type": "SAFE",
                "descriptors": [
                    {
                        "name": "firstName",
                        "type": "SEMANTIC"
                    }
                ]
            }
        ]
    }
}