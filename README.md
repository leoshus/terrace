TERRACE
=======
 terrace是Jave Web企业级开发的scaffold,前端利用jQuery强大的插件,项目整体使用struts+Spring+Spring Data JPA+Hiberante,提供基础数据结构和前端UI,
 计划使前端UI和数据端尽可能解耦。
## CORE 
	* 整体也算是使用了SSH,不过计划尝试SpringMVC
	* 提供通用的DAO Service Controller层 利用Spring Data JPA 简化了持久层业务逻辑的代码量,提高开发效率，统一标准
	* 使用Spring Security来进行基于资源的细粒度权限管理(不过使用Apache Shiro的人也挺多)
	* 使用jQuery/BootStrap 3作为前端UI的支柱
	* 基于Maven来构建管理项目,Maven的jar包管理解放了开发人员,其自身pom的集成机制 使项目可以模块化开发 大大提高了项目的开发效率
	* 基于Travis-CI 自动化持续集成构建项目
	
	
### Snapshot
![](http://ww3.sinaimg.cn/mw690/6e748ab3jw1ec80d9zh3fj211i0h5did.jpg)
> Travis CI Status: [![Build Status](https://travis-ci.org/sdw2330976/terrace.png?branch=master)](https://travis-ci.org/sdw2330976/terrace)
## TERRACE construting... ...
