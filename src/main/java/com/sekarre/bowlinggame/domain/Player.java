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

    @Builder.Default
    private Integer thisRoundHitPinsCount = 0;

    @Builder.Default
    private Integer score = 0;

    @Builder.Default
    private Integer bonusStrikeScore = 0;

    private ScoreType scoreType;

    @Builder.Default
    private Integer turnOfRound = 1;

    @JoinColumn(name = "game_id")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private Game game;
}
