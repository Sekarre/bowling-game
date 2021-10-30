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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private Integer numberInQueue;

    private String name;

    private Integer score;

    private SpecialScoreType specialScoreType;

    private Integer turnOfRound;

    @JoinColumn(name = "game_id")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Game game;
}
