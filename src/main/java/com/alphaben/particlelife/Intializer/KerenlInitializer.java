
package com.alphaben.particlelife.Intializer;


/**
 *
 * @author alphaben
 */

public class KerenlInitializer extends Initializer 
{
  
    final static int KERNEL_RADIUS = 100;
    @Override
    public float nextX(float bound)
    {
        float  tmp   = bound / 2f  - KERNEL_RADIUS / 2f;
        
        return tmp + rand.nextInt((int)KERNEL_RADIUS);
    }

    @Override
    public float nextY(float bound)
    {
         float  tmp   = bound / 2f  - KERNEL_RADIUS / 2f;
        return tmp + rand.nextInt((int)KERNEL_RADIUS);
    }


}
