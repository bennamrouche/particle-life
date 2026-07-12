package com.alphaben.particlelife.engine;

import com.alphaben.particlelife.Environment;
import com.alphaben.particlelife.Intializer.Initializer;
import com.alphaben.particlelife.Particle;
import com.alphaben.particlelife.ParticleGroup;
import com.alphaben.particlelife.rules.GravityRule;
import com.alphaben.particlelife.rules.PredationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulationTest
{
    private static final Initializer NULL_INIT = new Initializer()
    {
        @Override public float nextX(float bound) { return 0; }
        @Override public float nextY(float bound) { return 0; }
    };

    private final Environment env = Environment.getEnvironment();
    private final Simulation simulation = new Simulation();

    @BeforeEach
    void resetEnvironment()
    {
        env.reset();
    }

    private ParticleGroup emptyGroup(String name)
    {
        ParticleGroup group = new ParticleGroup(name, 0, Color.WHITE, NULL_INIT);
        env.add(group);
        return group;
    }

    @Test
    void forcesFromMultipleSourceGroupsAreSummedNotOverwritten()
    {
        ParticleGroup target = emptyGroup("target");
        target.getMembers().add(new Particle(50, 50, Color.WHITE, 0, 0));

        ParticleGroup sourceA = emptyGroup("sourceA");
        sourceA.getMembers().add(new Particle(40, 50, Color.WHITE, 0, 0));
        sourceA.bind(target, new GravityRule(4f));

        ParticleGroup sourceB = emptyGroup("sourceB");
        sourceB.getMembers().add(new Particle(50, 40, Color.WHITE, 0, 0));
        sourceB.bind(target, new GravityRule(6f));

        simulation.step(env);

        // Under the old design, whichever group's update() ran last would
        // overwrite the other's contribution instead of summing them.
        Particle result = target.getMembers().get(0);
        assertEquals(2.0f, result.velocityX, 0.01f, "sourceA's force should still be present");
        assertEquals(3.0f, result.velocityY, 0.01f, "sourceB's force should still be present");
    }

    @Test
    void destroyedParticlesAreRemovedAfterStep()
    {
        ParticleGroup prey = emptyGroup("prey");
        prey.getMembers().add(new Particle(0, 0, Color.WHITE, 0, 0));

        ParticleGroup predator = emptyGroup("predator");
        predator.getMembers().add(new Particle(0, 0, Color.WHITE, 0, 0));
        predator.bind(prey, new PredationRule(15f));

        simulation.step(env);

        assertTrue(prey.getMembers().isEmpty());
    }
}
