package com.asrihashim.library.movement;

import java.time.LocalDateTime;

import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Audited
@Table(name = "movement")
public class Movement {
    
    @Id
    int id;
    String itemId;
    String borrowerId;
    int stat;
    LocalDateTime movementDate;
}