package sample;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.scene.control.Label;

import javafx.animation.PathTransition;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

import static javafx.animation.Animation.Status.RUNNING;

public class bubblesort extends sort {

    final Lock lock = new ReentrantLock();
    final Condition[] swapComplete = {lock.newCondition(), lock.newCondition()};
    private int x = 0;

    bubblesort(int a[], Label[] b, int counter) {
        super(a, b, counter);

    }
    public void sort(){
        Label[] l = b.clone();
        List<Swap> swaps = new ArrayList<Swap>();
        for(int i=0;i<counter;i++){
            for(int j=0;j<counter-i-1;j++){
                if(a[j]>a[j+1]){
                    swaps.add(new Swap(j,j+1));
                    Label dummy = l[j];
                    l[j]=l[j+1];
                    l[j+1]=dummy;
                    int d = a[j];
                    a[j]=a[j+1];
                    a[j+1]=d;
                }

            }
        }

        AnimationElements animationElements1 = new AnimationElements(100);
        AnimationElements animationElements2 = new AnimationElements(-100);
        ParallelTransition animation = new ParallelTransition(animationElements1.getTransition(), animationElements2.getTransition());


        Iterator<Swap> iterator = swaps.iterator();
        animation.setOnFinished(evt -> {
            if (iterator.hasNext()) {
                // continue with next swap
                iterator.next().init(animationElements1, animationElements2, l);
                animation.play();
            }
        });
        if (iterator.hasNext()) {
            // execute first swap
            iterator.next().init(animationElements1, animationElements2, l);
            animation.play();
        }


    }
}

