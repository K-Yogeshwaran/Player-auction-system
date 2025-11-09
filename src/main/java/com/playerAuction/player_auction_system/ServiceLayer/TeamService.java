package com.playerAuction.player_auction_system.ServiceLayer;


import com.playerAuction.player_auction_system.Model.Player;
import com.playerAuction.player_auction_system.Model.Team;
import com.playerAuction.player_auction_system.RepositoryLayer.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public void createNewTeam(Team team){
        teamRepository.save(team);
    }

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public Team getTeamById(int teamId){
        return teamRepository.findById(teamId).orElseThrow();
    }

    public List<Player> getPlayersByTeam(int teamId){
        Team team = teamRepository.findById(teamId).orElseThrow();
        return team.getPlayerList();
    }

    public Team updateBalance(int teamId, double newBalance) {
        Team team = teamRepository.findById(teamId).orElseThrow(()-> new RuntimeException("Team not found"));
        team.setPurseAmount(newBalance);
        return team;
    }
}
