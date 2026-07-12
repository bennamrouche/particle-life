package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PredationRuleTest
{
    @Test
    void destroysWhenPredatorWithinRadius()
    {
        Particle prey = new Particle(0, 0, Color.WHITE, 0, 0);
        Particle predator = new Particle(5, 0, Color.WHITE, 0, 0);

        new PredationRule(10f).apply(prey, List.of(predator));

        assertTrue(prey.isDestroyed());
    }

    @Test
    void leavesUntouchedWhenPredatorOutsideRadius()
    {
        Particle prey = new Particle(0, 0, Color.WHITE, 0, 0);
        Particle predator = new Particle(50, 0, Color.WHITE, 0, 0);

        new PredationRule(10f).apply(prey, List.of(predator));

        assertFalse(prey.isDestroyed());
    }
}
