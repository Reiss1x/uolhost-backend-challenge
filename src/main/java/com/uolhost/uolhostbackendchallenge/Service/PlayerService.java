package com.uolhost.uolhostbackendchallenge.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uolhost.uolhostbackendchallenge.domain.Team;
import com.uolhost.uolhostbackendchallenge.domain.Player;
import com.uolhost.uolhostbackendchallenge.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class PlayerService {

    private String vingadores = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";

    @Autowired
    private PlayerRepository repo;

    public void validatePlayer(/*Player player*/) throws Exception {
        List<Map<String, String>> avengers = fetchCodenames();
        for (Map<String, String> v : avengers){
            System.out.println(v.get("codinome"));
        }

        /*List<Player> list = repo.findAll();
        if(player.getTeam() == Team.VINGADORES){
            for (Player p : list){
                if
            }
        }*/
    }

    public List<Map<String, String>> fetchCodenames() throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(vingadores))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Map<String, String>>> jsonData = mapper.readValue(responseBody, new TypeReference<Map<String, List<Map<String, String>>>>() {
        });
        return jsonData.get("vingadores");
    }


}
