package com.alphaben.particlelife;

import com.alphaben.particlelife.Intializer.Initializer;
import com.alphaben.particlelife.rules.IRule;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alphaben
 * @version 1.0
 * @since 2025
 */
public class ParticleGroup {

    private final String    name;
    private IRule           selfRule;
    
        ArrayList<Particle> members = new ArrayList<>();

    Map<ParticleGroup, IRule> targetGroups = new HashMap<>();

    private final Environment env = Environment.getEnvironment();

    public ParticleGroup(String name, int count, Color color, Initializer init) {
        for (int i = 0; i < count; i++) {
            float x = init.nextX(env.getWidth());
            float y = init.nextY(env.getHeight());

            members.add(new Particle(x, y, color, 0, 0));
        }
        this.name = name;
    }

    public void update() {
        updateMembers();
        for (Map.Entry<ParticleGroup, IRule> pair : targetGroups.entrySet()) {
            for (Particle particle : pair.getKey().members) {
                pair.getValue().apply(particle, members);
            }
        }
    }

    private void updateMembers() {
        if (selfRule != null) {
            for (Particle target : members) {
                selfRule.apply(target, members);
            }
        }
    }

    public void addTargetGroup(ParticleGroup group, IRule rule) {
        targetGroups.put(group, rule);
    }

    public void removeTargetGroup(ParticleGroup group) {
        targetGroups.remove(group);
    }

    public void setRule(IRule rule) {
        this.selfRule = rule;
    }
}
