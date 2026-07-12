package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;

import java.util.List;

/**
 * Continuous color mutation: each tick, blends the target's color toward
 * the average color of nearby sources by {@code weight}. Works self-bound
 * (a group blends toward its own neighbors) or cross-bound.
 *
 * @author alphaben
 */
public class ColorBlendRule implements IRule
{
    private final float weight;
    private final float radius;

    public ColorBlendRule(float weight)
    {
        this(weight, 50f);
    }

    public ColorBlendRule(float weight, float radius)
    {
        this.weight = weight;
        this.radius = radius;
    }

    @Override
    public float interactionRadius()
    {
        return radius;
    }

    @Override
    public void apply(Particle target, List<Particle> sources)
    {
        for (Particle source : sources)
        {
            if (source == target) continue;
            float dx = target.x - source.x;
            float dy = target.y - source.y;
            float d = (float) Math.sqrt(dx * dx + dy * dy);
            if (d > 0 && d < radius)
            {
                target.requestColorBlend(source.color, weight);
            }
        }
    }
}
