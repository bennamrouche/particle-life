package com.alphaben.particlelife.engine;

import com.alphaben.particlelife.Environment;
import com.alphaben.particlelife.Particle;
import com.alphaben.particlelife.ParticleGroup;
import com.alphaben.particlelife.ParticleGroup.RuleBinding;

import java.awt.Dimension;
import java.util.List;

/**
 * Drives one simulation tick in three phases so that every rule
 * contribution to a particle is summed before that particle's
 * velocity/position/color/shape are committed exactly once:
 *
 * 1. accumulate - every rule binding writes only to pending accumulators
 * 2. integrate  - each particle is settled exactly once
 * 3. cull       - particles destroyed this tick are removed
 *
 * @author alphaben
 */
public class Simulation
{
    private static final float DAMPING = 0.5f;
    private static final float COLOR_BLEND_RATE = 0.2f;

    public void step(Environment env)
    {
        Dimension bounds = new Dimension(env.getWidth(), env.getHeight());
        List<ParticleGroup> groups = env.getAllGroups();

        for (ParticleGroup group : groups)
        {
            SpatialGrid grid = SpatialGrid.of(group.getMembers(), maxRadius(group));

            for (RuleBinding binding : group.getBindings())
            {
                float radius = binding.rule().interactionRadius();
                for (Particle target : binding.targetGroup().getMembers())
                {
                    List<Particle> sources = grid.near(target.x, target.y, radius);
                    binding.rule().apply(target, sources);
                }
            }
        }

        for (ParticleGroup group : groups)
        {
            for (Particle p : group.getMembers())
            {
                p.settle(bounds, DAMPING, COLOR_BLEND_RATE);
            }
        }

        for (ParticleGroup group : groups)
        {
            group.getMembers().removeIf(Particle::isDestroyed);
        }
    }

    private static float maxRadius(ParticleGroup group)
    {
        float max = 1f;
        for (RuleBinding binding : group.getBindings())
        {
            max = Math.max(max, binding.rule().interactionRadius());
        }
        return max;
    }
}
