package sample;

import javafx.animation.ParallelTransition;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class insertionsort extends sort {
    insertionsort(int a[], Label[] b, int counter) {
        super(a, b, counter);
    }

    public void sort(){
            Label[] l = b.clone();
            List<Swap> swaps = new ArrayList<Swap>();
        int i, key, j;
        for (i = 1; i < counter; i++)
        {

            j = i-1;
            key = a[i];

            while( j>=0 && a[j] > key){
                swaps.add(new Swap(j+1,j));
                Label dummy = l[j];
                l[j]=l[j+1];
                l[j+1] = dummy;
                int d = a[j];
                a[j]=a[j+1];
                a[j+1]=d;

                j=j-1;
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
