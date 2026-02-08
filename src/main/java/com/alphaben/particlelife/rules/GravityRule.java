package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Environment;
import com.alphaben.particlelife.Particle;

import java.util.ArrayList;

/**
 *
 * @author alphaben
 */

public class GravityRule implements IRule
{
    private final  float gravity;
    public static final    float GRAVITY_BOUND       = 1.5f;

    
    Environment env;
    
    public GravityRule(float gravity, Environment env)
    {
        this.gravity = gravity;
        this.env =  env;
    }
    
 

    @Override
    public void apply(Particle target, ArrayList<Particle> sources)
    {
        float    fx = 0;
        float    fy = 0;
        float    forace;
        
      for(Particle B: sources)
      {  
        float dx  = target.x - B.x;
        float dy  = target.y - B.y;
        
        float distance  = (float)Math.sqrt(dx * dx + dy * dy);
        
        if(distance  > 0 && distance  < 120)
        {
         forace = this.gravity  * 1f / distance ;
         fx += forace * dx;
         fy += forace * dy; 
//          System.err.printf("dx = %f - %f\n", fx, fy);
         
        }
      }

         target.velocityX = (target.velocityX  + fx) * 0.5f;
         target.velocityY = (target.velocityY  + fy) * 0.5f ;
         
         // here must applay the rule not change the movment direction 
           
         target.x +=  target.velocityX;
         target.y +=  target.velocityY;
         
          if(target.x < 0 || target.x >= env.getWidth())  target.velocityX    = target.velocityX * -1;
          if(target.y < 0 || target.y >= env.getHeight()) target.velocityY    = target.velocityY * -1;     
    }
}


