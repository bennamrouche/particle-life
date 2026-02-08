/*
 * Copyright (c) 2026
 * Author: alphaben
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alphaben.particlelife.rules;

import com.alphaben.particlelife.Particle;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author alphaben
 */
public class ColorRule implements IRule {
   private final  int deltaR;
   private final  int deltaB;
   private final  int deltaG;

    public ColorRule(int deltaR, int deltaB, int deltaG) {
        this.deltaR = deltaR;
        this.deltaB = deltaB;
        this.deltaG = deltaG;
    }


    @Override
    public void apply(Particle target, ArrayList<Particle> sources)
    { 
        int factor  =  sources.size();

        Color color = target.color;
        
        int newR    =  getColor(factor * deltaR, color.getRed());
        int newB    = getColor(factor * deltaB, color.getBlue());
        int newG    = getColor(factor * deltaG, color.getGreen());
        
        target.color =  new Color(newR, newG, newB);
        

    }
    
    
    private int getColor(int deltaTotal, int currentValue) {
      int result =  (currentValue -  deltaTotal) % 255;
      return Math.abs(result);
    
    } 
}
