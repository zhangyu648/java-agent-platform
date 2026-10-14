# 本机环境快照（10/09 这一天的产出，实测于 2026-10-14）

> 这份文件的作用：**三天后你会忘记"当初为什么这么配"**。写下来 = 把排查过程存盘。
> 以后再看到「java 版本不对」「mvn 是 25」这类现象，先翻这里。
>
> 说明：本文件是**补做 10/09 这一天的内容**，实际操作日期是 2026-10-14，环境数据都是当天实测值。

## 一、硬件与系统

| 项 | 值 | 怎么查的 |
|---|---|---|
| 操作系统 | Windows 11, amd64 | `mvn -v` 的 `OS name` 行 |
| 物理内存 | **15.6 GB** | `[math]::Round((Get-CimInstance Win32_ComputerSystem).TotalPhysicalMemory/1GB,1)` |
| 当前账号是否管理员 | **否** | `([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)` |
| D 盘可用 | 67.8 GB | `(Get-PSDrive D).Free/1GB` |

**「非管理员」是今天最关键的一条事实**：`sysdm.cpl` 里能看不能改**系统变量**，所以 10-09 文件里"改系统变量 `JAVA_HOME`"的写法在本机走不通，改走用户级（见第三节）。

## 二、JDK

| 项 | 切换前 | 切换后 |
|---|---|---|
| `java -version` | 25.0.2 LTS（Oracle，2026-01-20 发布） | **21.0.12+101 LTS（Eclipse Temurin）** |
| `JAVA_HOME`（用户级） | 无 | **`D:\java21`** |
| `JAVA_HOME`（系统级） | `D:\java all\java` | `D:\java all\java`（**故意不动**，当回滚锚点） |
| `java` 命令来源 | `C:\Program Files\Common Files\Oracle\Java\javapath\java.exe` | **`D:\java21\bin\java.exe`**（用户 Path 优先） |
| JDK 25 的目录 | `D:\java all\java` | 原样保留，**没卸载** |

**装的是 ZIP 版不是 msi**：ZIP 版不需要管理员权限、不改注册表、不写系统变量，删目录即卸载。

- 安装包：`OpenJDK21U-jdk_x64_windows_hotspot_21.0.12.1_1.zip`（205,073,461 字节，来自清华 TUNA 的 Adoptium 镜像）
- 解压目标：`D:\java21`（解压后目录里应直接看到 `bin\java.exe`、`release`）

## 三、切换/回滚命令（都是用户级，随时可撤）

### 切到 21（当前状态，无需管理员）

```powershell
[Environment]::SetEnvironmentVariable('JAVA_HOME', 'D:\java21', 'User')
$userPath = [Environment]::GetEnvironmentVariable('Path','User')
if ($userPath -notlike '*D:\java21\bin*') {
    [Environment]::SetEnvironmentVariable('Path', "D:\java21\bin;$userPath", 'User')
}
# 关键：改完必须开新终端（环境变量是进程启动时的快照）
```

### 回滚到 25

```powershell
[Environment]::SetEnvironmentVariable('JAVA_HOME', $null, 'User')          # 删掉用户级，回落到系统级
$userPath = [Environment]::GetEnvironmentVariable('Path','User')
$newPath = ($userPath -split ';' | Where-Object { $_ -ne 'D:\java21\bin' -and $_ -ne '' }) -join ';'
[Environment]::SetEnvironmentVariable('Path', $newPath, 'User')
# 开新终端 -> java -version 回到 25.0.2
```

要点：**`D:\java21` 目录不用删**。两份 JDK 共存，谁在 PATH 前面谁赢。

### 只影响当前窗口的临时切换（最安全的对照实验手段）

```powershell
# 临时用 21
$env:JAVA_HOME = 'D:\java21'
$env:Path = "D:\java21\bin;$env:Path"

# 临时用 25
$env:JAVA_HOME = 'D:\java all\java'
$env:Path = "D:\java all\java\bin;$env:Path"
```

关掉窗口自动失效，零风险。**「某个工具在 21 上跑不起来」时，用这个 2 分钟切回 25 做对照。**

## 四、Maven

| 项 | 值 |
|---|---|
| 版本 | Apache Maven 3.9.12 |
| Maven home | `D:\Maven tool\apache-maven-3.9.12-bin\apache-maven-3.9.12` |
| `mvn -v` 的 Java version | 跟随 `JAVA_HOME`（切 21 后必须变成 21 / Eclipse Adoptium） |
| platform encoding | UTF-8（好事，中文不乱码） |
| Wrapper | `D:\learn\java-agent-platform\project-a\mvnw` / `mvnw.cmd` / `.mvn\wrapper\maven-wrapper.properties` |

## 五、Git

| 项 | 值 |
|---|---|
| 仓库 | `D:\learn\java-agent-platform` |
| `user.name` | `86199` |
| `user.email` | `qwe19942669865@163.com`（GitHub 上必须是验证过的同一个邮箱，否则贡献图挂不上） |
| 提交信息写法 | `"..." \| Out-File -Encoding utf8 .git\msg.txt` → `git commit -F .git/msg.txt`（避免 `-m` 的中文乱码） |

## 六、Docker / WSL2

| 项 | 值 |
|---|---|
| Docker Desktop | `Docker version 29.6.1, build 8900f1d` |
| Compose | `Docker Compose version v5.3.0`（**插件版，用空格 `docker compose`**） |
| 安装位置 | `C:\Users\86199\AppData\Local\Programs\DockerDesktop\Docker Desktop.exe`（**用户级安装**） |
| daemon 状态 | 已启动，`docker info` 有 `Server:` 段；`OSType: linux`，Kernel `6.18.33.2-microsoft-standard-WSL2` |
| WSL | 默认版本 **2**；分发版 `Ubuntu`(Stopped) / `docker-desktop`(Running)，VERSION 都是 2 |
| 内存上限 | `C:\Users\86199\.wslconfig` → `memory=6GB processors=4 swap=2GB` |
| 上限生效验证 | 改前 `docker info`：CPUs=20 / Mem=8.1 GiB；重启后：**CPUs=4 / Mem=5.79 GiB**（≈6GB，按 GiB 计） |

**为什么是 `.wslconfig`（用户目录）而不是项目目录**：它管的是整台机器的 WSL2 虚拟机，跟项目无关。改完要 `wsl --shutdown` + 重启 Docker Desktop 才生效。

## 七、四个数据库服务（`D:\learn\docker-compose.yml`，10/09 只读预检）

| 服务 | 容器名 | 镜像 | 宿主机端口 | 账号 / 密码 | 库名 |
|---|---|---|---|---|---|
| mysql | `dev-mysql` | `mysql:8.0` | **3307** → 3306 | root / root123456 | `it_service_desk` |
| redis | `dev-redis` | `redis:7-alpine` | 6379 → 6379 | 无密码 | — |
| postgres | `dev-pg` | `pgvector/pgvector:pg17` | 5432 → 5432 | postgres / root123456 | `agentdb` |
| rabbitmq | `dev-rabbitmq` | `rabbitmq:3.13-management` | 5672 / **15672** | admin / admin123 | — |

命名卷：`mysql-data` / `redis-data` / `pg-data` / `rabbitmq-data`。

**MySQL 映射 3307 的原因**：本机 3306 已被另一个 MySQL 占用（PID 8184），直接映射 3306 会报 `port is already allocated`。
