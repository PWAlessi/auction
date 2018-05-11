package com.narrative.auction.controllers;

import com.narrative.auction.entities.Auction;
import com.narrative.auction.entities.Bid;
import com.narrative.auction.repositories.AuctionRepository;
import com.narrative.auction.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Date;
import java.util.Optional;


@RestController
public class AuctionRestControllerImpl implements AuctionRestController {

    private final
    AuctionRepository auctionRepository;

    private final
    BidRepository bidRepository;

    @Autowired
    public AuctionRestControllerImpl(AuctionRepository auctionRepository, BidRepository bidRepository) {
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
    }

    @GetMapping("/getAuctions")
    @Override
    public Iterable<Auction> getAuctions() {
        return auctionRepository.findAll();
    }

    @PostMapping("/createAndStartAuction")
    @Override
    public Auction createAndStartAuction(@RequestParam("auctionOwnerId") Long auctionOwnerId,
                                         @RequestParam("auctionName") String auctionName,
                                         @RequestParam("endTime") Date endTime) {

        // If date is before now, throw an exception
        if (endTime.compareTo(new Date()) < 0) {
            throw new UncheckedIOException("Bad Date", new IOException());
        }

        // Create and save an Auction
        Auction auction = Auction.builder()
                .auctionOwnerId(auctionOwnerId)
                .auctionName(auctionName)
                .endTime(endTime)
                .build();
        auctionRepository.save(auction);

        return auction;
    }

    @PostMapping("/placeBidOnAuction")
    @Override
    public Bid placeBidOnAuction(@RequestParam("auctionId") Long auctionId,
                                 @RequestParam("userId") Long userId,
                                 @RequestParam("bidAmount") float bidAmount) {

        Auction auction = getAuction(auctionId);

        if (auction.getEndTime().compareTo(new Date()) < 0) {
            // Auction has ended
            throw new UncheckedIOException("Auction has ended", new IOException());
        }

        Bid bid = Bid.builder()
                .auctionId(auctionId)
                .userId(userId)
                .bidAmount(bidAmount)
                .build();


        bidRepository.save(bid);

        return bid;
    }

    private Auction getAuction(@RequestParam("auctionId") Long auctionId) {
        // Get the auction
        Optional<Auction> optionalAuction = auctionRepository.findById(auctionId);

        if (!optionalAuction.isPresent()) {
            // No auction
            throw new UncheckedIOException("Auction does not exist", new IOException());
        }

        return optionalAuction.get();
    }

    @Override
    @PostMapping("/endAuction")
    public Auction endAuction(@RequestParam("auctionId") Long auctionId) {
        Auction auction = getAuction(auctionId);

        auction.setEndTime(new Date());
        auctionRepository.save(auction);

        return auction;
    }


}
