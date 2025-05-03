package com.alphaben.particlelife;

import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author alphaben
 * @version 1.0
 * 
 */

public class Environment
{
    
    Dimension size = new Dimension(500, 500);
    private ArrayList<ParticleGroup> groups   = new ArrayList();
    private static final Environment instance = new Environment();
    
    public static Environment getEnvironment()
    {
        return instance;
    }
    
    private  Environment()
    {
       
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
     
     public ArrayList<ParticleGroup> getAllGroups()
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
}
