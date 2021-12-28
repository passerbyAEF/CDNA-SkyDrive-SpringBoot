# CDNA-SkyDrive-SpringBoot
广东水力电力职业技术学院 计算机科学与技术专插本班 JAVA课程作业\
我之前参与后端制作的 [CDNA-SkyDrive网盘项目（后端通过ASP.NET Core实现）](https://github.com/Jumposc/CDNA-SkyDrive) 的Spring Boot重构版本

这次重构开发者只有我一人，我的前端水平也有限，所以前端布局没改只是修改了一些代码，实现了一些老版本设计上有但是没有实现的功能\
（移动、复制、剪切功能依旧没做，因为我不会写前端的弹框）\
修复了之前APS.NET版本的一些bug\
文件上传不支持大过200MB的文件，我没有做分片上传，只是把包限制拉到了200MB。

使用技术:
-
+ Spring Boot
+ Spring Security
+ Redis
+ Mybatis & Mybatis-Plus
+ thymeleaf(因为没有什么Nginx，路径上带个.html难看，所以加入这个转发前端，项目本质上是前后端分离的，thymeleaf不参与前端渲染)
