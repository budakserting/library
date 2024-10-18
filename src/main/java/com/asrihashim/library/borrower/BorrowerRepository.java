package com.asrihashim.library.borrower;

import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface BorrowerRepository extends Repository<Borrower, String> {
    Borrower save(Borrower borrower);
    Optional<Borrower> findById(String id);
    Optional<Borrower> findByEmail(String email);
}