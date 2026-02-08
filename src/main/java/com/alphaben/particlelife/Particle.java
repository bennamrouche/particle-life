
package com.alphaben.particlelife;

import java.awt.Color;

/**
 *
 * @author alphaben
 */
public class Particle
{

    public Color       color;
    public float x;
    public float y;
    public float velocityX;
    public float velocityY;
    
    public Particle( float x, float y, Color color, float velocityX, float velocityY) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
    


}
