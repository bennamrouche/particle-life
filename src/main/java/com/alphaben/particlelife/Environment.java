package com.alphaben.particlelife;

import com.alphaben.particlelife.Intializer.Initializer;
import com.alphaben.particlelife.Intializer.KerenlInitializer;
import com.alphaben.particlelife.rules.ColorRule;
import com.alphaben.particlelife.rules.GravityRule;
import static com.alphaben.particlelife.rules.GravityRule.GRAVITY_BOUND;
import com.alphaben.particlelife.rules.IRule;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alphaben
 * @version 1.0
 * 
 */

public class Environment
{
    
    private  Dimension                      size;
    private Initializer                     currentIntializer;
    static private final Random RAND        = new Random();
    

    public void setCurrentIntializer(Initializer currentIntializer) {
        this.currentIntializer = currentIntializer;
    }

    public Initializer getCurrentIntializer() {
        return currentIntializer;
    }
    
    private final List<ParticleGroup> groups = new ArrayList();
    
   
  

    public  Environment(Dimension dim)
    {
       this.size =  dim;
       
       this.currentIntializer = new KerenlInitializer();
    }
    
    public void setSize(Dimension size)
    {
        this.size = size; 
    }
    
    public int getHeight()
    {
        return this.size.height;
    }
    
    public int getWidth()
    {
        return this.size.width;
    }
     
     public List<ParticleGroup> getAllGroups()
     {
        return this.groups;
     }
     
     public void add(ParticleGroup group)
     {
        this.groups.add(group);
     }
     
     public void  reset()
     {
         synchronized (this.groups)
         {
             groups.clear();
         }
         
     } 
     
     
   public  void   addNewGroup()
   {
        
      
           int particleCount    =    RAND.nextInt(Initializer.MAX_PARTICLE_COUNT);
           
           particleCount        =    Math.max(particleCount, Initializer.MIN_PARTICLE_COUNT);
          
           var gravity          =    RAND.nextFloat(-GRAVITY_BOUND, 2 * GRAVITY_BOUND);
           
           int i = RAND.nextInt(Initializer.COLORS.size());
           
           ParticleGroup newGroup  = new ParticleGroup("Group-Random", particleCount, Initializer.COLORS.get(i),this.currentIntializer, this);
            
//           if(RAND.nextInt() % 4 != 0)
                    newGroup.setRule(new GravityRule(Math.abs(gravity), this));
//           else 
//                    newGroup.setRule(new ColorRule(-1,2,30));
            
            this.add(newGroup);
            
       for(ParticleGroup group: this.getAllGroups())
       {
             for(ParticleGroup target: this.getAllGroups())
             {
                 if(group == target){
                    continue;
                 }
                 
                 gravity = RAND.nextFloat(-GRAVITY_BOUND, 2 * GRAVITY_BOUND);
                 IRule rule  = new GravityRule(Math.abs(gravity), this);
                group.addTargetGroup(target, rule);
             }
       }
      
   
   }
     
}
