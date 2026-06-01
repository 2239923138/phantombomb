# Phantom Bomb 💣

一个 Minecraft NeoForge 模组，让幻翼不再普通攻击，而是**飞向玩家后自爆**，爆炸会产生火焰！

## 需求

- Minecraft **1.21.1**
- NeoForge **21.1.169**（或兼容版本）
- Java **21+**

## 安装

1. 安装 [NeoForge](https://neoforged.net/) 1.21.1
2. 从 [Releases](../../releases) 下载 `phantombomb-1.0.jar`
3. 放入 Minecraft `mods` 文件夹
4. 启动游戏

## 功能

- 🔥 幻翼飞向玩家 → 自爆 → 产生火焰
- 🎯 直线冲锋，像导弹一样追踪
- 🧨 TNT 级别爆炸特效
- ⚙️ 可自由调节所有参数

## 命令

所有命令需**管理员权限**（op 2 级），使用 `/phantom` 根命令：

| 命令 | 说明 | 默认值 | 范围 |
|------|------|--------|------|
| `/phantom power <值>` | 爆炸强度 | 4.0 | 0.1 ~ 100 |
| `/phantom fire true/false` | 是否产生火焰 | true | - |
| `/phantom speed <值>` | 幻翼冲锋速度 | 1.2 | 0.1 ~ 5.0 |
| `/phantom range <值>` | 搜索玩家距离（格） | 32 | 1 ~ 64 |
| `/phantom explode_range <值>` | 触发爆炸距离（格） | 3.5 | 1 ~ 16 |
| `/phantom status` | 查看当前配置 | - | - |

> **提示**：把 `power` 调到 10+，整片地都会被炸飞 🔥

## 行为机制

```
[幻翼生成] → [在32格内检测到玩家] → [锁定目标，高速冲锋]
                                           ↓
                                    [距离<3.5格] → [TNT爆炸 + 火焰]
                                           ↓
                                     [幻翼消失]
```

- 幻翼不会攻击创造模式或旁观模式的玩家
- 爆炸会破坏方块并点燃周围（跟 TNT 一样）
- 爆炸后幻翼被移除，不会反复爆炸

## 构建

```bash
# 克隆仓库
git clone https://github.com/你的用户名/phantombomb.git
cd phantombomb

# 编译（首次会下载依赖，需要网络）
./gradlew build

# 输出: build/libs/phantombomb-1.0.jar
```

## 项目结构

```
phantombomb/
├── build.gradle              # Gradle 构建配置
├── settings.gradle           # 项目名称
├── gradle.properties         # JVM 参数
├── gradlew / gradlew.bat     # Gradle Wrapper
└── src/main/
    ├── java/com/example/phantombomb/
    │   ├── PhantomBombMod.java    # 模组主入口
    │   ├── PhantomEvents.java     # 幻翼行为逻辑
    │   ├── PhantomCommands.java   # 命令注册
    │   └── ModConfig.java         # 运行时配置
    └── resources/
        ├── META-INF/neoforge.mods.toml  # 模组元数据
        └── pack.mcmeta                  # 资源包信息
```

## 许可

MIT
