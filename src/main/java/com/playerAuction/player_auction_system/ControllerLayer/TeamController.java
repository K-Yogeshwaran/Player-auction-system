package com.playerAuction.player_auction_system.ControllerLayer;


import com.playerAuction.player_auction_system.Model.Player;
import com.playerAuction.player_auction_system.Model.Team;
import com.playerAuction.player_auction_system.ServiceLayer.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/")
    public String createNewTeam(@RequestBody Team team){
        teamService.createNewTeam(team);
        return "Team Created Successfully.";
    }

    @GetMapping("/")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable("id") int teamId){
        return teamService.getTeamById(teamId);
    }

    @GetMapping("/{id}/players")
    public List<Player> getPlayersByTeam(@PathVariable("id") int teamId){
        return teamService.getPlayersByTeam(teamId);
    }

    @PutMapping("/{teamId}/balance")
    public Team updateBalance(@PathVariable("teamId") int teamId,@RequestParam double newBalance){
        return teamService.updateBalance(teamId,newBalance);
    }
}
