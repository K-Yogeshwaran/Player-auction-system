    package com.playerAuction.player_auction_system.Model;


    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Entity
    public class Player {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String name;
        private String country;
        private String role;
        private double basePrice;
        private boolean isSold = false;

        @ManyToOne
        @JoinColumn(name = "team_id")
        private Team team;

        @ManyToOne
        @JoinColumn(name = "auction_id")
        private AuctionRoom auctionRoom;

        @OneToMany(mappedBy = "player" , cascade = CascadeType.ALL)
        private List<Bid> playerBidList;

        public Player(String name,String country, String role, double basePrice,boolean isSold ){
            this.name = name;
            this.country = country;
            this.role = role;
            this.basePrice = basePrice;
            this.isSold = isSold;
        }

    }
