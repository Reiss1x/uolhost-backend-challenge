package com.uolhost.uolhostbackendchallenge.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uolhost.uolhostbackendchallenge.domain.Player;
import com.uolhost.uolhostbackendchallenge.dtos.PlayerDTO;
import com.uolhost.uolhostbackendchallenge.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<String> avengers = fs.fetchAvengers();

        List<Player> players = repo.findAll();
        List<Player> team = players.stream()
                .filter(obj -> obj.getTeam().equals(player.team())).toList();

        for (String code : avengers) {
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
