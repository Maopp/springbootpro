# springbootpro
spring boot pro


静态文件放到/resources/static目录下，可以直接访问，默认不会被拦截；添加资源映射，将/static文件下内容映射到/catpp/resources

LogBack读取配置文件的步骤
（1）尝试classpath下查找文件logback-test.xml
（2）如果文件不存在，尝试查找logback.xml
（3）如果两个文件都不存在，LogBack用BasicConfiguration自动对自己进行最小化配置