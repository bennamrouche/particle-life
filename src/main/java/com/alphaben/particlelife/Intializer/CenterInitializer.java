
package com.alphaben.particlelife.Intializer;


/**
 *
 * @author alphaben
 */

public class CenterInitializer extends Initializer 
{
  
    @Override
    public float nextX(float bound)
    {
        return rand.nextInt((int)bound);
    }

    @Override
    public float nextY(float bound)
    {
          return (bound/2) - 50 +  rand.nextInt(100);
    }


}
