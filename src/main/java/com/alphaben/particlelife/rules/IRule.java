package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;
import java.util.ArrayList;

/**
 *
 * @author alphaben
 * @since 2025
 *  
 */
public interface  IRule
{
   public void apply(Particle target, ArrayList<Particle> sources); 
}
