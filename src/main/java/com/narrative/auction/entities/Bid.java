package com.narrative.auction.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long bidId;

    private Long auctionId;

    private Long userId;

    private float bidAmount;

}
