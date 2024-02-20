package com.uolhost.uolhostbackendchallenge.Controllers;

import com.uolhost.uolhostbackendchallenge.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService ps;

    @GetMapping("/vingadores")
    public void vingadores() throws Exception {
        ps.validatePlayer();
    }
}
