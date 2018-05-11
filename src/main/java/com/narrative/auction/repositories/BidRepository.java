package com.narrative.auction.repositories;

import com.narrative.auction.entities.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, Long> {
}
