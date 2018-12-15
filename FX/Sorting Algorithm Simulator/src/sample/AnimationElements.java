package sample;

import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

class AnimationElements {

    private final PathTransition transition;
    private final MoveTo start;
    private final ArcTo fin;

    public AnimationElements(double height) {
        this.start = new MoveTo();
        this.fin = new ArcTo();
        fin.setRadiusX(150);
        fin.setRadiusY(200);
        this.transition = new PathTransition(Duration.millis(1000), new Path(start, fin));
    }

    public void init(Label movedNode, Label moveEnd) {
        // init animation according to positions of the Nodes to move
        double sx = movedNode.getTranslateX();

        double dx = moveEnd.getTranslateX();
        double dy = moveEnd.getTranslateY();
        start.setX(sx+45/2);
        start.setY(movedNode.getTranslateY()+45/2);
        fin.setX(dx+45/2);
        fin.setY(dy+45/2);
        transition.setNode(movedNode);
    }

    public PathTransition getTransition() {
        return transition;
    }

}