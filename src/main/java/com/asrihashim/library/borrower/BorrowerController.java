package com.asrihashim.library.borrower;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asrihashim.library.util.Result;
import com.asrihashim.library.util.StatusEnum;


@RestController
@RequestMapping("/borrower")
public class BorrowerController {
    
    @Autowired
    BorrowerRepository borrowerRepository;

    @PostMapping("/add")
    public Object addBorrower(@RequestParam String name, @RequestParam String email) {
        Result r = new Result();

        try {
            if (!email.isBlank() && borrowerRepository.findByEmail(email).isPresent()) {
                r.setStatus(StatusEnum.FAIL);
                r.setMessage("Email address already registered.");
            }
            else if ((!name.isBlank() || !email.isBlank())) {
                Borrower b = new Borrower();
                b.setId(UUID.randomUUID().toString().replace("-", ""));
                b.setName(name);
                b.setEmail(email);
                borrowerRepository.save(b);

                r.setStatus(StatusEnum.SUCCESS);
                r.setMessage("New borrower added.");
            }
            else {
                r.setStatus(StatusEnum.ERROR);
                r.setMessage("At least 1 detail must be provided.");
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
