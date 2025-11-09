package com.playerAuction.player_auction_system.RepositoryLayer;

import com.playerAuction.player_auction_system.Model.AuctionRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRoomRepository extends JpaRepository<AuctionRoom,Integer> {
}
