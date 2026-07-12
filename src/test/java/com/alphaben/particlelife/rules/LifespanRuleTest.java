package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LifespanRuleTest
{
    @Test
    void doesNotDestroyBeforeMaxAge()
    {
        Particle p = new Particle(0, 0, Color.WHITE, 0, 0);
        p.age = 5;

        new LifespanRule(10).apply(p, List.of());

        assertFalse(p.isDestroyed());
    }

    @Test
    void destroysPastMaxAge()
    {
        Particle p = new Particle(0, 0, Color.WHITE, 0, 0);
        p.age = 11;

        new LifespanRule(10).apply(p, List.of());

        assertTrue(p.isDestroyed());
    }
}
