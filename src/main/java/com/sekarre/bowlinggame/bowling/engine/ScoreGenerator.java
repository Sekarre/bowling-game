package com.sekarre.bowlinggame.bowling.engine;

import com.sekarre.bowlinggame.domain.enums.GameMode;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Component;

import static com.sekarre.bowlinggame.domain.enums.GameProperties.GAME_MODE;
import static com.sekarre.bowlinggame.domain.enums.GameProperties.MAX_PINS;

@Component
public class ScoreGenerator {

    public Integer generateRandomPinsHit(Integer lastHitPinsCount) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

        if (GAME_MODE.equals(GameMode.ZERO)) {
            return 0;
        }

        if (GAME_MODE.equals(GameMode.MAX)) {
            return 10;
        }

        return randomDataGenerator.nextInt(0, MAX_PINS - lastHitPinsCount);
    }
}
