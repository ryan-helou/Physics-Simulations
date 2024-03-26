
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author anishmehra
 */
public class Pendulum {
        Circle holder;
        Circle bob;
        Line line;
        Arc arc;
        private PathTransition path;
        Color color = Color.hsb(55.6, 0.63, 0.926);
  public Pendulum(Circle holder, Circle bob, Line line, Arc arc){
   this.holder = holder;
   this.bob = bob;
   this.line = line;
   this.arc = arc;
   
   bob.setFill(color);
    line.endXProperty().bind(bob.translateXProperty().add(bob.getCenterX()));
            line.endYProperty().bind(bob.translateYProperty().add(bob.getCenterY()));
             path = new PathTransition();
            path.setDuration(Duration.millis(1000));
            path.setPath(arc);
            path.setNode(bob);
            path.setOrientation(PathTransition.OrientationType.NONE);
            path.setCycleCount(PathTransition.INDEFINITE);
            path.setAutoReverse(true);
            path.play();
}

    public PathTransition getPath() {
        return path;
    }
  
}
