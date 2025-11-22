# AutoDL_backend
AutoDL

# 项目结构
以下是项目结构说明以及向 AutoDL 发送请求的规范代码流程。

# 1. 项目结构说明
   当前项目采用经典的 Spring Boot 分层架构，并集成了 MyBatis-Plus 和 AutoDL 外部接口。

src/main/java/com/autodl_backend

controller: 控制层。负责接收 HTTP 请求，解析参数，调用 Service 层，并返回统一格式的响应（如 Result）。

service: 业务逻辑层。包含接口 (Service) 和实现类 (impl)。负责核心业务处理，如数据组装、调用外部 API (integration)、调用持久层 (mapper) 等。

mapper: 持久层。使用 MyBatis-Plus 与数据库交互。

pojo: 实体类与数据对象。
通常包含数据库实体（对应表结构）。
也可以包含部分 DTO (Data Transfer Object) 或 VO (View Object)。

integration: 外部集成层（核心）。

AutoDLClient.java: 封装了与 AutoDL API 交互的通用 HTTP 客户端，处理 Token 注入、请求发送和统一错误处理。

DTO: 存放 AutoDL 接口专用的请求 (Req) 和响应数据 (Data) 对象。

common: 公共模块。包含全局异常 (exception)、通用工具类 (utils)、统一响应结构等。

config: 配置类。如 Spring 配置、MyBatis 配置等。

autodl: (可能为旧结构或特定模块) 包含部分 AutoDL 相关的配置或任务。

# 2. 向 AutoDL 发送请求的规范代码流程

接下来的开发中，向 AutoDL 发送请求应遵循 "定义 DTO -> Service 调用 Client -> 处理响应" 的标准流程。

第一步：定义 DTO (在 integration/DTO 包下)
    你需要定义两个类：一个是请求参数，一个是响应中的 Data 部分。
    
    请求 DTO (AutoDLXxxReq):
    
    响应 Data DTO (AutoDLXxxData):
    注意：这里只需要定义 data 字段内部的结构，外层的 code, msg, data 由 AutoDLResp<T> 统一处理。


第二步：在 Service 中调用 (使用 AutoDLClient)
    在你的 Service 实现类中注入 AutoDLClient，并使用 postForData (或 get) 方法。


核心优势
自动鉴权: 不需要每次手动 setHeader("Authorization", token)。
统一报错: 如果 AutoDL 返回错误码，AutoDLClient 会自动抛出 AutoDLException，由全局异常处理器捕获。
类型安全: 直接拿到定义好的 Data 对象，无需手动解析 JSON。