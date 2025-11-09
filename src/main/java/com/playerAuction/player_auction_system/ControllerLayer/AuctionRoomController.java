package com.playerAuction.player_auction_system.ControllerLayer;


import com.playerAuction.player_auction_system.Model.AuctionRoom;
import com.playerAuction.player_auction_system.Model.Team;
import com.playerAuction.player_auction_system.ServiceLayer.AuctionRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctionRooms")
public class AuctionRoomController {

    @Autowired
    private AuctionRoomService auctionRoomService;

    @PostMapping("/")
    public AuctionRoom createRoom(@RequestBody AuctionRoom auctionRoom){
        return auctionRoomService.createRoom(auctionRoom);
    }

    @GetMapping("/")
    public List<AuctionRoom> getAllAuctionRooms(){
        return auctionRoomService.getAllAuctionRooms();
    }

    @GetMapping("/{id}")
    public AuctionRoom getRoomById(@PathVariable("id") int id){
        return auctionRoomService.getRoomById(id);
    }

    @PostMapping("/{roomId}/addTeam/{teamId}")
    public AuctionRoom addTeamToAuctionRoom(@PathVariable("roomId") int roomId, @PathVariable("teamId") int teamId){
        return auctionRoomService.addTeamToAuctionRoom(roomId,teamId);
    }

    @GetMapping("/{roomId}/teams")
    public List<Team> getTeamListByRoomId(@PathVariable("roomId") int roomId){
        return auctionRoomService.getTeamListByRoomId(roomId);
    }
}
