package com.uolhost.uolhostbackendchallenge.controllers;

import com.uolhost.uolhostbackendchallenge.service.PlayerService;
import com.uolhost.uolhostbackendchallenge.domain.Player;
import com.uolhost.uolhostbackendchallenge.dtos.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService ps;

    @PostMapping("/players")
    public ResponseEntity<Player> registerPlayer(@RequestBody PlayerDTO player) throws Exception {
        Player newPlayer = ps.validatePlayer(player);

        if(newPlayer != null){
            return new ResponseEntity<>(newPlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, (HttpStatus.FORBIDDEN));
        }
    }
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getPlayers(){
        return new ResponseEntity<List<Player>>(ps.getPlayers(), HttpStatus.OK);
    }
}