# 学习进度台账

> 规则：每天收工前填一行。**算法题号**只记当天真正刷完的；**完成度**打 ✅ / ⚠️ / ❌。
> 欠账不擦掉，往后看才知道 W1 补了什么。

| 日期 | 算法题号 | 主线任务 | 实际时长 | 完成度 |
|---|---|---|---|---|
| 10/09 | — | 环境检查 + JDK21 + Docker 就位（今天不刷算法、不起数据库） | 4.5h | ✅ |
| 10/10 | 1,26,27 | 起库 + Spring Boot 骨架连库 | | |
| 10/11 | 283,977 | 缓冲补账 + W1 复盘 | | |

## 10/09 当天实际完成的事（对照打卡表）

| 块 | 内容 | 结果 |
|---|---|---|
| 一 | JDK 四条命令查现状 | ✅ 查清：`java` 来自 Oracle javapath，`JAVA_HOME` 指向 `D:\java all\java`（25.0.2） |
| 一 | 装 Temurin 21 | ✅ 21.0.12+101 LTS，**ZIP 版解压到 `D:\java21`**（本机账号非管理员，改用用户级变量，见 `env.md`） |
| 一 | 回滚步骤演练 | ✅ 回滚路径写进 `env.md`，实测可切回 25 |
| 二 | Maven / mvnw | ✅ 3.9.12；`Java version` 跟随 `JAVA_HOME` 这点已验证 |
| 三 | Git 身份 | ✅ `user.name=86199`、`user.email=qwe19942669865@163.com` |
| 四 | Docker Desktop daemon | ✅ 启动后 `docker info` 有 `Server: 29.6.1`，`OSType: linux`；`docker compose ps` 从报 npipe 变成空表 |
| 四 | WSL2 前置检查 | ✅ `wsl -l -v` 的 VERSION 都是 2（Ubuntu / docker-desktop） |
| 四 | 内存检查 + 限住 | ✅ 物理内存 15.6 GB；`.wslconfig` 写 `memory=6GB / processors=4 / swap=2GB` 并重启，实测 Docker 拿到 4 核 / 5.79 GiB |
| 五 | 预检 `docker-compose.yml` | ✅ 四服务、端口映射、四个命名卷全部核对（今天只读） |
| 六 | 建台账 + 提交 | ✅ `notes/` 四文件落地 |

## 待办（滚动往下带）

- [ ] 10/10 第一件事：`cd D:\learn; docker compose up -d`（内存足，四个一起起）
- [ ] 10/10 起算法：1（两数之和）、26（删除有序数组重复项）、27（移除元素）
- [ ] 10/18 复盘时评估：是否把系统变量 `JAVA_HOME` 也切成 21（需要管理员）
