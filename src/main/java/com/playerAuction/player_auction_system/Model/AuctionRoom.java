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
    public class AuctionRoom {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int auctionId;

        private String auctionName;

        private String status;

        @OneToMany(mappedBy = "auctionRoom" , cascade = CascadeType.ALL)
        private List<Team> teamList;

        @OneToMany(mappedBy = "auctionRoom" , cascade = CascadeType.ALL)
        private List<Player> playerList;

        public AuctionRoom(String auctionName,String status, List<Team> teamList, List<Player> playerList){
            this.auctionName = auctionName;
            this.status = status;
            this.teamList = teamList;
            this.playerList = playerList;
        }
    }
