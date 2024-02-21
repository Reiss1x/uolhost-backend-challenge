package com.uolhost.uolhostbackendchallenge.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="players")
@Table(name="players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private String telephone;
    private String codename;
    @Enumerated(EnumType.STRING)
    private Team team;
}

//(String email, String name, String telephone, String codename, List list)
