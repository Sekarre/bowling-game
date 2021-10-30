package com.sekarre.bowlinggame.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Game {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Min(1)
    @Max(8)
    private Integer playersCount;

    private Integer currentRound;

    private Integer totalRound;

    private boolean gameEnded;

    private String winner;

    @JoinColumn(name = "player_od")
    @ManyToOne
    private Player currentPlayerMoving;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Player> players;
}
