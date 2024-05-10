/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.template.pendulum;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an individual pendulum object, of which, the cradle is composed of
 * exactly five. Code logic is explained in detail through the update methods below. 
 *
 * @author salki
 */
public class Pendulum {

    public Vector2D loc;
    public float theta_vel;
    public float r;
    private boolean dragging = false;
    private boolean showTrail = true;
    private double mass;
    private int index;
    private static final int TRAIL_LENGTH = 100;
    private final float MAX_THETA = (float) Math.toRadians(90); //restrictions to the drag
    private final float MIN_THETA = (float) -Math.toRadians(90);
    private float ballr;
    private float damping;
    private float theta;
    private float theta_acc;
    private float gravity = 1.0f;
    private GraphicsContext gc;
    private Vector2D origin;
    private Vector2D clickPos;
    private List<Vector2D> trail = new ArrayList<>(); //To store previous positions of the pendulum for trail
    private Pendulum[] pendulums;

    public Pendulum(GraphicsContext gc, Vector2D origin_, float r_, boolean showTrail, int index) {
        this.gc = gc;
        this.showTrail = showTrail;
        this.origin = origin_;
        this.r = r_;
        this.ballr = 30.0f;
        this.damping = 1f;
        this.loc = new Vector2D(r * Math.sin(theta), r * Math.cos(theta)).add(origin);
        this.index = index;
    }

    /**
     * Updates the pendulum throughout the simulation. Makes use of
     * two methods, update() and render(). Update is responsible for
     * the back-end calculations of the pendulum while render is responsible
     * for redrawing the canvas.
     */
    public void go() {
        update();
        render();
    }

    /**
     * Updates the pendulum's location on the back-end. When it is released and
     * not dragging, the method calculates the angular acceleration that opposes
     * the pendulum's motion. Angular velocity is updated by adding angular acceleration
     * to it, slowing it down. It is also multiplied by the damping factor for whether
     * the user decides to enable it or not. Theta is then updated by theta_vel to represent
     * the angle of the new location. "Loc" is simply the x and y components of the angle,
     * thus representing the new location of the canvas.
     * 
     * 
     */
    private void update() {
        if (!dragging) {
            float G = gravity; //0.0981f
            theta_acc = (-G / r) * (float) Math.sin(theta);
            theta_vel += theta_acc;
            theta_vel *= damping;
            theta += theta_vel;
        }
        loc = new Vector2D(r * Math.sin(theta), r * Math.cos(theta)).add(origin); //new location/position of the bob
        
        trail.add(loc.copy());              //adds current position to the trail
        if (trail.size() > TRAIL_LENGTH) {      //keeps the trail length limited
            trail.remove(0);
        }
    }

    /**
     * Renders a preset pendulum object of a specific size and width
     */
    private void render() {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        if (showTrail) {
            // Draws the trail
            for (int i = 0; i < trail.size() - 1; i++) {
                Vector2D current = trail.get(i);
                Vector2D next = trail.get(i + 1);
                gc.strokeLine(current.getX(), current.getY(), next.getX(), next.getY());
            }
        }
        // Draws the pendulum
        gc.strokeLine(origin.getX(), origin.getY(), loc.getX(), loc.getY());
        gc.setFill(Color.rgb(3, 255, 46));
        if (dragging) {
            gc.setFill(Color.BLACK);
        }
        gc.fillOval(loc.getX() - ballr / 2, loc.getY() - ballr / 2, ballr, ballr);
    }

    /**
     * Responsible for handling click events. Used to determine whether the user
     * has clicked on the pendulum bob, to initiate the dragging method. It does
     * this by calculating the distance "d" between the initial click location
     * and the bob itself. If this distance is less than half of the bob's
     * radius, it registers it as a click and initiates dragging.
     *
     * It uses the euclidean distance formula to do this, which is the formula used
     * to find the straight line distance between two points on a 2-dimensional plane.
     * 
     * @param mx mouse coordinate x upon clicking.
     * @param my mouse coordinate y upon clicking.
     */
    public void clicked(int mx, int my) {
        float d = (float) Math.sqrt(Math.pow(mx - loc.getX(), 2) + Math.pow(my - loc.getY(), 2));
        if (d < ballr / 2) {
            dragging = true;
            resetAll();
            clickPos = new Vector2D(mx, my);
        }
    }

