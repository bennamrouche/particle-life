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
 * @author alphaben
 */

public class GravityRule implements IRule
{
    private  float gravity;
    Environment env;
    public GravityRule(float gravity)
    {
        this.gravity = gravity;
        this.env =  Environment.getEnvironment();
    }
    
 

    @Override
    public void apply(Particle target, ArrayList<Particle> sources)
    {
        float    fx = 0;
        float    fy = 0;
        float    Force = 0;
      for(Particle B: sources)
      {  
        float dx  = target.x - B.x;
        float dy  = target.y - B.y;
        float d = (float)Math.sqrt(dx * dx + dy * dy);
        if(d > 0 && d < 80)
        {
         Force = this.gravity  * 1f/ d;
         fx += Force * dx;
         fy += Force * dy; 
         
        }
      }

         target.velocityX = (target.velocityX  + fx) * 0.5f;
         target.velocityY = (target.velocityY  + fy) * 0.5f ;
           
         target.x +=  target.velocityX;
         target.y +=  target.velocityY;
         
          if(target.x < 0 || target.x >= env.getWidth())  target.velocityX    = target.velocityX * -1;
          if(target.y < 0 || target.y >= env.getHeight()) target.velocityY    = target.velocityY * -1;     
    }
}


