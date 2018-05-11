package com.narrative.auction.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "AppUser")
class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    private String userName;

}
