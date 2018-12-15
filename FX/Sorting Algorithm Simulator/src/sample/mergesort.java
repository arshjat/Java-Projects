package sample;

import javafx.animation.ParallelTransition;
import javafx.beans.binding.IntegerBinding;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class mergesort extends sort {

    Label[] lab;
    List<Swap> swaps = new ArrayList<Swap>();

    mergesort(int a[], Label[] b, int counter) {
        super(a, b, counter);
    }

    public void sort(){
        lab = b.clone();

        Integer[] what = Arrays.stream( a ).boxed().toArray( Integer[]::new );
        iterativeMergeSort(what);
        AnimationElements animationElements1 = new AnimationElements(100);
        AnimationElements animationElements2 = new AnimationElements(-100);
        ParallelTransition animation = new ParallelTransition(animationElements1.getTransition(), animationElements2.getTransition());


        Iterator<Swap> iterator = swaps.iterator();
        animation.setOnFinished(evt -> {
            if (iterator.hasNext()) {
                // continue with next swap
                iterator.next().init(animationElements1, animationElements2, lab);
                animation.play();
            }
        });
        if (iterator.hasNext()) {
            // execute first swap
            iterator.next().init(animationElements1, animationElements2, lab);
            animation.play();
        }
    }

    public  <T extends Comparable<? super T>> void iterativeMergeSort(T[] seed) {

        for (int i = 1; i <counter; i=i+i)
        {
            for (int j = 0; j < counter - i; j = j + i+i)
            {
                inPlaceMerge(seed, j, j + i-1, Math.min(j+i + i -1, counter-1));
            }
        }
    }
    public  <T extends Comparable<? super T>>  void inPlaceMerge(T[] collection, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;

        if(collection[mid].equals(collection[right])) {
            return ;//Skip the merge if required
        }
        while (left <= mid && right <= high) {
            // Select from left:  no change, just advance left
            if (collection[left].compareTo(collection[right]) <= 0) {
                left ++;
            } else if(collection[left].compareTo(collection[right]) > 0){ // Select from right:  rotate [left..right] and correct
                T tmp = collection[right]; // Will move to [left]
                collection[right]=collection[left];
                collection[left] = tmp;
                Label dummy = lab[left];
                lab[left] = lab[right-left];
                lab[right]=dummy;
                swaps.add(new Swap(left,right));
                // EVERYTHING has moved up by one
                left ++; right ++; mid ++;
            }

        }
    }


}
