/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.pendulum;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Pendulum {
    Vector2D loc;
    private Vector2D origin;
    float r;
    private float ballr;
    private float damping;
    private float theta;
    float theta_vel;
    private float theta_acc;
    private double mass;
    private boolean dragging = false;
    private GraphicsContext gc;
    private boolean showTrail = true;

    // Store previous positions of the pendulum for trail
    private List<Vector2D> trail = new ArrayList<>();
    private static final int TRAIL_LENGTH = 100; // Adjust as needed

    public Pendulum(GraphicsContext gc, Vector2D origin_, float r_, boolean showTrail) {
        this.gc = gc;
        this.showTrail = showTrail;
        this.origin = origin_;
        this.r = r_;
        this.ballr = 30.0f;
        this.damping = 1f;
        this.loc = new Vector2D(r * Math.sin(theta), r * Math.cos(theta)).add(origin);
    }

    public void go() {
        update();
        render();
    }
 public void setShowTrail(boolean showTrail) {
        this.showTrail = showTrail;
    }
    private void update() {
        if (!dragging) {
            float G = 0.0981f;
            theta_acc = (-G / r) * (float) Math.sin(theta);
            theta_vel += theta_acc;
            theta_vel *= damping;
            theta += theta_vel;
        }
        loc = new Vector2D(r * Math.sin(theta), r * Math.cos(theta)).add(origin);
        // Add current position to the trail
        trail.add(loc.copy());
        // Keep the trail length limited
        if (trail.size() > TRAIL_LENGTH) {
            trail.remove(0);
        }
    }

    private void render() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
          if(showTrail){
        // Draw the trail
        for (int i = 0; i < trail.size() - 1; i++) {
            Vector2D current = trail.get(i);
            Vector2D next = trail.get(i + 1);
            gc.strokeLine(current.getX(), current.getY(), next.getX(), next.getY());
        }
          }
        // Draw the pendulum
        gc.strokeLine(origin.getX(), origin.getY(), loc.getX(), loc.getY());
        gc.setFill(Color.rgb(3, 255, 46));
        if (dragging) gc.setFill(Color.BLACK);
        gc.fillOval(loc.getX() - ballr / 2, loc.getY() - ballr / 2, ballr, ballr);
    }

    public void clicked(int mx, int my) {
        float d = (float) Math.sqrt(Math.pow(mx - loc.getX(), 2) + Math.pow(my - loc.getY(), 2));
        if (d < ballr / 2) {
            dragging = true;
            resetAll();
        }
    }

    public void dragged(int mx, int my) {
        if (dragging) {
            Vector2D diff = origin.subtract(new Vector2D(mx, my));
            theta = (float) (Math.atan2(-diff.getY(), diff.getX()) - Math.toRadians(90));
            loc = new Vector2D(r * Math.sin(theta), r * Math.cos(theta)).add(origin);
        }
    }

    public void stopDragging() {
        dragging = false;
    }

    public void resetAll() {
        theta = 0.0f;
        theta_vel = 0.0f;
        loc = new Vector2D(r * Math.sin(theta), r * Math.cos(theta)).add(origin);
        trail.clear();
    }

    public void resetAllPendulums(Pendulum[] pendulums) {
        for (Pendulum p : pendulums) {
            p.resetAll();
        }
        
    }
}
