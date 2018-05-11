package com.narrative.auction.repositories;

import com.narrative.auction.entities.Auction;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository  extends CrudRepository<Auction, Long> {
}
