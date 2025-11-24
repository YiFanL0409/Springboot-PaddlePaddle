# Springboot-PaddlePaddle
📖 项目简介
这是一个基于SpringBoot后端框架和Bootstrap前端框架构建的智能语言学习系统，集成了百度飞桨(PaddlePaddle)平台的OCR文字识别等技术（开发者可根据API灵活添加功能），为语言学习者提供全方位的学习支持。

核心特色：通过AI技术赋能传统语言学习，实现拍照取词、智能练习、个性化学习路径等功能，显著提升学习效率。

✨ 主要功能
👤 用户功能模块
📸 拍照取词 - 基于PaddlePaddle OCR技术的通用场景文字识别

📚 单词学习 - 多等级词汇学习、生词本管理、单词测试

👂 听力训练 - 分级听力材料、倍速调节、原文对照

📖 阅读学习 - 精选英文段落、中英对照阅读

📊 学习统计 - 可视化学习进度和成果展示

📝 题目练习 - 词汇测试、错题本自动记录

👨‍💼 管理员功能模块
用户管理 - 用户信息维护与权限管理

资源管理 - 单词、题目、书籍、听力材料管理

等级管理 - 学习资源难度分级

公告管理 - 系统公告发布与维护

🛠️ 技术栈
后端技术
Spring Boot - 后端核心框架

MyBatis - 持久层框架

MySQL - 数据库

百度飞桨(PaddlePaddle) - AI能力平台

前端技术
Bootstrap - 响应式UI框架

jQuery - JavaScript库

Thymeleaf - 模板引擎

开发工具
IntelliJ IDEA - 开发环境

Navicat - 数据库管理

Postman - API测试

🏗️ 系统架构
text
前端展示层 (Bootstrap + jQuery)
        ↓
   控制层 (Spring MVC)
        ↓
  业务逻辑层 (Spring Boot)
        ↓
  数据访问层 (MyBatis)
        ↓
  数据库 (MySQL)
        ↓
AI服务层 (PaddlePaddle OCR)
🚀 快速开始
环境要求
JDK 8+

MySQL 5.7+

Maven 3.6+

安装步骤
克隆项目

bash
git clone [项目地址]
数据库配置

sql
# 创建数据库
CREATE DATABASE language_learning;
# 导入数据库脚本
source schema.sql
配置文件修改

yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/language_learning
    username: your_username
    password: your_password

# PaddlePaddle配置
paddle:
  app-id: your_app_id
  api-key: your_api_key
  secret-key: your_secret_key
运行项目

bash
mvn spring-boot:run
访问系统

text
http://localhost:8080
📁 项目结构
text
src/
├── main/
│   ├── java/com/example/
│   │   ├── controller/     # 控制器层
│   │   ├── service/        # 业务逻辑层
│   │   ├── mapper/         # 数据访问层
│   │   ├── pojo/           # 实体类
│   │   └── util/           # 工具类
│   └── resources/
│       ├── static/         # 静态资源
│       └── templates/      # 页面模板
└── test/                   # 测试代码
🎯 核心特性
🤖 AI赋能学习
集成PaddlePaddle OCR技术，实现高精度文字识别

支持通用场景下的图片文字提取

智能化的学习资源推荐

📱 用户体验
响应式设计，支持多设备访问

直观的操作界面，降低学习成本

个性化的学习路径规划

🔧 管理便捷
完善的后台管理系统

丰富的学习资源维护功能

详细的用户学习数据分析
