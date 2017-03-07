# 项目简介
 
  该项目是一个微博Web应用程序，在eclipse+maven+tomcat+mysql的开发环境下用jsp/servlet搭建而成，具有会员注册，会员登录，会员动态信息新增，读取，显示等功能。
 
 
## 功能介绍
* 会员注册: 注册会员的信息，包括姓名，邮箱，密码
* 会员登录：查询数据库中所有的会员，如果数据库有对应记录，显示登录成功页面
* 会员动态信息显示：根据会员储存在数据库的账号信息，显示该会员之前发表过的动态，并且能实时删除添加
* 会员注册失败信息提醒

## 设计模式
* MVC设计模式，各功能层解耦
* 数据库连接及DAO设计,分离储存逻辑和业务逻辑

## 搭建环境
 Myeclipse + maven + tomcat + Mysql

## 项目工程截图

### 注册界面

  ![alt text](https://github.com/wjddxyjx/xlweibo/blob/master/Screenshots/%E6%B3%A8%E5%86%8C.PNG)
  
### 注册失败界面

  ![alt text](https://github.com/wjddxyjx/xlweibo/blob/master/Screenshots/%E6%B3%A8%E5%86%8C%E5%A4%B1%E8%B4%A5.PNG)
  
### 登录界面

  ![alt text](https://github.com/wjddxyjx/xlweibo/blob/master/Screenshots/%E7%99%BB%E5%BD%95.PNG)
  
### 显示界面

  ![alt text](https://github.com/wjddxyjx/xlweibo/blob/master/Screenshots/%E6%98%BE%E7%A4%BA.PNG)
