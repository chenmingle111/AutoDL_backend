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

1.设置websocket实时更新类 + sync5min更新
2.后续完善创建部署时，以下两个请求参数专门的限制，现在默认都必填
replica_num	| ReplicaSet、Job必填
parallelism_num	| Job必填