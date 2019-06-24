package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller // 组件扫描会自动将其注册为Spring应用程序上下文里的一个Bean
@RequestMapping("/")    // 将其中所有的处理器方法都映射到了“/”这个URL路径上。
@ConfigurationProperties(prefix="amazon")   // 属性注入
public class ReadingListController {
    
    private String associateId;
    
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }
    
    // associateId 的 setter 方法
    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    // 处理/{reader}上的HTTP GET 请求，根据路径里指定的读者
    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks(Reader reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
        }
        model.addAttribute("amazonID", associateId);    // 将 associateId 放入模型
        return "readingList";
    }

    // 处理/{reader}上的HTTP POST 请求，将请求正文里的数据绑定到一个 Book 对象上。
    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(Reader reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";
    }
}
