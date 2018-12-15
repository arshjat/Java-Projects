package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static javafx.scene.input.KeyCode.*;

public class Main extends Application {

    private static final int      KEYBOARD_MOVEMENT_DELTA = 5;
    private static final Duration TRANSLATE_DURATION      = Duration.seconds(0.25);
    Stage window ;
    MouseGestures mouseGestures = new MouseGestures();

    MenuBar menu = new MenuBar();
    Group group = new Group(menu);
    public static void main(String[] args) { launch(args); }
    @Override public void start(Stage stage) throws Exception {
        window=stage;
//        Circle circle = createCircle();



        Scene scene = new Scene(group, 600, 400, Color.CORNSILK);

        Menu filemenu = new Menu("Select Shape");
        Menu ins = new Menu("Manual");
        MenuItem instruction = new MenuItem("instructions");
        ins.getItems().addAll(instruction);
        instruction.setOnAction(e->{
            VBox v = new VBox();
            Scene sc2 = new Scene(v);
            Label l1 = new Label("Press left mouse button to select a shape. Move the mouse while pressed to drag." +
                    "\n" + "Use up-down-left-right buttons on keyboard to move the shape" +"\n" + "Use + and - to increase or decrease size of shape" + "\n"
                    + "Use delete button on keyboard to delete a shape");

            Button b = new Button("Back");
            b.setOnAction(e1->{
                window.setScene(scene);
                window.show();
            });
            Label l2 = new Label("\n");
            v.getChildren().addAll(l1,l2,b);
            window.setScene(sc2);
            window.show();
        });
        MenuItem c_menu = new MenuItem("Circle");
        c_menu.setOnAction(e->{
            Circle circle = createCircle();
//            TranslateTransition transitionC = createTranslateTransitionCircle(circle);
            moveCircleOnKeyPress(scene,circle);
//            moveCircleOnMouseDrag(scene, circle, transitionC);
            circle.setOnMouseClicked(e1->{
                circle.requestFocus();
            });


            mouseGestures.makeDraggable(circle);
            group.getChildren().add(circle);
        });
        MenuItem r_menu = new MenuItem("Rectangle");
        r_menu.setOnAction(e->{
            Rectangle rect = new Rectangle(100,100,Color.rgb(134, 193, 185));
            rect.setOnMouseClicked(e1->{
                rect.requestFocus();
            });
//            TranslateTransition transitionR = createTranslateTransitionRect(rect);
            moveRectOnKeyPress(scene, rect);
//            moveRectOnMouseDrag(scene, rect, transitionR);
            mouseGestures.makeDraggable(rect);
            group.getChildren().add(rect);
        });
        MenuItem t_menu = new MenuItem("Triangle");
        t_menu.setOnAction(e->{
            Polygon tri = new Polygon();
            tri.setOnMouseClicked(e1->{
                tri.requestFocus();
            });
            tri.setFill(Color.rgb(100, 178, 74));
            tri.getPoints().addAll(new Double[]{
                    100.0,0.0,
                    50.0,50.0,
                    0.0,0.0});

//            TranslateTransition transitionT = createTranslateTransitionTri(tri);
            moveTriOnKeyPress(scene, tri);
//            moveTriOnMouseDrag(scene, tri, transitionT);
            mouseGestures.makeDraggable(tri);
            group.getChildren().add(tri);
        });
        filemenu.getItems().add(c_menu);
        filemenu.getItems().add(new SeparatorMenuItem());
        filemenu.getItems().add(r_menu);
        filemenu.getItems().add(new SeparatorMenuItem());
        filemenu.getItems().add(t_menu);

        menu.getMenus().addAll(filemenu,ins);

        window.setScene(scene);
        window.show();
    }

    private Circle createCircle() {
        final Circle circle = new Circle(200, 150, 50, Color.BLUEVIOLET);
        circle.setOpacity(0.7);
        return circle;
    }



    private void moveCircleOnKeyPress(Scene scene,Circle circle) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override public void handle(KeyEvent event) {
//                Circle circle = (Circle) (event.getSource());
                switch (event.getCode()) {
                    case UP:    circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
                    case RIGHT: circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
                    case DOWN:  circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
                    case LEFT:  circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
                    case ADD:   circle.setRadius(circle.getRadius() + KEYBOARD_MOVEMENT_DELTA); break;
                    case SUBTRACT: circle.setRadius(circle.getRadius() - KEYBOARD_MOVEMENT_DELTA); break;
                    case DELETE: group.getChildren().remove(circle); break;
                }
            }
        });
    }

    private void moveRectOnKeyPress(Scene scene, final Rectangle rect) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    rect.setY(rect.getY() - KEYBOARD_MOVEMENT_DELTA); break;
                    case RIGHT: rect.setX(rect.getX() + KEYBOARD_MOVEMENT_DELTA); break;
                    case DOWN:  rect.setY(rect.getY() + KEYBOARD_MOVEMENT_DELTA); break;
                    case LEFT:  rect.setX(rect.getX() - KEYBOARD_MOVEMENT_DELTA); break;
                    case ADD:   {
                                rect.setScaleX(rect.getScaleX() + KEYBOARD_MOVEMENT_DELTA*0.05);
                                rect.setScaleY(rect.getScaleY() + KEYBOARD_MOVEMENT_DELTA*0.05);
                                } break;
                    case SUBTRACT: {
                                    rect.setScaleX(rect.getScaleX() - KEYBOARD_MOVEMENT_DELTA*0.05);
                                    rect.setScaleY(rect.getScaleY() - KEYBOARD_MOVEMENT_DELTA*0.05);
                                } break;
                    case DELETE: group.getChildren().remove(rect); break;
                }
            }
        });
    }

    private void moveTriOnKeyPress(Scene scene, final Polygon tri) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    tri.setLayoutY(tri.getLayoutY() - KEYBOARD_MOVEMENT_DELTA); break;
                    case RIGHT: tri.setLayoutX(tri.getLayoutX() + KEYBOARD_MOVEMENT_DELTA); break;
                    case DOWN:  tri.setLayoutY(tri.getLayoutY() + KEYBOARD_MOVEMENT_DELTA); break;
                    case LEFT:  tri.setLayoutX(tri.getLayoutX() - KEYBOARD_MOVEMENT_DELTA); break;
                    case ADD:   {
                        tri.setScaleX(tri.getScaleX() + KEYBOARD_MOVEMENT_DELTA*0.05);
                        tri.setScaleY(tri.getScaleY() + KEYBOARD_MOVEMENT_DELTA*0.05);
                    } break;
                    case SUBTRACT: {
                        tri.setScaleX(tri.getScaleX() - KEYBOARD_MOVEMENT_DELTA*0.05);
                        tri.setScaleY(tri.getScaleY() - KEYBOARD_MOVEMENT_DELTA*0.05);
                    } break;
                    case DELETE: group.getChildren().remove(tri);break;

                }
            }
        });
    }



}