//    public void sort() {
//        Thread thread = new Thread(
//                () -> {
//                    for(int i=0;i<counter;i++){
//                        for(int j=i;j<counter-i-1;j++){
//                            if (Integer.parseInt(Character.toString(b[j].getText().charAt(4))) > Integer.parseInt(Character.toString(b[j].getText().charAt(4)))) {
//                                final int finalMin = j;
//                                final int finalI = j+1;
//                                FutureTask<Void> future = new FutureTask<>(
//                                () -> {
//                                    StartAnimation(b[finalI],0,b[finalMin].getTranslateX(),b[finalMin].getTranslateY());
//                                    StartAnimation(b[finalMin],0,b[finalI].getTranslateX(),b[finalI].getTranslateY());
//
//                                    return null;
//                                });
//
//                                lock.lock();
//                                try {
//                                    Platform.runLater(future);
//                                    future.get();
//                                    for (Condition condition: swapComplete) {
//                                        condition.await();
//                                    }
//                                } catch (InterruptedException e) {
//                                    Thread.interrupted();
//                                    break;
//                                } catch (ExecutionException e) {
//                                    e.printStackTrace();
//                                    break;
//                                } finally {
//                                    lock.unlock();
//                                }
//
//                                Label temporary = b[finalI];
//                                b[finalI] = b[finalMin];
//                                b[finalMin] = temporary;
//                            }
//
//                        }
//                    }
//                }
//        );
//        thread.setDaemon(true);
//        thread.start();
//
//
//
//        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
////
////        int min;
////        for (int i = 0; i < counter; i++) {
////
////            min = i;
////            for (int j = i + 1; j < counter; j++) {
////                if (a[j] < a[min]) {
////                    min = j;
////
////                }
////            }
////            if (min != i) {
////                int temp = a[i];
////                a[i] = a[min];
////                a[min] = temp;
////                final int finalMin = min;
////                final int finalI = i;
////
////                FutureTask<Void> future = new FutureTask<>(
////                        () -> {
////                            StartAnimation(b[finalI],0,b[finalMin].getTranslateX(),b[finalMin].getTranslateY());
////                            StartAnimation(b[finalMin],0,b[finalI].getTranslateX(),b[finalI].getTranslateY());
////
////                            return null;
////                        }
////                );
////
////                lock.lock();
////                try {
////                    Platform.runLater(future);
////                    future.get();
////                    for (Condition condition: swapComplete) {
////                        condition.await();
////                    }
////                } catch (InterruptedException e) {
////                    Thread.interrupted();
////                    break;
////                } catch (ExecutionException e) {
////                    e.printStackTrace();
////                    break;
////                } finally {
////                    lock.unlock();
////                }
////
////                Label temporary = b[i];
////                b[i] = b[min];
////                b[min] = temporary;
////            }
////            System.out.println(a[i]);
////        }
//        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
////        for (int i = 0; i < 3 - 1; i++) {
////            for (int k = 0; k < 20; k++) pathTransition[k] = new PathTransition();
//
////            for (int j = 0; j < 3 - i - 1; j++) {
////                if (Integer.parseInt(Character.toString(b[j].getText().charAt(4))) > Integer.parseInt(Character.toString(b[j].getText().charAt(4)))) {
////                    double x1 = b[j].getTranslateX();
////                    double y1 = b[j].getTranslateY();
////                    double x2 = b[j + 1].getTranslateX();
////                    double y2 = b[j + 1].getTranslateY();
////                    animate(b[j], j, x2, y2);
////                    animate(b[j + 1], j, x1, y1);
////                    while (x==0);
////                    Label dummy = b[j];
////                    b[j] = b[j + 1];
////                    b[j + 1] = dummy;
////                    pathTransition[j] = new PathTransition();
////                    pathTransition[j + 1] = new PathTransition();
////                }
////                for (int k = 0; k < 20; k++) pathTransition[k] = new PathTransition();
////
////            }
//
////            super.StartAnimation(b[0],0,b[4].getTranslateX(),b[4].getTranslateY());
////            super.StartAnimation(b[4],4,b[0].getTranslateX(),b[0].getTranslateY());
//////            while(b[0].getTranslateX()!=x2 && b[4].getTranslateX()!=x1 && b[0].getTranslateY()!=y2 && b[4].getTranslateY()!=y1);
////        Thread.sleep(1000);
////                    Label dummy = b[0];
////                    b[0] = b[4];
////                    b[4] = dummy;
////            pathTransition[0]=new PathTransition();
////            pathTransition[4]=new PathTransition();
////            super.StartAnimation(b[0],0,b[1].getTranslateX(),b[1].getTranslateY());
////        super.StartAnimation(b[1],1,b[0].getTranslateX(),b[0].getTranslateY());
//
//
////        }
////        double x1 = b[0].getTranslateX();
////        double y1 = b[0].getTranslateY();
////        double x2 = b[4].getTranslateX();
////        double y2 = b[4].getTranslateY();
////        super.StartAnimation(b[0],0,b[4].getTranslateX(),b[4].getTranslateY());
////        super.StartAnimation(b[4],4,b[0].getTranslateX(),b[0].getTranslateY());
////        while(pathTransition[0].getStatus()==RUNNING || pathTransition[4].getStatus()==RUNNING );
////        System.out.println("yaaaasssss!!");
//
//    }
//
//    public void StartAnimation(Label label,int i,double x,double y){
//        path = new Path();
//        pathTransition[i] = new PathTransition();
//        MoveTo moveTo1 = new MoveTo(label.getTranslateX()+45/2,label.getTranslateY()+25);
//        ArcTo fin = new ArcTo();
//        fin.setRadiusX(200);
//        fin.setRadiusY(200);
//        fin.setX(x+45/2);
//        fin.setY(y+45/2);
//        path.getElements().addAll(moveTo1,fin);
//        pathTransition[i].setDuration(Duration.millis(1000));
//        pathTransition[i].setNode(label);
//        pathTransition[i].setPath(path);
//        pathTransition[i].setCycleCount(1);
//        pathTransition[i].setAutoReverse(false);
//
//
//        pathTransition[i].setOnFinished(event -> {
////            label.setTranslateX(x);
////            label.setTranslateY(y);
//
//            lock.lock();
//            try {
//                swapComplete[i].signal();
//            } finally {
//                lock.unlock();
//            }
//        });
//        pathTransition[i].play();
//    }
//
//
//}
