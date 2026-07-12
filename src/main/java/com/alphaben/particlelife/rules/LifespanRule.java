package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;

import java.util.List;

/**
 * Event-driven destroy: ignores sources, destroys the target once it has
 * lived past {@code maxAge} ticks. Self-bound.
 *
 * @author alphaben
 */
public class LifespanRule implements IRule
{
    private final int maxAge;

    public LifespanRule(int maxAge)
    {
        this.maxAge = maxAge;
    }

    @Override
    public float interactionRadius()
    {
        return 0f;
    }

    @Override
    public void apply(Particle target, List<Particle> sources)
    {
        if (target.age > maxAge)
        {
            target.destroy();
        }
    }
}
