package com.uolhost.uolhostbackendchallenge.controllers;

import com.uolhost.uolhostbackendchallenge.domain.Player;
import com.uolhost.uolhostbackendchallenge.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class PageController {
    @Autowired
    private PlayerService ps;

    @RequestMapping("/homes")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");
        return modelAndView;
    }
    @GetMapping("/")
    public ResponseEntity<List<Player>> getPlayers(){
        return new ResponseEntity<List<Player>>(ps.getPlayers(), HttpStatus.OK);
    }
}
