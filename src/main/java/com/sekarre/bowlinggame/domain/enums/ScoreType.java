package com.sekarre.bowlinggame.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScoreType {
    STRIKE(10),
    SPARE(10);

    private final Integer score;
}