    /**
     * Responsible for dragging a pendulum bob. If dragging is enabled, it calculates the
     * difference between the mouse and the bob via the diff vector. An angle is then calculated
     * based off this vector which is called proposedTheta. The pendulum is then updated to the
     * new location "loc" that is calculated the same way as described in the update method.
     * 
     * Additional code is included to limit the pendulum's range of motion. "Index" is meant to represent
     * the current pendulum selected and if the theta goes over or under the minimum/maximum value permitted,
     * it sets the proposed theta to said maximum/minimum value which is a final variable. This is meant to represent
     * the "horizontal limit" which is why the central pendulum is not included. For the "vertical" limit, if the proposed
     * theta is smaller than -pi or bigger than pi the method brings it back to a more acceptable range. This is universal
     * for all bobs to limit the central pendulum as well, therefore no indexes are used.
     *
     * @param mx mouse coordinate x upon dragging.
     * @param my mouse coordinate y upon dragging.
     */
    public void dragged(int mx, int my) {
        if (dragging) {
            Vector2D diff = origin.subtract(new Vector2D(mx, my));
            float proposedTheta = (float) (Math.atan2(-diff.getY(), diff.getX()) - Math.toRadians(90));

            if (proposedTheta < -Math.PI) {
                proposedTheta += 2 * Math.PI;
            } //  brings angle to pi and -pi.  
            else if (proposedTheta > Math.PI) {
                proposedTheta -= 2 * Math.PI;
            }

            if ((index == 2 || index == 1) && proposedTheta > 0) {
                //movement for pendulums 2 and 1
                proposedTheta = Math.min(proposedTheta, 0);
            } else if ((index == 3 || index == 4) && proposedTheta < 0) {
                //movement for pendulums 3 and 4
                proposedTheta = Math.max(proposedTheta, 0);
            }

            if (proposedTheta > MAX_THETA) {
                theta = MAX_THETA;
            } else if (proposedTheta < MIN_THETA) {
                theta = MIN_THETA;
            } else {
                theta = proposedTheta;
            }

            loc = new Vector2D(r * Math.sin(theta), r * Math.cos(theta)).add(origin);
        }
    }

    /**
     * Stops the dragging of a bob by setting the "dragging" to false.
     */
    public void stopDragging() {
        dragging = false;
    }

    /**
     * Resets an individual pendulum to its initial state before dragging by setting
     * theta to 0.
     */
    public void resetAll() {
        theta = 0.0f;
        theta_vel = 0.0f;
        loc = new Vector2D(r * Math.sin(theta), r * Math.cos(theta)).add(origin);
        trail.clear();
    }

    /**
     * Resets every pendulum to its initial state before dragging.
     *
     * @param pendulums every pendulum within the cradle.
     */
    public void resetAllPendulums(Pendulum[] pendulums) {
        for (Pendulum p : pendulums) {
            p.resetAll();
        }

    }

    //Getters and setters
    public Vector2D getLoc() {
        return loc;
    }

    public void setLoc(Vector2D loc) {
        this.loc = loc;
    }

    public boolean isShowTrail() {
        return showTrail;
    }

    public void setShowTrail(boolean showTrail) {
        this.showTrail = showTrail;
    }

    public float getMAX_THETA() {
        return MAX_THETA;
    }

    public float getMIN_THETA() {
        return MIN_THETA;
    }

    public static int getTRAIL_LENGTH() {
        return TRAIL_LENGTH;
    }

    public Vector2D getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2D origin) {
        this.origin = origin;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getBallr() {
        return ballr;
    }

    public void setBallr(float ballr) {
        this.ballr = ballr;
    }

    public float getDamping() {
        return damping;
    }

    public void setDamping(float damping) {
        this.damping = damping;
    }

    public float getTheta() {
        return theta;
    }

    public void setTheta(float theta) {
        this.theta = theta;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public Pendulum[] getPendulums() {
        return pendulums;
    }

    public void setPendulums(Pendulum[] pendulums) {
        this.pendulums = pendulums;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public float getTheta_vel() {
        return theta_vel;
    }

    public void setTheta_vel(float theta_vel) {
        this.theta_vel = theta_vel;
    }

    public float getTheta_acc() {
        return theta_acc;
    }

    public void setTheta_acc(float theta_acc) {
        this.theta_acc = theta_acc;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public Vector2D getClickPos() {
        return clickPos;
    }

    public void setClickPos(Vector2D clickPos) {
        this.clickPos = clickPos;
    }

    public List<Vector2D> getTrail() {
        return trail;
    }

    public void setTrail(List<Vector2D> trail) {
        this.trail = trail;
    }

}
