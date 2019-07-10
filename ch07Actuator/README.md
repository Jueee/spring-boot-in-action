## Actuator 简介

Spring Boot Actuator 可以帮助你监控和管理Spring Boot应用，比如健康检查、审计、统计和HTTP追踪等。所有的这些特性可以通过 JMX 或者 HTTP endpoints 来获得。

Actuator同时还可以与外部应用监控系统整合，比如 [Prometheus](https://prometheus.io/), [Graphite](https://graphiteapp.org/), [DataDog](https://www.datadoghq.com/), [Influx](https://www.influxdata.com/), [Wavefront](https://www.wavefront.com/), [New Relic](https://newrelic.com/)等。这些系统提供了非常好的仪表盘、图标、分析和告警等功能，使得你可以通过统一的接口轻松的监控和管理你的应用。

Actuator使用[Micrometer](http://micrometer.io/)来整合上面提到的外部应用监控系统。这使得只要通过非常小的配置就可以集成任何应用监控系统。

#### 官方文档

[官方文档](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)

#### Actuator 内容

1. [揭秘 Actuator 的端点](7.1揭秘Actuator的端点.md)
3. [连接Actuator的远程shell](7.2连接Actuator的远程shell.md)
4. [通过JMX监控应用程序](7.3通过JMX监控应用程序.md)
5. 定制Actuator
   1. [定制Actuator：修改端点ID](7.4.1定制Actuator：修改端点ID.md)
   2. [定制Actuator：启用和禁用端点](7.4.2定制Actuator：启用和禁用端点.md)
   3. [定制Actuator：添加自定义度量信息](7.4.3定制Actuator：添加自定义度量信息.md)
   4. [定制Actuator：创建自定义跟踪仓库](7.4.4定制Actuator：创建自定义跟踪仓库.md)
   5. [定制Actuator：插入自定义健康指示器](7.4.5定制Actuator：插入自定义健康指示器.md)
6. [保护Actuator端点](7.5保护Actuator端点.md)