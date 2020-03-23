## spring全家桶系列
### 06 leo-spring-programmatic-transaction
本例子主要展示 编程式事务

spring 的事务抽象

一致的事务模型
* JDBC/Hibernate/mybatis
* DataSource/JTA


事务抽象的核心接口

PlatformTransactionManager
* DataSourceTransactionManager
* HibernateTransactionManager
* JtaTransactionManager

TransactionDefinition
* Propagation
* lsolation
* Timeout
* Read-only status


事务的传播特性

|传播性|值|描述|
| :----- | :----- | :----- |
|PROPERGATION_REQUIRED|0|当前有事务就用当前的，没有就用新的|
|PROPERGATION_SUPPORTS|1|事务可有可无，不是必须的|
|PROPERGATION_MANDATORY|2|当前一定要有事务，不然就抛异常|
|PROPERGATION_REQUIRES_NEW|3|无论是否有事务，都起个新的事务|
|PROPERGATION_NOT_SUPPORT|4|不支持事务，按非事务方式运行|
|PROPERGATION_NEVER|5|不支持事务，如果有事务则抛异常|
|PROPERGATION_NESTED|6|当前有事务则在当前事务中再起一个事务|


事务的隔离级别

|隔离性|描述|值|脏读|不可重复读|幻读|
| :----- | :----- | :----- | :----- | :----- | :----- |
|ISOLOCATION_DEFAULT|数据库默认级别|-1||||
|ISOLOCATION_READ_UNCOMMITTED|允许读取未提交的读， 可能导致脏读，不可重复读，幻读|1|√|√|√|
|ISOLOCATION_READ_COMMITTED|允许读取已提交的读，可能导致不可重复读，幻读|2|×|√|√|
|ISOLOCATION_REPEATABLE_READ|不能能更新另一个事务修改单尚未提交(回滚)的数据，可能引起幻读|3|×|×|√|
|ISOLOCATION_SERIALIZABLE|序列执行效率低|4|×|×|×|



编程式事务

TransactionTemplate
* TransactionCallback -- 有返回值
* TransactionCallbackWithoutResult -- 没有返回值

PlatformTransactionManager
* 可以传入TransactionDefinition进行定义
