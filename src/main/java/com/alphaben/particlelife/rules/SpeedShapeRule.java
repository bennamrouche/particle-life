package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;

import java.util.List;

/**
 * Continuous shape mutation: no sources needed, just reacts to the target's
 * own current speed - slow particles read as circles, mid-speed as squares,
 * fast ones as triangles. Self-bound.
 *
 * @author alphaben
 */
public class SpeedShapeRule implements IRule
{
    private final float slowThreshold;
    private final float fastThreshold;

    public SpeedShapeRule(float slowThreshold, float fastThreshold)
    {
        this.slowThreshold = slowThreshold;
        this.fastThreshold = fastThreshold;
    }

    @Override
    public float interactionRadius()
    {
        return 0f;
    }

    @Override
    public void apply(Particle target, List<Particle> sources)
    {
        float speed = (float) Math.sqrt(
            target.velocityX * target.velocityX + target.velocityY * target.velocityY);

        if (speed < slowThreshold)
        {
            target.requestShape(Particle.Shape.CIRCLE);
        }
        else if (speed < fastThreshold)
        {
            target.requestShape(Particle.Shape.SQUARE);
        }
        else
        {
            target.requestShape(Particle.Shape.TRIANGLE);
        }
    }
}
