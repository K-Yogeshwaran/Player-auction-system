package com.playerAuction.player_auction_system.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId;

    private String teamName;

    private double purseAmount;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private AuctionRoom auctionRoom;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bid> bidList;

    @OneToMany(mappedBy = "team" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> playerList;

    public Team(String teamName, double purseAmount,List<Player> playerList,AuctionRoom auctionRoom, List<Bid> bidList){
        this.teamName = teamName;
        this.purseAmount = purseAmount;
        this.playerList = playerList;
        this.auctionRoom = auctionRoom;
        this.bidList = bidList;
    }
}
