# Java Agent Platform

用 Java 构建的企业级后端平台，包含两个递进的系统。

## 系统

| 目录 | 系统 | 技术栈 |
|---|---|---|
| `project-a/` | **IT 服务台 / 工单系统**<br>工单全生命周期、状态机、SLA 超时升级、权限模型 | Spring Boot 4.0 / MySQL 8.0 / Redis 7 / RabbitMQ |
| `project-b/` | **企业知识库 Agent 平台**<br>构建在 project-a 之上：知识检索 + 工具调用 + 评测闭环 | LangChain4j / PostgreSQL + pgvector / MCP |

> project-b 通过 project-a 的 REST 接口读取真实工单数据，不是独立造的表。

## 目录

```
java-agent-platform/
├── project-a/          工单系统
├── project-b/          Agent 平台
├── docs/               架构 / 接口 / 压测
└── notes/              技术笔记
```

## 本地环境

```bash
cd ..                  # 回到上级目录（含 docker-compose.yml）
docker compose up -d
docker compose ps
```

| 服务 | 端口 | 凭据 |
|---|---|---|
| MySQL | **3307** | root / root123456，库 `it_service_desk` |
| Redis | 6379 | 无密码 |
| PostgreSQL | 5432 | postgres / root123456，库 `agentdb` |
| RabbitMQ | 5672 / 15672 | admin / admin123 |

## 开发规范

见 [CONTRIBUTING.md](CONTRIBUTING.md)。
