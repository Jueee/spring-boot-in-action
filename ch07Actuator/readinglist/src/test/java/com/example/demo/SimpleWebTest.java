package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingListApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT) // 用随机端口启动
public class SimpleWebTest {

    private static ChromeDriver browser;

    @Value("${local.server.port}") // 注入端口号
    private int port;

    @BeforeClass
    public static void openBrowser() {
        // 加载 Chrome 驱动
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");   // 自动化不打开浏览器测试
        browser = new ChromeDriver(chromeOptions); // 配置 Chrome 驱动
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void printPort() {
        System.out.println(port);
    }

    // 加载首页，登录验证并发送表单，填充书籍信息并发送表单，然后判断登录的页面是否包含刚刚添加的新书
    @Test
    public void addBookToEmptyList() {
        // 加载首页
        String baseUrl = "https://localhost:" + port;
        browser.get(baseUrl);
        // 登录验证并发送表单
        browser.findElementByName("username").sendKeys("craig");
        browser.findElementByName("password").sendKeys("password");
        browser.findElementByTagName("form").submit();
        // 填充书籍信息并发送表单
        assertEquals("You have no books in your book list", browser.findElementByTagName("div").getText());
        browser.findElementByName("title").sendKeys("BOOK TITLE");
        browser.findElementByName("author").sendKeys("BOOK AUTHOR");
        browser.findElementByName("isbn").sendKeys("1234567890");
        browser.findElementByName("description").sendKeys("DESCRIPTION");
        browser.findElementById("addbookform").submit();
        // 判断登录的页面是否包含刚刚添加的新书
        WebElement dl = browser.findElementByCssSelector("dt.bookHeadline");
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)", dl.getText());
        WebElement dt = browser.findElementByCssSelector("dd.bookDescription");
        assertEquals("DESCRIPTION", dt.getText());
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit(); // 关闭浏览器
    }

}
