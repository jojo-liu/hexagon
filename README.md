# hexagon
construct a consult platform which provides people with business advice

目前需要实现的核心需求：
1. 实现用户发布需求
2. 向用户显示带有相关标签的专家信息
3. 实现电话注册功能
4. 实现微信号注册功能
5. 订单服务功能
6. 实现用户支付功能，接入点微信或者支付宝支付接口

项目框架简介：
本项目的核心是Spring Boot框架，通过集成Mybatis来实现数据库的操作，包括CRUD和其他复杂的操作
druid是数据库连接池，已经配置完毕，可以直接使用(http://git.oschina.net/free/Mapper)；
log4j2是用来处理日志的部件，也已经配置完毕，可以直接使用；

本项目前后端分离，前端由徐文琪同志负责，使用vue框架结合AJAX来实现前后端的数据通信。

由于我们使用maven来构建项目，pom.xml是项目中添加的各种依赖，resource/application.yml是项目的各种配置参数。
main/java/com.jojoliu.hexagon/+++
model:存放各种实体类，和数据库中的表示一一对应的关系
mapper：存放各个实体类所对应的mapper，/resources/mapper下对应的xml文件，里面可以实现sql语句 
controller：存放各个实体类所对应的controller，主要负责页面的映射和与实体类相关的函数接口
service：编写和实体类相关的业务函数 
util：一些工具类
common：一些公共类，目前有page用来实现分页功能，和Response共同使用，用来分页显示结果（包括用户查询的“专家”结果，或者专家查询的”用户需求“结果）
