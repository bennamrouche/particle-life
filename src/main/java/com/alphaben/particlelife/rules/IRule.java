package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;
import java.util.List;

/**
 *
 * @author alphaben
 * @since 2025
 *
 * Rules run during the accumulate phase of a tick: apply() must only write
 * to the target's pending accumulators (addForce/requestColorBlend/
 * requestShape/destroy) and never commit state directly. The engine
 * integrates every particle exactly once, after all bindings have run.
 */
public interface  IRule
{
   public void apply(Particle target, List<Particle> sources);

   /** Bounding radius used by the spatial grid to gather candidate sources. */
   default float interactionRadius()
   {
       return 80f;
   }
}
