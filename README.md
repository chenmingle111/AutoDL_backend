# AutoDL_backend
AutoDL

# 项目结构
.
├── AutoDL_backend/src/main/java/com.autodl_backend/
│   ├── annotation/RequiredParams.java //检查参数完整性？
│   ├── autodl/
│   │   ├── client/AutoDLClient.java //专门访问AutoDL的Client
│   │   ├── config/AutoDLProperties.java //AutoDL连接访问的配置信息
│   │   ├── dto/
│   │   │   ├── AutoDLReq.java //AutoDL连接访问的请求体（注入Client）
│   │   │   ├── AutoDLRep.java //AutoDL连接访问的响应体（注入Client）
│   │   │   ├── AutoDLImageReq.java //请求镜像列表的参数请求体
│   │   │   ├── AutoDLImageRep.java //响应镜像列表的参数响应体
│   │   │   ├── AutoDLXXXReq.java
│   │   │   ├── AutoDLXXXReP.java
│   │   │   └── ...
│   │   └── task/AutoDLStateSyncTack //定时线程检测AutoDL状态，同步本地数据库
│   ├── local/
│   │   ├── config/ //拦截器等配置
│   │   ├── contolller/ //控制层
│   │   ├── service/ //服务层
│   │   ├── mapper/
│   │   └── pojo/
│   │       ├── entity/ //实体类（有表的）
│   │       ├── view/ //视图类
│   │       ├── response //响应类
│   │       └── page //分页器
│   └── util/
└── Application

1.实时更新功能：设置websocket实时更新类 + sync5min更新
2.请求参数限制：后续完善创建部署时，以下两个请求参数专门的限制，现在默认都必填
    replica_num	| ReplicaSet、Job必填
    parallelism_num	| Job必填
3.登录和注册功能：
    token：
        令牌的生成，验证逻辑当前不佳，建议修改为JWT令牌(需要时间戳，用户id，token一起存入用户数据库)
    细节问题：
        当前所有用户都用的时学长的token访问autodl，但autodl并未提供创建token的api
4.日志输出不完全，有的有日志，有的没有，要统一，如何规范？
5.回显(getById)相关方法
6.将对象转换为JSON字符串序列化流给前端：
    目前采取AOP直接控制并且返回响应：response.getWriter().write()
    存在两种自动化返回的方式：Spring MVC的@RestController和ResponseEntity，或@ControllerAdvice和ResponseBodyAdvice