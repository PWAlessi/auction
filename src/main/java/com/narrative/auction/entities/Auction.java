package com.narrative.auction.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long auctionId;

    private Long auctionOwnerId;

    private String auctionName;

    private Date endTime;

    @OneToMany
    @JoinColumn (name="auctionId")
    private List<Bid>bids;

}
