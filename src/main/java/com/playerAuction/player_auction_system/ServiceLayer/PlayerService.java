package com.playerAuction.player_auction_system.ServiceLayer;


import com.playerAuction.player_auction_system.Model.Player;
import com.playerAuction.player_auction_system.RepositoryLayer.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public void addPlayerList(List<Player> playerList) {
        playerRepository.saveAll(playerList);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(int id) {
        return playerRepository.findById(id).orElseThrow();
    }


    public void updatePlayer(Player player) {
         playerRepository.save(player);
    }
}
