package sample;

import javafx.animation.PathTransition;
import javafx.scene.control.Label;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.sql.Types.TIME;

public class sort {
    Label b[];
    int a[];
    PathTransition pathTransition[] = new PathTransition[20];
    Path path;
    int counter;
    final Lock lock = new ReentrantLock();
    final Condition[] swapComplete  = { lock.newCondition(), lock.newCondition() };
    sort(int a[],Label[] b,int counter){
        this.b=b;
        this.a=a;
        this.counter=counter;
    }
    public void sort(){}

}
