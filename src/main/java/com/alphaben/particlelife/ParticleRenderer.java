package com.alphaben.particlelife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Draws a particle with a soft halo, shaped according to {@link Particle.Shape}.
 * Kept out of {@link View} so the view stays focused on Swing wiring.
 *
 * @author alphaben
 */
final class ParticleRenderer
{
    private ParticleRenderer() {}

    static void draw(Graphics g, Particle particle)
    {
        Color src = particle.color;
        int x = (int) particle.x;
        int y = (int) particle.y;

        g.setColor(new Color(src.getRed(), src.getGreen(), src.getBlue(), 80));
        drawShape(g, particle.shape, x, y, 8);

        g.setColor(src);
        drawShape(g, particle.shape, x, y, 4);
    }

    private static void drawShape(Graphics g, Particle.Shape shape, int cx, int cy, int size)
    {
        int half = size / 2;
        switch (shape)
        {
            case SQUARE   -> g.fillRect(cx - half, cy - half, size, size);
            case TRIANGLE -> g.fillPolygon(triangle(cx, cy, size));
            default       -> g.fillOval(cx - half, cy - half, size, size);
        }
    }

    private static Polygon triangle(int cx, int cy, int size)
    {
        int half = size / 2;
        Polygon triangle = new Polygon();
        triangle.addPoint(cx, cy - half);
        triangle.addPoint(cx - half, cy + half);
        triangle.addPoint(cx + half, cy + half);
        return triangle;
    }
}
