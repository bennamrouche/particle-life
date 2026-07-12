package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;

import java.util.List;

/**
 * Event-driven destroy: bound as "predator group acts on prey group"
 * (sources = predator members, target = a prey particle). Any prey particle
 * within {@code eatRadius} of a predator is destroyed.
 *
 * @author alphaben
 */
public class PredationRule implements IRule
{
    private final float eatRadius;

    public PredationRule(float eatRadius)
    {
        this.eatRadius = eatRadius;
    }

    @Override
    public float interactionRadius()
    {
        return eatRadius;
    }

    @Override
    public void apply(Particle target, List<Particle> sources)
    {
        for (Particle predator : sources)
        {
            float dx = target.x - predator.x;
            float dy = target.y - predator.y;
            float d = (float) Math.sqrt(dx * dx + dy * dy);
            if (d < eatRadius)
            {
                target.destroy();
                return;
            }
        }
    }
}
