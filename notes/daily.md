# 每日日志

> 每天 3 行：**学了 / 卡了 / 明天**。一年后回看，这三行比任何笔记都好使。

## 10/09（周五）

- **学了**：`JAVA_HOME` 管工具链、`PATH` 管命令行，两个都要改才算真切换；Docker 是 C/S 架构，CLI 通过命名管道（npipe）连 daemon，"装了"不等于"能用"；compose 的 `"3307:3306"` 是「宿主机:容器」。
- **卡了**：`docker compose ps` 报 `npipe ... The system cannot find the file specified` —— 根因是 Docker Desktop 没启动，管道文件根本不存在，不是命令写错。
- **卡了**：本机账号不是管理员，`sysdm.cpl` 里改**系统变量** `JAVA_HOME` 走不通 —— 改用 ZIP 版 Temurin 21 + **用户级**变量，等价且更好回滚。
- **明天**：`cd D:\learn; docker compose up -d` 四个库一起起 → Spring Boot 骨架改连 3307 → 算法 1 / 26 / 27。

## 模板（每天照抄这一块）

```markdown
## MM/DD（周X）
- 学了：
- 卡了：
- 明天：
```
