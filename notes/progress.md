# 学习进度台账

> 规则：每天收工前填一行。**算法题号**只记当天真正刷完的；**完成度**打 ✅ / ⚠️ / ❌。
> 欠账不擦掉，往后看才知道 W1 补了什么。

| 日期 | 算法题号 | 主线任务 | 实际时长 | 完成度 |
|---|---|---|---|---|
| 10/09 | — | 环境检查 + JDK21 + Docker 就位（今天不刷算法、不起数据库） | 4.5h | ✅（补做于 2026-10-14） |
| 10/10 | 1,26,27 | 环境 + 骨架 | 6.5h | ✅（补做于 2026-10-14） |
| 10/11 | 283,977 | 缓冲补账 + W1 复盘 | | |

## 统计

| 项 | 值 |
|---|---|
| 累计天数 | 2 天（10/09 起） |
| 累计算法题 | 3 题（1 / 26 / 27） |
| 累计时长 | 7.2h |
| 平均每天 | 3.6h |

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

## 2026-10-14 补记：四个库已拉起并验证连通

`cd D:\learn; docker compose up -d` → 四容器 `Up (healthy)`，宿主机侧连通性实测：

| 服务 | 版本 | 从宿主机验证 | 结果 |
|---|---|---|---|
| dev-mysql | MySQL 8.0.46 | `mysql --host=127.0.0.1 --port=3307` + TCP 3307 | ✅ 库 `it_service_desk` 在，`utf8mb4 / utf8mb4_unicode_ci / +08:00` |
| dev-redis | Redis 7.4.11 | TCP 6379 | ✅ `PONG` |
| dev-pg | PostgreSQL 17.11 | TCP 5432 | ✅ `agentdb` 可连，扩展 `vector 0.8.7`（pgvector 在） |
| dev-rabbitmq | RabbitMQ 3.13 | TCP 5672/15672 + `HTTP 200` | ✅ 管理台 `http://localhost:15672` 账号 admin/admin123 可登录 |

**踩坑记录**：容器之前是 `Exited (137)` —— 不是崩溃，是**10-12 Docker 引擎关闭时一起停的**（四个同一秒停止，`OOMKilled=false`）。直接 `up -d` 就恢复了，数据卷 `learn_*` 完好无损。

**内存实测**：四容器合计约 **608 MiB**（mysql 387 + rabbitmq 166 + pg 47 + redis 7），`.wslconfig` 上限 5.79 GiB，余量充足。
**重启行为**：`restart: unless-stopped` —— 重开 Docker Desktop 后它们会自己回来。

## 2026-10-14 补记：10/10（启动日）完成情况

七块逐块核对（详见 `daily.md` 与 `algorithm.md`）：

| 块 | 内容 | 结果 |
|---|---|---|
| 1 | 环境检查 | ✅ JDK 21.0.12.1 / Maven 3.9.12 / Docker 29.6.1 + Compose v5.3.0 / 内存 15.6 GB |
| 2 | 起数据库 | ✅ 四容器 `Up (healthy)`；MySQL 8.0.46、Redis 7.4.11、PostgreSQL 17.11 + **pgvector 0.8.7**、RabbitMQ 管理台 200 |
| 3 | 建仓库核对 | ✅ 7 条提交；`.gitignore` 生效（`git check-ignore` 命中 `project-a/.gitignore:2:target/`） |
| 4 | 骨架校验 | ✅ 启动 **2.336 / 2.076 / 2.070 s**；`/api/health` 四字段；`/actuator/health` 顶层 UP（db UP、redis UP） |
| 5 | 算法 1/26/27 | ✅ 本地 17 项断言全过；洞察 + 2 个模板写进 `algorithm.md`；**LeetCode 在线 AC 待本人提交确认** |
| 6 | 建台账 | ✅ `algorithm.md` 新增（第 4 个文件），`progress.md`/`daily.md`/`questions.md` 补全 |
| 7 | 收尾提交 | ✅ 已提交推送，`git status --short` 无输出 |

**新增的一处配置改动**：`application.yml` 加了 `management.endpoint.health.show-details: always`，否则 `/actuator/health` 只返回 `{"status":"UP"}`，看不到 `db`/`redis` 组件状态。**生产环境不要开**（会泄露依赖组件内部信息）。

**两处与日卡的偏差（记录一下，别以后当成自己写错了）**：

| 项 | 日卡说的 | 实际 | 说明 |
|---|---|---|---|
| `day-cards/` 目录 | 仓库根目录应有 | **不存在** | 日卡文件现在放在 `D:\learn\04-每天照着做\`，仓库里没有这个目录。不影响任何验收项，仅备注 |
| 算法 AC | "三题全 AC" | **本地 17 项断言全过；LeetCode 在线 AC 需本人提交** | 在线判题只能由你本人账号提交确认，本地已验证解法正确 |
| Jackson 版本 | 知道 Boot 4 用 `tools.jackson` | ✅ 实测本地仓库是 `jackson-databind-3.1.5.jar`（Jackson 3） | 包名已迁到 `tools.jackson`，不再是 `com.fasterxml.jackson` |
