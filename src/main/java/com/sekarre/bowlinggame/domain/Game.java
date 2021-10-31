package com.sekarre.bowlinggame.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
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
    private Integer playersCount;

    private Integer currentRound;

    private Integer totalRound;

    private Integer lastHitPins;

    private boolean gameEnded;

    private String winner;

    @JoinColumn(name = "player_id")
    @ManyToOne
    private Player currentMovingPlayer;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Player> players;
}
