package com.sekarre.bowlinggame.domain;

import com.sekarre.bowlinggame.domain.enums.ScoreType;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numberInQueue;

    private String name;

    private Integer lastHitPinsCount;

    private Integer score = 0;

    private Integer bonusStrikeScore = 0;

    private ScoreType scoreType;

    private Integer turnOfRound = 1;

    @JoinColumn(name = "game_id")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private Game game;
}
