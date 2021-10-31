package com.sekarre.bowlinggame.domain.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameProperties {
    public static final Integer MAX_PINS = 10;
    public static final Integer FIRST_TURN = 1;
    public static final Integer LAST_TURN = 2;
    public static final Integer MAX_ROUND = 10;
    public static final Integer MAX_PLAYERS = 40;
}
