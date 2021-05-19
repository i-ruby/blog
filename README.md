# 前后端分离的博客平台实现
技术栈 (`Spring Boot` + `Spring Security` + `JUnit 5` + `Mybatis` + `Docker` )

1. 使用`Docker`做环境搭建(应用正式数据库和测试数据库的搭建)
2. 使用`Spring Boot` 作为web 框架,用来简化开发
3. 使用`Spring Security` 提供身份认证和鉴权功能,保证部分接口需要登录后才能访问
4. 使用`JUnit 5` 维护了一些单元测试和集成测试,搭配Ci保证了应用的健壮性
5. 接口测试中,使用了了`Spring` 自带的`TestRestTemplate` 简化访问,并且使用了 `Mockito` 来实现部分功能的单元测试

`Docker` 中创建一个mysql container ,,初始化root密码为root,并初始化创建一个blog数据库
```
docker run --name mysql -v ~/docker/mysql:/var/lib/mysql -p 3306:3306 -e TZ=Asia/Shanghai -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=blog -d mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci```