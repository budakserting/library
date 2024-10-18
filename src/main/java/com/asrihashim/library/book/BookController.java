package com.asrihashim.library.book;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asrihashim.library.util.Result;
import com.asrihashim.library.util.StatusEnum;



@RestController
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    BookRepository bookRepository;

    @PostMapping("/add")
    public Object addBook(@RequestBody Book book) {
        Result r = new Result();

        try {
            if (book.getTitle().isBlank()) {
                r.setStatus(StatusEnum.ERROR);
                r.setMessage("Book must at least have a title.");
            }
            else if (!validateIsbnTitleAuthor(book)) {
                r.setStatus(StatusEnum.FAIL);
                r.setMessage("ISBN " + book.getIsbn() + " already registered with different Title and/or Author.");
            }
            else {
                book.setId(UUID.randomUUID().toString().replace("-", ""));
                bookRepository.save(book);

                r.setStatus(StatusEnum.SUCCESS);
                r.setMessage("New book added.");
            }
        }
        catch (Exception e) {
            r.setStatus(StatusEnum.SYSERR);
            r.setMessage(e.getMessage());
            r.setData(e.getStackTrace().toString());
        }

        return r;
    }

    private boolean validateIsbnTitleAuthor(Book book) throws Exception {
        boolean res = true;
        Optional<Book> found = bookRepository.findByIsbn(book.getIsbn());
        if (found.isPresent()) {
            Book b = found.get();
            if (!b.getTitle().equals(book.getTitle()) || !b.getAuthor().equals(book.getAuthor()))
                res = false;
        }
        return res;
    }

    @GetMapping("/all")
    public Object getAllBook() {
        Result r = new Result();
        
        try {
            r.setStatus(StatusEnum.SUCCESS);
            r.setData(bookRepository.getAllBook());
        }
        catch (Exception e) {
            r.setStatus(StatusEnum.SYSERR);
            r.setMessage(e.getMessage());
            r.setData(e.getStackTrace().toString());
        }

        return r;
    }
    
    
}
