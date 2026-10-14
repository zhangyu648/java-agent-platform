# 每日日志

> 每天 3 行：**学了 / 卡了 / 明天**。一年后回看，这三行比任何笔记都好使。

## 10/09（周五）

- **学了**：`JAVA_HOME` 管工具链、`PATH` 管命令行，两个都要改才算真切换；Docker 是 C/S 架构，CLI 通过命名管道（npipe）连 daemon，"装了"不等于"能用"；compose 的 `"3307:3306"` 是「宿主机:容器」。
- **卡了**：`docker compose ps` 报 `npipe ... The system cannot find the file specified` —— 根因是 Docker Desktop 没启动，管道文件根本不存在，不是命令写错。
- **卡了**：本机账号不是管理员，`sysdm.cpl` 里改**系统变量** `JAVA_HOME` 走不通 —— 改用 ZIP 版 Temurin 21 + **用户级**变量，等价且更好回滚。
- **明天**：`cd D:\learn; docker compose up -d` 四个库一起起 → Spring Boot 骨架改连 3307 → 算法 1 / 26 / 27。

## 10/10（周六）

- **学了**：
  - Docker Compose 端口映射：宿主机 `3307` → 容器内 `3306`，两个世界两套端口号。
  - Spring Boot 4.0 的 starter 更名：`spring-boot-starter-web` → **`spring-boot-starter-webmvc`**。
  - 快慢指针套路：`nums[slow] = nums[fast]; slow++`，慢指针既是"下一个待写位置"也是"已保留个数"。
  - Actuator 的 `db` / `redis` 健康状态默认**不显示详情**，要 `management.endpoint.health.show-details: always` 才看得到 components。
- **卡了**：
  - `docker exec -it` 里的 `-it` 在脚本/管道里会报错（要 TTY 交互），自动化时去掉 `-it` 只留 `docker exec`。
  - PowerShell 传 native 命令的参数时，`-Dxxx.yyy=value` 里的**点号会被吃掉**（`-Dloader.main=...` 变成 `java.lang.ClassNotFoundException: /main=...`），要用引号包住整个参数。
  - 直接 `java -cp` 启动比 `mvn spring-boot:run` 慢（3.0~3.2s vs 2.1~2.3s）——因为少了 Maven 预热，JIT 冷启动更明显。
- **明天**：Spring Boot 依赖注入 + 项目 A 模块划分。

### 启动耗时基线（W11 项目 B 做虚拟线程对比要用这个数）

| 第几次 | 启动方式 | `Started ProjectAApplication in` | 备注 |
|---|---|---|---|
| 1 | `.\mvnw.cmd spring-boot:run` | **2.336 s** | 首次，进程总时长 2.636s |
| 2 | `.\mvnw.cmd spring-boot:run` | **2.076 s** | 最快的一次 |
| 3 | `.\mvnw.cmd spring-boot:run` | **2.070 s** | 进程总时长 2.358s |

对比用（换了启动方式，别和上面三个混着比）：直接 `java -cp target\classes;<依赖>` 启动是 **3.036 s / 3.246 s**，比 Maven 方式慢 1 秒左右。

**运行环境**：JDK **21.0.12.1**（Eclipse Temurin）、Spring Boot **4.0.8**、Spring Framework 7.0.9、Tomcat 11.0.24、端口 8080。
**数据库连通性**：连上了（`/actuator/health` 里 `db: UP`、`redis: UP`），不是"连不上所以慢"。

## 模板（每天照抄这一块）

```markdown
## MM/DD（周X）
- 学了：
- 卡了：
- 明天：
```
