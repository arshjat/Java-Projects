package sample;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;
import static java.sql.Types.TIME;


public class Main extends Application {

    Scene sc1,sc2,sc3,sc4,sc5;
    int a[] = new int[1000];
    Label b[]=null;
    Path path;
    PathTransition pathTransition[] = new PathTransition[20];
    private int counter;
    private int TIME = 1000;

    private int algocount=0;
    @Override
    public void start(Stage window) throws Exception{


        //------------------SCENE 1-------------------------

        GridPane gp1=new GridPane();
        Label l11 = new Label("   Enter The Numbers To Sort!");
        TextField t11 = new TextField();
        t11.setPromptText("Enter the numbers.");
        ComboBox c1 = new ComboBox();
        c1.setValue("Select Algorithm");
        c1.getItems().addAll("Bubble Sort","Insertion Sort","Selection Sort","Merge Sort","Quick Sort");
        Button b11 = new Button("          Go to Simulator          ");
        b11.setOnAction(e->{
            //------------------SCENE 2--------------------------
            ArrayList<sort> algos= new ArrayList<>();

            for(int i=0;i<20;i++){
                pathTransition[i] = new PathTransition();
            }
            String str = t11.getText();
            String strArray[] = str.split(" ");
            int a[] = getA();
            counter=0;
            for(String i:strArray) a[counter++]=Integer.parseInt(i);
            for(int i=0;i<counter;i++) System.out.println(a[i]);
            setCounterforB();
            Group root = new Group();




            for(int i=0;i<counter;i++){
                b[i] = new Label("    " + Integer.toString(a[i]));
                b[i].setTranslateX(150 + i*80);
                b[i].setTranslateY(200);
                b[i].setBackground(new Background(new BackgroundFill(Paint.valueOf("pink"), new CornerRadii(500), Insets.EMPTY)));
                b[i].setMinSize(45,45);
                root.getChildren().add(b[i]);
            }

            algos.add(new bubblesort(a,b,counter));
            algos.add(new selectionsort(a,b,counter));
            algos.add(new insertionsort(a,b,counter));
            algos.add(new mergesort(a,b,counter));
            Button b21 =  new Button("Start Simulation");
            b21.setTranslateX(150);
            b21.setTranslateY(100);
            b21.setOnAction(e1->{
                sort algo = new sort(a,b,counter);
                if(c1.getSelectionModel().isSelected(0)){
                    algo = new bubblesort(a,b,counter);
                }
                else if(c1.getSelectionModel().isSelected(1)){
                    algo = new insertionsort(a,b,counter);
                }
                else if(c1.getSelectionModel().isSelected(2)){
                    algo = new selectionsort(a,b,counter);
                }
                else if(c1.getSelectionModel().isSelected(4)){
                    algo = new quicksort(a,b,counter);
                }
                else if(c1.getSelectionModel().isSelected(3)){
                    algo = new mergesort(a,b,counter);
                }

//                algo = new mergesort(a,b,counter);
                algo.sort();
            });
            root.getChildren().add(b21);
            sc2 = new Scene(root,1200,700);
            window.setScene(sc2);
            window.show();
        });

        gp1.add(l11,0,0,1,1);
        gp1.add(t11,0,1,1,1);
        gp1.add(c1,0,2,1,1);
        gp1.add(b11,0,3,1,1);
        gp1.setVgap(40);
        gp1.setHgap(40);
        gp1.setPadding(new Insets(40));
        sc1 = new Scene(gp1);
        window.setScene(sc1);

        //------------------SCENE 3---------------------------


        window.setTitle("Sorting Algorithm Simulator");
        window.show();
    }

    private void setCounterforB() {
        b = new Label[counter];
    }

    public int[] getA(){
        return a;
    }

    public Label[] getB() {
        return b;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
