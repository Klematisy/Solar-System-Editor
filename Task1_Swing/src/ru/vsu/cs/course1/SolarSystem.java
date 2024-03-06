package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
    public static class Planet {
        private String name;
        private int velocity;
        private int radius;
        private int diameter;
        private long timeOfCreate;
        //String name;

        public Planet(String name, int velocity, int radius, int diameter, long timeOfCreate) {
            this.name = name;
            this.velocity = velocity;
            this.radius = radius;
            this.diameter = diameter;
            this.timeOfCreate = timeOfCreate;
        }

        public int getDiameter() {return diameter;}
        public int getVelocity(){return velocity;}
        public long getTimeOfCreate() {return timeOfCreate;}
        public int getRadius() {return radius;}
        public String getName() {return name;}

        public void setVelocity(int velocity) {
            this.velocity = velocity;
        }

        public void setDiameter(int diameter) {
            this.diameter = diameter;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTimeOfCreate(long timeOfCreate) {
            this.timeOfCreate = timeOfCreate;
        }
    }
    public static List<Planet> planets = new ArrayList<>();
}
