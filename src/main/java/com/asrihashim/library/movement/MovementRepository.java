package com.asrihashim.library.movement;

import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface MovementRepository extends Repository<Movement, Integer>{
    Movement save(Movement borrower);
    Optional<Movement> findByItemId(String itemId);
    Optional<Movement> findByItemIdAndStat(String itemId, int stat);
    Optional<Movement> findByItemIdAndBorrowerIdAndStat(String itemId, String borrowerId, int stat);
}
