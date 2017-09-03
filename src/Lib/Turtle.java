package Lib; /******************************************************************************
 *  Compilation:  javac Lib.Turtle.java
 *  Execution:    java Lib.Turtle
 *
 *  Data type for turtle graphics using standard draw.
 *
 ******************************************************************************/

import java.awt.*;

public class Turtle {
    private double x, y;     // turtle is at (x, y)
    private double angle;    // facing this many degrees counterclockwise from the x-axis

    // start at (x0, y0), facing a0 degrees counterclockwise from the x-axis
    public Turtle(double x0, double y0, double a0) {
        x = x0;
        y = y0;
        angle = a0;
    }

    // rotate orientation delta degrees counterclockwise
    public void turnLeft(double delta) {
        angle += delta;
    }

    // move forward the given amount, with the pen down
    public void goForward(double step) {
        double oldx = x;
        double oldy = y;
        x += step * Math.cos(Math.toRadians(angle));
        y += step * Math.sin(Math.toRadians(angle));
        StdDraw.line(oldx, oldy, x, y);
    }

    // pause t milliseconds
    public void pause(int t) {
        StdDraw.show(t);
    }


    public void setPenColor(Color color) {
        StdDraw.setPenColor(color);
    }

    public void setPenRadius(double radius) {
        StdDraw.setPenRadius(radius);
    }

    public void setCanvasSize(int width, int height) {
        StdDraw.setCanvasSize(width, height);
    }

    public void setXscale(double min, double max) {
        StdDraw.setXscale(min, max);
    }

    public void setYscale(double min, double max) {
        StdDraw.setYscale(min, max);
    }




            // sample client for testing

    public static void main(String[] args) {
     /*
        double x0 = 0.5;
        double y0 = 0.0;
        double a0 = 60.0;
        double step = Math.sqrt(3)/2;
        Lib.Turtle turtle  = new Lib.Turtle(x0, y0, a0);
        turtle.goForward(step);
        turtle.turnLeft(120.0);
        turtle.goForward(step);
        turtle.turnLeft(120.0);
        turtle.goForward(step);
        turtle.turnLeft(120.0);
        */

        Thread t2 = new Thread(){
            public void run() {
                StdDraw.setPenColor(StdDraw.BLUE);
                System.out.println("2 is on");
                double x0 = 0.5;
                double y0 = 0.0;
                StdRandom.setSeed(System.nanoTime());
                double a0 = StdRandom.uniform(0, 360);
                double step = 0.001;
                Turtle turtle = new Turtle(0.5, 0.5, a0);
               // setPenColor(Color.GREEN);
                for (int i = 0; i < 100000; i++) {
                    StdDraw.setPenColor(StdDraw.BLUE);
                    turtle.goForward(step);
                    turtle.turnLeft(StdRandom.uniform(0, 360));
                }
            }
        };
        Thread t1 = new Thread() {
            public void run() {

                System.out.println("1 is on");
                double x0 = 0.5;
                double y0 = 0.0;
                StdRandom.setSeed(System.nanoTime());
                double a0 = StdRandom.uniform(0, 360);
                double step = 0.001;
                Turtle turtle = new Turtle(0.5, 0.5, a0);
              //  setPenColor(Color.RED);
                for (int i = 0; i < 100000; i++) {
                    StdDraw.setPenColor(StdDraw.RED);
                    turtle.goForward(step);
                    turtle.turnLeft(StdRandom.uniform(0, 360));
                }
            }
        };

        t1.start();

        t2.start();
    }

}