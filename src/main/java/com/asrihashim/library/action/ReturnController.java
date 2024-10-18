package com.asrihashim.library.action;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/return")
public class ReturnController {
    
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    MovementRepository movementRepository;

    @Transactional
    @PostMapping("/book")
    public Object returnBook(@RequestParam String bookId, @RequestParam String borrowerId) {
        Result r = new Result();

        try {
            Optional<Movement> mo = movementRepository.findByItemIdAndStat(bookId, 0);

            if (!mo.isPresent()) {
                r.setStatus(StatusEnum.FAIL);
                r.setMessage("This is awkward. No record of said book being borrowed.");
            }
            else {
                mo = movementRepository.findByItemIdAndBorrowerIdAndStat(bookId, borrowerId, 0);
                
                if (!mo.isPresent()) {
                    r.setStatus(StatusEnum.FAIL);
                    r.setMessage("This is awkward. No record of said borrower borrowing said book.");
                }
                else {
                    Movement m = mo.get();
                    m.setStat(1);
                    m.setMovementDate(LocalDateTime.now());
                    movementRepository.save(m);

                    r.setStatus(StatusEnum.SUCCESS);
                    r.setMessage("Book returned successfully.");
                }
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
