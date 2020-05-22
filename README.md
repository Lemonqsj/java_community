## 社区

## 资料
 [elastic](https://elasticsearch.cn/explore)  
 [Bootstrap](https://v3.bootcss.com/getting-started/)  
 [Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
 [spring-boot](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/reference/html/spring-boot-features.html#boot-features)

## 工具
 [idea 快捷键](https://www.jianshu.com/p/454c71172c46)

## 文档



## bug记录
    1. 导航栏的位置错位： 由于将js的引入错误导致，少了一个/，位置全部乱了
    
      <script src="/js/bootstrap.min.js" type="application/javascript"></script>
##脚本
   
   ```sql脚本
    create table USER(
          ID           INT auto_increment,
          ACCOUNT_ID   VARCHAR(100),
          NAME         VARCHAR(50),
          TOKEN        VARCHAR(36),
          GMT_CREATE   BIGINT,
          GMT_MODIFIED BIGINT,
          constraint USER_PK
              primary key (ID)
      );

     mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
    ```