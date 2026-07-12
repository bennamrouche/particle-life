package com.alphaben.particlelife;

import com.alphaben.particlelife.Intializer.Initializer;
import com.alphaben.particlelife.rules.IRule;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alphaben
 * @version 1.0
 * @since  2025
 */

public class ParticleGroup
{
    /** A rule bound on this group is applied to targetGroup's members, using this group's members as sources. */
    public record RuleBinding(ParticleGroup targetGroup, IRule rule) {}

    private final String   name;
    private final List<Particle> members = new ArrayList<>();
    private final List<RuleBinding> bindings = new ArrayList<>();

    private final Environment env = Environment.getEnvironment();


        public  ParticleGroup(String name, int count, Color color,Initializer init)
   {
       for(int i=0; i <count; i++)
       {
           float  x = init.nextX(env.getWidth());
           float  y = init.nextY(env.getHeight());

           members.add(new Particle(x, y, color,0 ,0));
       }
       this.name = name;
   }

   public List<Particle> getMembers()
   {
       return members;
   }

   public List<RuleBinding> getBindings()
   {
       return bindings;
   }

   /** Registers rule to run each tick against targetGroup's members, using this group's members as sources. */
   public void bind(ParticleGroup targetGroup, IRule rule)
   {
       bindings.add(new RuleBinding(targetGroup, rule));
   }
}
