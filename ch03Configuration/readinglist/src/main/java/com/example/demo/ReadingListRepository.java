package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



// 通过扩展 JpaRepository ， ReadingListRepository 直接继承了18个执行常用持久化操作的方法。 
// JpaRepository 是个泛型接口，有两个参数：仓库操作的领域对象类型，及其ID属性的类型。
public interface ReadingListRepository extends JpaRepository<Book, Long>{
    // 根据读者的用户名来查找阅读列表
    List<Book> findByReader(String reader);
    
}
