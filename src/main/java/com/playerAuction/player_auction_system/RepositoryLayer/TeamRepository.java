package com.playerAuction.player_auction_system.RepositoryLayer;

import com.playerAuction.player_auction_system.Model.Player;
import com.playerAuction.player_auction_system.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {


}
