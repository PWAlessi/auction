package com.narrative.auction.controllers;

import com.narrative.auction.entities.Auction;
import com.narrative.auction.entities.Bid;

import java.util.Date;

public interface AuctionRestController {

    Iterable<Auction> getAuctions();

    Auction createAndStartAuction(Long auctionOwnerId, String auctionName, Date endTime);
    Bid placeBidOnAuction(Long auctionId, Long userId, float bidAmount);
    Auction endAuction(Long auctionId);


//    Include basic business logic to handle starting an auction, placing a bid, and ending an auction.

}
