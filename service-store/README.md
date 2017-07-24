Korprulu
==========

## 模板使用指南
#### 依赖
- python3.6
- abathur

#### 安装依赖

- python3

```
brew install python3
```

- abathur

```
pip3 install abathur
```

#### 使用模版
1. 使用abathur关联模板

```
abathur add service template-service
```

2. 根据模板创建项目

```
abathur build -a service project_name
```

## 关于abatuhr
https://github.com/yeyuexia/abathur
欢迎提issue：）


========================================

### 开发环境
- 安装好`docker`和`docker-compose`
- 在`docker/mysql/`目录下运行`docker-compose up -d`，启动数据库
- 在`docker/swagger-ui/`目录下运行`docker-compose up -d`，启动swagger ui
- 确保开发环境下有jdk8
- 在项目的根本路上，运行`./gradlew cleanIdea idea`生成Intellij工程
- 使用`Intellij`打开生成的`korprulu-store.ipr`文件
- 找到`KorpruluApplication`这个类，运行`main()`方法启动服务器

### 测试服务器是否启动正常

- 检查启动中有无异常log
- 打开浏览器，访问<http://localhost:8081>，看swagger ui是否成功现实API列表
- 打开浏览器，访问<http://localhost:8080/rest/store/1>，看是否返回包含`"errorCode":"RESOURCE_NOT_FOUND"`这样的出错信息

### 查看所有API 地址及请求所需方式、参数

- 利用swagger ui，访问：<http://localhost:8080/swagger>

*注意：更改localhost到你希望查看的服务器IP*
