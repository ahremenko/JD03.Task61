package java.by.htp.ahremenko.task61.controller;

import java.by.htp.ahremenko.task61.dao.BookDao;
import java.by.htp.ahremenko.task61.model.Book;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookDao bookDao;

    public BookController( BookDao bookDao ) {
        this.bookDao = bookDao;
    }

    @GetMapping("/books")
    List<Book> all() {
        return bookDao.getSortedEntitiesPage(1, "", false, 10);
    }

}
