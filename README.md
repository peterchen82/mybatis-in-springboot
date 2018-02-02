# mybatis-in-springboot
mybatis与springboot整合项目模板，包含了mybatis在springboot的常用用法。
项目模板是一个高度工程化的样例，可直接在正式项目中使用。
## 特性
* 完整的service和rest api测试用例
* 测试用例覆盖率100%
* 集成了redis缓存
* 多环境(dev/test/prod)支持，在test环境下使用内嵌的H2数据库进行测试
* 集成jacoco分析代码覆盖率
* 集成阿里代码规约PMD插件
* 本地事务支持
* 对大数据量表（超过1千万条数据）的分页优化

## 打包并生成site
执行 `mvn clean package -Pprod site` 打包并生成各类项目报告


