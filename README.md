# auction sample code
This is my repo for the auction sample.

The application works correctly using a PostgreSQL back end database. The application
is a REST microservice that implements the following methods:

* getAuctions - get a list of all auctions
* createAndStartAuction - creates an auction and starts it
* placeBidOnAuction - allows a user to place a bid on an auction
* endAuction - ends an auction

I've also implemented [Swagger](https://swagger.io/) documentation of the API.

I ran out of time, so I didn't write any unit tests.

    