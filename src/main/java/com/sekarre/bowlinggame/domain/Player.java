package com.sekarre.bowlinggame.domain;

import com.sekarre.bowlinggame.domain.enums.SpecialScoreType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

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

    private Integer score = 0;

    private Integer lastHitPinsCount;

    private SpecialScoreType specialScoreType;

    private Integer turnOfRound = 1;

    @JoinColumn(name = "game_id")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    private Game game;
}
