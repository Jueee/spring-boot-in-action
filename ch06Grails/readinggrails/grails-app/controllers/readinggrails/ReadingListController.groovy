package readinggrails

import grails.gorm.transactions.Transactional

class ReadingListController {

    def index() {
        // 获取图书填充到模型里
        respond Book.list(params), model:[book: new Book()]
    }

    // 保存图书
    @Transactional
    def save(Book book) {
        book.reader = 'Craig'
        book.save flush:true
        redirect(action: "index")
    }
}
