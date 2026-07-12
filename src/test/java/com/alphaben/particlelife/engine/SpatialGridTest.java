package com.alphaben.particlelife.engine;

import com.alphaben.particlelife.Particle;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpatialGridTest
{
    @Test
    void nearReturnsOnlyParticlesWithinRadius()
    {
        Particle close = new Particle(10, 10, Color.WHITE, 0, 0);
        Particle far   = new Particle(500, 500, Color.WHITE, 0, 0);

        SpatialGrid grid = SpatialGrid.of(List.of(close, far), 50f);

        List<Particle> found = grid.near(10, 10, 30f);

        assertTrue(found.contains(close));
        assertFalse(found.contains(far));
    }

    @Test
    void zeroRadiusReturnsNoNeighbors()
    {
        Particle p = new Particle(0, 0, Color.WHITE, 0, 0);
        SpatialGrid grid = SpatialGrid.of(List.of(p), 50f);

        assertTrue(grid.near(0, 0, 0f).isEmpty());
    }
}
