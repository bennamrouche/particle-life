/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Environment;
import com.alphaben.particlelife.Particle;
import java.util.ArrayList;

/**
 *
 * @author ebennamr
 */
public class KillerRule  implements IRule {
    
     Environment env = Environment.getEnvironment();
 
     @Override
     public void apply(Particle target, ArrayList<Particle> sources)
    {
        float    fx = 0;
        float    fy = 0;
        float    distance  = Float.MAX_VALUE;
        
       Particle  closer = null;
       
       for(Particle particle : sources)
      {  
        float dx  = target.x - particle.x;
        float dy  = target.y - particle.y;
        float d = (float)Math.sqrt(dx * dx + dy * dy);
        
        if(closer == null || distance > d  ) {
            closer = particle;
        }
             
      }

         target.velocityX = (target.velocityX  + fx) * 0.5f;
         target.velocityY = (target.velocityY  + fy) * 0.5f ;
           
         target.x +=  target.velocityX;
         target.y +=  target.velocityY;
    
    }
        
}
