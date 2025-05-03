package com.alphaben.particlelife.Intializer;

/**
 *
 * @author alphaben
 */

public class IntializerManager
{
    private Initializer currentIntializer;
    private final static IntializerManager mangaer = new IntializerManager();

    private  IntializerManager () {
    }
    
     public   static IntializerManager getManager()
     {
         return mangaer;
     }
    
   public Initializer getCurrentIitializer()
   {
       return  currentIntializer;
   }
   
   public void  setCurrentIitializer(Initializer init)
   {
       this.currentIntializer = init;
   } 
   
}
