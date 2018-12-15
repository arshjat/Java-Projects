package sample;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class MouseGestures {
    class DragContext {
        double x;
        double y;
    }

    DragContext dragContext = new DragContext();

    public void makeDraggable( Node node) {

        node.setOnMousePressed( onMousePressedEventHandler);
        node.setOnMouseDragged( onMouseDraggedEventHandler);
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, event ->
                node.getParent().setCursor(Cursor.HAND));
    }


    EventHandler<MouseEvent> onMousePressedEventHandler = event -> {


        if( event.getSource() instanceof Circle) {

            Circle circle = (Circle) (event.getSource());
            circle.requestFocus();
            dragContext.x = circle.getCenterX() - event.getSceneX();
            dragContext.y = circle.getCenterY() - event.getSceneY();

        } else {

            Node node = (Node) (event.getSource());
            node.requestFocus();
            dragContext.x = node.getTranslateX() - event.getSceneX();
            dragContext.y = node.getTranslateY() - event.getSceneY();

        }
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {

        if( event.getSource() instanceof Circle) {

            Circle circle = (Circle) (event.getSource());

            circle.setCenterX( dragContext.x + event.getSceneX());
            circle.setCenterY( dragContext.y + event.getSceneY());

        } else {

            Node node = (Node) (event.getSource());

            node.setTranslateX( dragContext.x + event.getSceneX());
            node.setTranslateY( dragContext.y + event.getSceneY());

        }

    };

}
