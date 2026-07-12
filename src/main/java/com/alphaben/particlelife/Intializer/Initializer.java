package com.alphaben.particlelife.Intializer;

import com.alphaben.particlelife.Environment;
import com.alphaben.particlelife.ParticleGroup;
import com.alphaben.particlelife.rules.ColorBlendRule;
import com.alphaben.particlelife.rules.GravityRule;
import com.alphaben.particlelife.rules.LifespanRule;
import com.alphaben.particlelife.rules.PredationRule;
import com.alphaben.particlelife.rules.SpeedShapeRule;
import java.awt.Color;
import java.util.ArrayList;
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

   private static final float COLOR_BLEND_WEIGHT  = 0.05f;
   private static final float COLOR_BLEND_RADIUS  = 50f;
   private static final float SPEED_SHAPE_SLOW    = 0.5f;
   private static final float SPEED_SHAPE_FAST    = 2.0f;
   private static final int   LIFESPAN_MAX_AGE     = 3000;
   private static final float PREDATION_EAT_RADIUS = 15f;
   private static final float PREDATION_CHANCE     = 0.2f;

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

        List<ParticleGroup> created = new ArrayList<>();
        for(int i = 0;i < count; i++)
        {
           int particleCount    = rand.nextInt(MAX_PARTICLE_COUNT);
           particleCount        = Math.max(particleCount, MIN_PARTICLE_COUNT);
           ParticleGroup group  = new ParticleGroup(String.format("Group-%d",i),    particleCount, COLORS.get(i % MAX_GROUPS_COUNT),this);
           group.bind(group, new GravityRule(randomGravity()));
           attachCosmeticRules(group);
           maybeAddPredation(group, created);
           created.add(group);
           env.add(group);
        }

        wireAllGravityPairs(env.getAllGroups());
    }

   public  void   addNewGroup()
   {
        Environment env = Environment.getEnvironment();

           int particleCount    =    rand.nextInt(MAX_PARTICLE_COUNT);
           particleCount        =    Math.max(particleCount, MIN_PARTICLE_COUNT);
           int i = rand.nextInt(MAX_GROUPS_COUNT);

           List<ParticleGroup> existing = new ArrayList<>(env.getAllGroups());

           ParticleGroup newGroup  = new ParticleGroup("Group-Random",particleCount, COLORS.get(i), this);
           newGroup.bind(newGroup, new GravityRule(Math.abs(randomGravity())));
           attachCosmeticRules(newGroup);
           maybeAddPredation(newGroup, existing);
           env.add(newGroup);

           wireGravityForNewGroup(newGroup, existing);
   }

   /** Always-on cosmetic rules every group gets: color drifts toward neighbors, shape reacts to speed, particles age out. */
   private void attachCosmeticRules(ParticleGroup group)
   {
       group.bind(group, new ColorBlendRule(COLOR_BLEND_WEIGHT, COLOR_BLEND_RADIUS));
       group.bind(group, new SpeedShapeRule(SPEED_SHAPE_SLOW, SPEED_SHAPE_FAST));
       group.bind(group, new LifespanRule(LIFESPAN_MAX_AGE));
   }

   /** With PREDATION_CHANCE odds, the new group becomes a predator of one existing group (single binding, no repeats). */
   private void maybeAddPredation(ParticleGroup newGroup, List<ParticleGroup> existingGroups)
   {
       if (existingGroups.isEmpty() || rand.nextFloat() > PREDATION_CHANCE) return;
       ParticleGroup prey = existingGroups.get(rand.nextInt(existingGroups.size()));
       newGroup.bind(prey, new PredationRule(PREDATION_EAT_RADIUS));
   }

   private float randomGravity()
   {
       return rand.nextFloat(-GRAVITY_BOUND, 2 * GRAVITY_BOUND);
   }

   /** Wires every ordered pair once - used when a whole new environment is built from scratch. */
   private void wireAllGravityPairs(List<ParticleGroup> groups)
   {
       for(ParticleGroup group: groups)
       {
             for(ParticleGroup target: groups)
             {
                 if(group == target){
                    continue;
                 }
                group.bind(target, new GravityRule(randomGravity()));
             }
       }
   }

   /** Wires only the new group's relationships to already-existing groups, both directions - avoids re-touching (and duplicating bindings on) pairs that already exist. */
   private void wireGravityForNewGroup(ParticleGroup newGroup, List<ParticleGroup> existingGroups)
   {
       for(ParticleGroup other: existingGroups)
       {
           if(other == newGroup) continue;
           newGroup.bind(other, new GravityRule(randomGravity()));
           other.bind(newGroup, new GravityRule(randomGravity()));
       }
   }


     public abstract  float  nextX(float bound);
     public abstract  float  nextY(float bound);

}
