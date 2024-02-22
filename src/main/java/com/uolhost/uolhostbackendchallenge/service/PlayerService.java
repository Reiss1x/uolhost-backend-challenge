package com.uolhost.uolhostbackendchallenge.service;


import com.uolhost.uolhostbackendchallenge.domain.Player;
import com.uolhost.uolhostbackendchallenge.domain.Team;
import com.uolhost.uolhostbackendchallenge.dtos.PlayerDTO;
import com.uolhost.uolhostbackendchallenge.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayerService {


    @Autowired
    private PlayerRepository repo;

    @Autowired
    private FilesService fs;

    public List<Player> getPlayers(){
        return repo.findAll();
    }

    public Player validatePlayer(PlayerDTO player) throws Exception {
        List<String> squad;

        if(player.team().equals(Team.VINGADORES)){
            squad = fs.fetchAvengers();
        } else {
            squad = fs.fetchLeague();
        }

        List<Player> players = repo.findAll();
        List<Player> team = players.stream()
                .filter(obj -> obj.getTeam().equals(player.team())).toList();

        for (String code : squad) {
            boolean available = true;
            for(Player p : team) {
                if (p.getCodename().equals(code)) {
                    available = false;
                    break;
                }
            }
            if(available) {
                Player newPlayer = new Player();
                newPlayer.setEmail(player.email());
                newPlayer.setName(player.name());
                newPlayer.setTelephone(player.telephone());
                newPlayer.setCodename(code);
                newPlayer.setTeam(player.team());
                repo.save(newPlayer);
                return newPlayer;
            }
        }
        return null;
    }
}
