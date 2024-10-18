package com.asrihashim.library.action;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asrihashim.library.book.BookRepository;
import com.asrihashim.library.borrower.BorrowerRepository;
import com.asrihashim.library.movement.Movement;
import com.asrihashim.library.movement.MovementRepository;
import com.asrihashim.library.util.Result;
import com.asrihashim.library.util.StatusEnum;


@RestController
@RequestMapping("/borrow")
public class BorrowController {
    
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    MovementRepository movementRepository;

    @PostMapping("/book")
    public Object borrowBook(@RequestParam String bookId, @RequestParam String borrowerId) {
        Result r = new Result();

        System.out.println(" book : "+ bookId + " ; borrower : " + borrowerId);

        try {
            if (movementRepository.findByItemIdAndStat(bookId,0).isPresent()) {
                r.setStatus(StatusEnum.FAIL);
                r.setMessage("This is awkward. Book already checked out.");
            }
            else {
                Movement m = new Movement();
                m.setItemId(bookId);
                m.setBorrowerId(borrowerId);
                m.setStat(0);
                m.setMovementDate(LocalDateTime.now());
                movementRepository.save(m);

                r.setStatus(StatusEnum.SUCCESS);
                r.setMessage("Book check-out successful.");
            }
        }
        catch (Exception e) {
            r.setStatus(StatusEnum.SYSERR);
            r.setMessage(e.getMessage());
            r.setData(e.getStackTrace().toString());
        }

        return r;
    }
    
}
