# 问题池

> 规则：当场不懂的先记下来，**周末集中查**，别当场陷进去。
> 判断标准：这个问题值不值得占「周末 2 小时」，还是 5 分钟能搜明白。能搜明白的当场解决，不往这里放。

| 日期 | 问题 | 状态 | 答案 |
|---|---|---|---|
| 10/10 | Spring Boot 4.0 为什么把虚拟线程设为默认？ | 待查 | |
| 10/10 | `@RestController` 和 `@Controller` 的区别？（为什么返回 JSON 而不是视图名） | 待查 | |
| 10/10 | 为什么本地要用 Docker 起中间件，不直接装？ | 待查 | |
| 10/10 | 宿主机端口 3307 和容器内 3306 是什么关系？ | 待查 | |
| 10/10 | `mvnw` 和本机的 `mvn` 有什么区别？ | 待查 | |
| 10/09 | pgvector 的索引 HNSW 和 IVFFlat 怎么选？（W11 项目B 要用） | 待查 | |
| 10/09 | `maven.compiler.release` 和 `source`/`target` 的差别，为什么前者更严格？ | 待查 | |
| 10/09 | MySQL 的 `utf8` 只有 3 字节，为什么官方不直接把 `utf8` 改成 4 字节？ | 待查 | |
| 10/09 | Docker Desktop 的 WSL2 发行版 `docker-desktop` 和 `docker-desktop-data` 各自存什么？ | 待查 | |
| 10/09 | 命名卷的数据在 Windows 上的物理路径到底在哪（`\\wsl$\...`？） | 待查 | |

## 已当场解决（不占周末）

| 日期 | 问题 | 答案（一句话） |
|---|---|---|
| 10/09 | `docker compose ps` 报 npipe 找不到文件是什么？ | Docker Desktop 没启动，命名管道不存在；启动并等鲸鱼变绿即可 |
| 10/09 | 为什么 `docker --version` 有输出但 `docker info` 报错？ | `--version` 只读本地文件不连 daemon；`info` 必须连 Server |
| 10/09 | 装完 JDK 21 为什么 `java -version` 还是 25？ | PATH 顺序：Oracle javapath 排在前面，`java` 解析到它 |
| 10/09 | 非管理员账号怎么切 JDK？ | 用 ZIP 版 + **用户级** `JAVA_HOME`/`Path`，用户变量优先于系统变量 |
| 10/09 | 两个 JDK 会不会冲突？ | 不会。JDK = 一个目录 + 一个变量 + PATH 一行，互相不知道对方存在 |
