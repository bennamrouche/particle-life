package com.alphaben.particlelife.Intializer;

import com.alphaben.particlelife.Environment;
import com.alphaben.particlelife.ParticleGroup;
import com.alphaben.particlelife.rules.GravityRule;
import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alphaben
 */
public  abstract  class Initializer {
    private static final     int MAX_GROUPS_COUNT    =     14;
   private static final     int MAX_PARTICLE_COUNT = 400;
   private static final     int MIN_GROUPS_COUNT =  5;
   private static final     int MIN_PARTICLE_COUNT = 150;
   private static final  float GRAVITY_BOUND = 0.75f;
   private static final List<Color> COLORS = List.of(
    new Color(255, 75, 75),     // Neon Red
    new Color(255, 140, 0),     // Deep Orange
    new Color(255, 255, 102),   // Light Yellow
    new Color(0, 255, 127),     // Mint Green
    new Color(0, 191, 255),     // Sky Blue
    new Color(30, 144, 255),    // Dodger Blue
    new Color(138, 43, 226),    // Electric Purple
    new Color(186, 85, 211),    // Orchid
    new Color(255, 105, 180),   // Hot Pink
    new Color(255, 20, 147),    // Deep Pink
    new Color(0, 255, 255),     // Cyan
    new Color(173, 216, 230),   // Light Blue
    new Color(0, 255, 0),       // Pure Green
    new Color(255, 255, 255)    // White (always a good contrast on black)
);

   protected final Random rand = new Random();
    
    public  void newEnvironment()
    {
        Environment env = Environment.getEnvironment();
        env.reset();
        int count = rand.nextInt(MAX_GROUPS_COUNT);
        count= Math.max(count, MIN_GROUPS_COUNT);
        
        for(int i = 0;i < count; i++)
        {
           int particleCount    = rand.nextInt(MAX_PARTICLE_COUNT);
           particleCount        = Math.max(particleCount, MIN_PARTICLE_COUNT);
           float gravity =  rand.nextFloat(-GRAVITY_BOUND, 2 * GRAVITY_BOUND);
           ParticleGroup group  = new ParticleGroup(String.format("Group-%d",i),    particleCount, COLORS.get(i % MAX_GROUPS_COUNT),this);
           group.setRule(new GravityRule(gravity));    
            env.add(group);
            
        }
        
       for(ParticleGroup group: env.getAllGroups())
       {
             for(ParticleGroup target: env.getAllGroups())
             {
                 if(group == target){
                    continue;
                 }
                 
                 float gravity =  rand.nextFloat(-GRAVITY_BOUND, 2 * GRAVITY_BOUND);
                group.addTargetGroup(target, new GravityRule(gravity));
             }
       }
      
    }

   public  void   addNewGroup()
   {
        Environment env = Environment.getEnvironment();
      
           int particleCount    =    rand.nextInt(MAX_PARTICLE_COUNT);
           particleCount        =    Math.max(particleCount, MIN_PARTICLE_COUNT);
           float gravity        =    rand.nextFloat(-GRAVITY_BOUND, 2 * GRAVITY_BOUND);
           int i = rand.nextInt(MAX_GROUPS_COUNT);
           
           ParticleGroup newGroup  = new ParticleGroup("Group-Random",particleCount, COLORS.get(i), this);
            newGroup.setRule(new GravityRule(Math.abs(gravity)));
            env.add(newGroup);
            
 
       for(ParticleGroup group: env.getAllGroups())
       {
             for(ParticleGroup target: env.getAllGroups())
             {
                 if(group == target){
                    continue;
                 }
                 
                 gravity =  rand.nextFloat(-GRAVITY_BOUND, 2 * GRAVITY_BOUND);
                group.addTargetGroup(target, new GravityRule(gravity));
             }
       }
      
   
   }

     
     public abstract  float  nextX(float bound);
     public abstract  float  nextY(float bound);
       
}
