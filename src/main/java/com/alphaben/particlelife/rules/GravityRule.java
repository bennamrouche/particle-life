/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;

import java.util.List;

/**
 *
 * @author alphaben
 */

public class GravityRule implements IRule
{
    private static final float RADIUS = 80f;

    private  float gravity;
    public GravityRule(float gravity)
    {
        this.gravity = gravity;
    }

    @Override
    public float interactionRadius()
    {
        return RADIUS;
    }

    @Override
    public void apply(Particle target, List<Particle> sources)
    {
        float    fx = 0;
        float    fy = 0;
        float    Force = 0;
      for(Particle B: sources)
      {
        float dx  = target.x - B.x;
        float dy  = target.y - B.y;
        float d = (float)Math.sqrt(dx * dx + dy * dy);
        if(d > 0 && d < RADIUS)
        {
         Force = this.gravity  * 1f/ d;
         fx += Force * dx;
         fy += Force * dy;

        }
      }

      target.addForce(fx, fy);
    }
}
