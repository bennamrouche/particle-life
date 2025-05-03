
package com.alphaben.particlelife.Intializer;

/**
 *
 * @author alphaben
 */

public class BorderInitializer extends Initializer 
{
  
 private int pointer = 0;
private static final int SIDES = 4;

@Override
public float nextX(float bound) {
    int side = pointer % SIDES;
    switch (side) {
        case 0, 2 -> { return rand.nextInt((int) bound); }   // top or bottom
        case 1 -> { return bound - 1; }                      // right
        case 3 -> { return 0; }                              // left
    }
        return 0;
}

@Override
public float nextY(float bound)
{
    int side = pointer % SIDES;
    pointer++; 
    switch (side) {
        case 0 -> { return bound - 1; }                      // bottom
        case 1, 3 -> { return rand.nextInt((int) bound); }   // right or left
        case 2 -> { return 0; }                              // top
    }
    return 0;
}

}
