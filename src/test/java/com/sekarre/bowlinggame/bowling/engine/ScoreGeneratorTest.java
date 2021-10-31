package com.sekarre.bowlinggame.bowling.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreGeneratorTest {

    ScoreGenerator scoreGenerator = new ScoreGenerator();

    @Test
    void should_generate_random_pins_hit() {
        //given
        Integer lastHitPinsCount = 9;

        //when
        Integer result = scoreGenerator.generateRandomPinsHit(lastHitPinsCount);

        //then
        assertTrue(result < 2);
    }
}