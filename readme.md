- 参考文档 `http://www.cnblogs.com/coderland/p/5902867.html`

- 1.下载后使用`mvn eclipse:eclipse` 或则 `mvn idea:idea` 
  命令生成eclipse 或idea的maven项目，然后import即可

- 2.进入项目根目录下输入`mvn tomcat6:run`命令即可运行项目

- 3.http://localhost:8080/  用户名：zhangsan 密码：111111

- 注意事项：

> 注意当手动logout后rememberMe生成的cookie将会被清空，
    之后不可访问remember的页面。
> 如果是直接关闭浏览器，则不会被清空，可以继续访问rememberMe的页面。

