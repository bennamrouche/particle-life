package com.alphaben.particlelife.engine;

import com.alphaben.particlelife.Particle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Uniform grid over a fixed set of particles, rebuilt once per tick per
 * source group. Turns "find particles within radius r" from an O(n) scan
 * of the whole group into a scan of the handful of nearby cells.
 *
 * @author alphaben
 */
public class SpatialGrid
{
    private final float cellSize;
    private final Map<Long, List<Particle>> cells = new HashMap<>();

    private SpatialGrid(float cellSize)
    {
        this.cellSize = cellSize;
    }

    public static SpatialGrid of(List<Particle> particles, float cellSize)
    {
        SpatialGrid grid = new SpatialGrid(Math.max(cellSize, 1f));
        for (Particle p : particles)
        {
            grid.cells.computeIfAbsent(grid.cellKey(p.x, p.y), k -> new ArrayList<>()).add(p);
        }
        return grid;
    }

    public List<Particle> near(float x, float y, float radius)
    {
        if (radius <= 0) return List.of();

        List<Particle> result = new ArrayList<>();
        int cx = (int) Math.floor(x / cellSize);
        int cy = (int) Math.floor(y / cellSize);
        int reach = (int) Math.ceil(radius / cellSize);

        for (int dx = -reach; dx <= reach; dx++)
        {
            for (int dy = -reach; dy <= reach; dy++)
            {
                List<Particle> bucket = cells.get(packKey(cx + dx, cy + dy));
                if (bucket != null) result.addAll(bucket);
            }
        }
        return result;
    }

    private long cellKey(float x, float y)
    {
        return packKey((int) Math.floor(x / cellSize), (int) Math.floor(y / cellSize));
    }

    private static long packKey(int cx, int cy)
    {
        return (((long) cx) << 32) ^ (cy & 0xffffffffL);
    }
}
