package ru.vsu.cs.course1;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Random;

public class DrawPanel extends JPanel {
    private SolarSystem ss = null;
    public void setSolarSystem(SolarSystem ss) {
        this.ss = ss;
        repaint();
    }
    int Ox = 650;
    int Oy = 300;
    private long solarS = 1210000000;
    private double myWindow = 0.0001032F;
    private double k = myWindow / solarS;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (ss == null)
            return;

        for (SolarSystem.Planet p : ss.planets) {
            PlanetData planetData = counterOfPoints(p.getRadius(), p.getVelocity(), p.getTimeOfCreate());
            g.drawOval((int) planetData.getX() + this.getWidth() / 2 - (p.getDiameter() / 2),
                       (int) planetData.getY() + this.getHeight() / 2 - (p.getDiameter() / 2),
                          (int) (p.getDiameter()),
                          (int) (p.getDiameter()));
            g.drawOval((this.getWidth() / 2 - 138 / 2), (this.getHeight() / 2 - (138 / 2)), 138, 138);
        }

    }
    private PlanetData counterOfPoints(int radius, int v, long time) {
        Date now = new Date();
        long nowtime = (int) (now.getTime() / 1000);

        double angle = ((double) v / (radius + 50)) * (nowtime - time);
        double x = (Math.cos(angle) * (radius + 50));
        double y = (Math.sin(angle) * (radius + 50));

        return new PlanetData(x, y);
    }
}