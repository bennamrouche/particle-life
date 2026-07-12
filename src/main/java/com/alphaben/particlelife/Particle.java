
package com.alphaben.particlelife;

import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author alphaben
 */
public class Particle
{
    public enum Shape { CIRCLE, SQUARE, TRIANGLE }

    public Color color;
    public float x;
    public float y;
    public float velocityX;
    public float velocityY;
    public Shape shape = Shape.CIRCLE;
    public int age = 0;

    private float pendingForceX;
    private float pendingForceY;
    private float blendR;
    private float blendG;
    private float blendB;
    private float blendWeight;
    private Shape pendingShape;
    private boolean destroyed;

    public Particle( float x, float y, Color color, float velocityX, float velocityY) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * Rules call this during the accumulate phase; nothing is committed
     * until {@link #settle} runs, so contributions from every rule that
     * touches this particle in a tick are summed rather than overwritten.
     */
    public void addForce(float fx, float fy)
    {
        pendingForceX += fx;
        pendingForceY += fy;
    }

    public void requestColorBlend(Color target, float weight)
    {
        if (weight <= 0) return;
        blendR += target.getRed() * weight;
        blendG += target.getGreen() * weight;
        blendB += target.getBlue() * weight;
        blendWeight += weight;
    }

    /** First request in a tick wins (rule bindings run in deterministic order). */
    public void requestShape(Shape shape)
    {
        if (pendingShape == null)
        {
            pendingShape = shape;
        }
    }

    public void destroy()
    {
        destroyed = true;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }

    /**
     * Commits everything rules accumulated this tick: integrates the summed
     * force into velocity/position exactly once, blends color, applies any
     * requested shape change, ages the particle, then resets accumulators.
     */
    /** Called once per tick by {@link com.alphaben.particlelife.engine.Simulation}; not for rules to call directly. */
    public void settle(Dimension bounds, float damping, float colorBlendRate)
    {
        velocityX = (velocityX + pendingForceX) * damping;
        velocityY = (velocityY + pendingForceY) * damping;

        x += velocityX;
        y += velocityY;

        if (x < 0 || x >= bounds.width)  velocityX = velocityX * -1;
        if (y < 0 || y >= bounds.height) velocityY = velocityY * -1;

        if (blendWeight > 0)
        {
            float targetR = blendR / blendWeight;
            float targetG = blendG / blendWeight;
            float targetB = blendB / blendWeight;
            int r = clamp255(color.getRed()   + (targetR - color.getRed())   * colorBlendRate);
            int g = clamp255(color.getGreen() + (targetG - color.getGreen()) * colorBlendRate);
            int b = clamp255(color.getBlue()  + (targetB - color.getBlue())  * colorBlendRate);
            color = new Color(r, g, b);
        }

        if (pendingShape != null)
        {
            shape = pendingShape;
        }

        age++;
        resetAccumulators();
    }

    void resetAccumulators()
    {
        pendingForceX = 0;
        pendingForceY = 0;
        blendR = 0;
        blendG = 0;
        blendB = 0;
        blendWeight = 0;
        pendingShape = null;
    }

    private static int clamp255(float v)
    {
        return Math.max(0, Math.min(255, Math.round(v)));
    }
}
