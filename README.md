# Web Automation Testing Framework with Gauge and Selenium

这是一个使用Gauge、Selenium和Java构建的Web自动化测试框架。它遵循页面对象模型（POM）模式，以提高可维护性和可重用性。

## 先决条件

- Java 11或更高版本
- Maven
- Gauge (https://gauge.org/get-started/)
- Chrome或Firefox浏览器

## 项目结构

- `specs/`: 包含Gauge规范文件
- `src/test/java/pages/`: 页面对象
- `src/test/java/utils/`: 工具类
- `src/test/java/`: 步骤实现
- `src/test/resources/`: 配置文件
- `screenshots/`: 测试过程中生成的截图
- `logs/`: 测试日志文件
- `reports/`: Gauge生成的HTML报告

## 运行测试

执行以下命令运行所有测试：

```bash
gauge run specs
```

运行特定规范：

```bash
gauge run specs/login.spec
```

使用Maven运行测试：

```bash
mvn test
```

## 环境变量

- `BROWSER`: 设置为'chrome'或'firefox'以指定使用哪种浏览器（默认：chrome）

示例:
```bash
BROWSER=firefox gauge run specs
```

## 示例测试

该框架包含一个示例测试，该测试：
1. 导航到http://the-internet.herokuapp.com/login
2. 使用有效凭据登录
3. 验证登录成功

## 框架特性

- 页面对象模型（POM）架构
- 使用WebDriverManager进行WebDriver管理
- 显式等待以提高稳定性
- 跨浏览器测试支持
- 测试失败时自动截图
- 全面的HTML报告
- 日志记录（使用SLF4J和Logback）
- 优化的异常处理
- 灵活的钩子实现（BeforeSuite, AfterSuite, BeforeScenario, AfterScenario）
- 截图工具类

## 日志

框架使用SLF4J和Logback进行日志记录。日志文件保存在`logs`目录中。可以在`src/test/resources/logback.xml`中配置日志级别和输出位置。

## 截图

在测试失败时，框架会自动截图。此外，也可以在任何时候手动截图：

```java
ScreenshotUtil.takeScreenshot("screenshot-name");
```

截图保存在`screenshots`目录中。

## 扩展框架

要添加新的测试场景：
1. 在`specs/`目录中创建新的规范文件
2. 在`src/test/java/pages/`中添加新的页面对象类
3. 在`src/test/java/`中实现相应的步骤 