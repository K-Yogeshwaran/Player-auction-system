package com.playerAuction.player_auction_system.RepositoryLayer;

import com.playerAuction.player_auction_system.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Users getByUserName(String username);
}
