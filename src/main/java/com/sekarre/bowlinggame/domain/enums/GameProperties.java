package com.sekarre.bowlinggame.domain.enums;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
public class GameProperties {
    public static final Integer MAX_PINS = 10;
    public static final Integer FIRST_TURN = 1;
    public static final Integer LAST_TURN = 2;
}
