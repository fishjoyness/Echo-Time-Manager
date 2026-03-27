📄 Echo | 个人时间管理与行为诊断系统
<p align="center">
<img src="https://img.shields.io/badge/Spring%20Boot-3.0-green.svg" alt="Spring Boot">
<img src="https://img.shields.io/badge/Vue.js-3.0-blue.svg" alt="Vue">
<img src="https://img.shields.io/badge/DeepSeek-V3-orange.svg" alt="DeepSeek">
<img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License">
</p>

🌟 这是一个什么神仙项目？
你是那个每天忙忙碌碌，晚上却想不起来今天到底干了啥的人吗？

你是不是常常立了 flag 要好好学习，结果转头就在打游戏和刷视频里度过了一天，事后又后悔不已？

ECHO（Echo 个人行为环境深度诊断）就是为你准备的。这不仅仅是一个“打卡”软件，它是一个带有灵魂的 AI 教练。通过对每日活动的精准记录，ECHO 调用 DeepSeek-V3 大模型，给你的时间分配逻辑来一次犀利、客观、拒绝套话的“灵魂级体检”。

核心法则：做得好，大力赞赏；做得差，一针见血开骂！

🛠️ 技术架构
系统采用现代化的全栈逻辑，实现了前端交互、后端业务与 AI 推理的完全解耦：

🎨 前端 (The Face)
核心框架：基于 Vue.js 3.0 构建。

数据看板：集成 ECharts 5.5 实现 180 天精力热力大屏。

视觉体验：颜色深度随投入时长动态变化，一眼看穿你是“卷王”还是“摆烂”。

⚙️ 后端 (The Brain)
核心框架：基于 Spring Boot 3.0。

安全防护：集成 Spring Security + JWT，确保数据绝不“裸奔”。

🧠 模型层 (The Soul)
独立分类服务：通过 Python Flask 部署轻量级 NLP 模型，自动识别活动类别。

深度诊断引擎：集成 DeepSeek-V3 API，生成不少于 1000 字的深度行为体检报告。

💾 存储层 (The Memory)
数据库：MySQL 8.0 存储结构化记录，支持长周期趋势建模。

🚀 核心功能
📍 行为轨迹追踪：精准记录每日事项与时长，支持跨天环比分析。

📊 180天热力图：直观展示过去半年的努力轨迹，颜色深度实时反馈。

👺 AI 教练诊断：根据核心学习时间占比，AI 会变换口吻进行“降维打击”：

占比 > 80%：高度赞赏 + 严厉的压力预警。

60% - 80%：极度认可的完美状态。

30% - 50%：严厉敲打，警惕堕落。

占比 < 30%：极其严厉，痛骂警醒，绝不客气！

🔒 安全防线：全链路 JWT 鉴权，保护你的个人隐私数据。

📈 演示快照 (看这大气排面)
01. 180天热力大屏（大气磅礴版）
02. 今日活动录入面板
03. 数字化工作台大标题
📦 快速开始 (3分钟部署指南)
1. 数据库准备
安装 MySQL 8.0+。

导入项目根目录下的 db_backup.sql 文件以初始化表结构。

2. 后端启动
使用 IDEA 打开 demo 文件夹。

在 src/main/resources/application.properties 中配置你的数据库连接与 DeepSeek API Key：

Properties
spring.datasource.password=你的数据库密码
deepseek.api.key=sk-你的API密钥
运行 DemoApplication.java。

3. 前端运行
无需安装！ 直接使用浏览器打开项目根目录下的 index.html。

注册账号并登录，开始你的第一次“行为诊断”。

🤝 贡献与支持
如果你发现了 Bug，或者有更“毒舌”的诊断建议，欢迎发起 PR。
如果这个项目帮到了你，请给一个 Star ⭐️，这是对开发者（熬夜改 Bug 的我）最大的鼓励！

🛡️ 隐私与安全
我们通过 .gitignore 严格抽离了所有敏感密钥。请确保不要手动删除忽略规则，保护你的 DeepSeek 额度不被黑客盗刷。
