## 测试

### 测试分类

#### 单元测试

在编写单元测试的时候，Spring通常不需要介入。

Spring鼓励松耦合、接口驱动的设计，这些都能让你很轻松地编写单元测试。

但是在写单元测试时并不需要用到Spring。

#### 集成测试

集成测试要用到Spring。

如果生产应用程序使用Spring来配置并组装组件，那么测试就需要用它来配置并组装那些组件。

### SpringBootTest

Spring的 `SpringBootTest` 可以在基于 `JUnit` 的应用程序测试里加载Spring应用程序上下文。

在测试Spring Boot应用程序时，Spring Boot除了拥有Spring的集成测试支持，还开启了自动配置和Web服务器，并提供了不少实用的测试辅助工具。

```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
@WebAppConfiguration // 开启Web上下文测试
public class SpringSecurityTests {}
```

### 集成测试方案

Spring MVC有一个优点：它的编程模型是围绕POJO展开的，在POJO上添加注解，声明如何处理Web请求。

这种编程模型不仅简单，还让你能像对待应用程序中的其他组件一样对待这些控制器。

要恰当地测试一个Web应用程序，需要投入一些实际的HTTP请求，确认它能正确地处理那些请求。

Spring Boot开发者有两个可选的方案能实现这类测试。

- **[Spring Mock MVC](4.2测试Web应用程序.md)**：  
  能在一个近似真实的模拟Servlet容器里测试控制器，而不用实际启动应用服务器。
- **[Web集成测试](4.3测试运行中的应用程序.md)**：  
  在嵌入式Servlet容器（比如Tomcat或Jetty）里启动应用程序，在真正的应用服务器里执行测试。

这两种方法各有利弊。很明显，启动一个应用服务器会比模拟Servlet容器要慢一些，但毫无
疑问基于服务器的测试会更接近真实环境，更接近部署到生产环境运行的情况。

### 版本升级导致的注解更迭

[参考资料](<https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-1.4-Release-Notes>)

Spring Boot 1.4尝试合理化和简化Spring Boot测试的各种运行方式。

应该迁移以下内容以使用新`@SpringBootTest`注释：

- 从`@SpringApplicationConfiguration(classes=MyConfig.class)`到`@SpringBootTest(classes=MyConfig.class)`
- 从`@ContextConfiguration(classes=MyConfig.class, loader=SpringApplicationContextLoader.class)`到`@SpringBootTest(classes=MyConfig.class)`
- 从`@IntegrationTest`到`@SpringBootTest(webEnvironment=WebEnvironment.NONE)`
- 从`@IntegrationTest with @WebAppConfiguration`到`@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)`（或`RANDOM_PORT`）
- 从`@WebIntegrationTest`到`@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)`（或`RANDOM_PORT`）

在迁移测试时，您可能还希望`@RunWith(SpringJUnit4ClassRunner.class)`用Spring 4.3 替换任何声明更具可读性`@RunWith(SpringRunner.class)`。