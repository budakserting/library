package com.asrihashim.library.borrower;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "borrower")
public class Borrower {

    @Id
    private String id;
    private String name;
    private String email;

}