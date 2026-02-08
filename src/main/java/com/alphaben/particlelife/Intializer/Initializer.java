package com.alphaben.particlelife.Intializer;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alphaben
 */
public  abstract  class Initializer  {
   public static final      int MAX_GROUPS_COUNT         =   50;

   private static final     int MIN_GROUPS_COUNT        =    20;
    
   public static final     int MAX_PARTICLE_COUNT       =    150;

   public static final     int MIN_PARTICLE_COUNT       =    80;
   
  
   public static final List<Color> COLORS = List.of(
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
         
     public abstract  float  nextX(float bound);
     public abstract  float  nextY(float bound);
       
}
