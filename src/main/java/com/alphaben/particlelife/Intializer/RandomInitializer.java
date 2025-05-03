
package com.alphaben.particlelife.Intializer;


/**
 *
 * @author alphaben
 */

public class RandomInitializer extends Initializer 
{
  
    @Override
    public float nextX(float bound)
    {
        return rand.nextInt((int)bound);
    }

    @Override
    public float nextY(float bound)
    {
          return rand.nextInt((int)bound);
    }


}
