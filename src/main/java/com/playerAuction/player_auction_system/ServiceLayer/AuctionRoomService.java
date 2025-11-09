package com.playerAuction.player_auction_system.ServiceLayer;


import com.playerAuction.player_auction_system.Model.AuctionRoom;
import com.playerAuction.player_auction_system.Model.Team;
import com.playerAuction.player_auction_system.RepositoryLayer.AuctionRoomRepository;
import com.playerAuction.player_auction_system.RepositoryLayer.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionRoomService {

    @Autowired
    private AuctionRoomRepository auctionRoomRepository;

    @Autowired
    private TeamRepository teamRepository;

    public AuctionRoom createRoom(AuctionRoom auctionRoom) {
        return auctionRoomRepository.save(auctionRoom);
    }

    public List<AuctionRoom> getAllAuctionRooms() {
        return auctionRoomRepository.findAll();
    }

    public AuctionRoom getRoomById(int id) {
        return auctionRoomRepository.findById(id).orElseThrow();
    }

    public AuctionRoom addTeamToAuctionRoom(int roomId, int teamId) {
        AuctionRoom room = auctionRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));

        room.getTeamList().add(team);
        team.setAuctionRoom(room);
        auctionRoomRepository.save(room);
        teamRepository.save(team);
        return room;
    }

    public List<Team> getTeamListByRoomId(int roomId) {
        AuctionRoom room = auctionRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        return room.getTeamList();
    }
}
