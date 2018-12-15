package sample;

import javafx.animation.ParallelTransition;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class selectionsort extends sort {
    selectionsort(int a[], Label[] b, int counter) {
        super(a, b, counter);
    }


    public void sort(){
        int i, j, min_idx;

        Label[] l = b.clone();
        List<Swap> swaps = new ArrayList<Swap>();
        // One by one move boundary of unsorted subarray
        for (i = 0; i < counter-1; i++)
        {
            // Find the minimum element in unsorted array
            min_idx = i;
            for (j = i+1; j < counter; j++)
                if (a[j] < a[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first element
            if(min_idx!=i) {
                swaps.add((new Swap(min_idx, i)));
                Label dummy = l[i];
                l[i] = l[min_idx];
                l[min_idx] = dummy;
                int d = a[i];
                a[i] = a[min_idx];
                a[min_idx] = d;
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
