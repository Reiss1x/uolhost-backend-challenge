package com.uolhost.uolhostbackendchallenge.repositories;

import com.uolhost.uolhostbackendchallenge.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
