package sample;

import javafx.animation.ParallelTransition;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class quicksort extends sort {
    Label[] l = b.clone();
    List<Swap> swaps = new ArrayList<Swap>();
    quicksort(int a[], Label[] b, int counter) {
        super(a, b, counter);
    }

    public void sort(){

        quickSort(a, 0, counter-1);

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
    int partition (int arr[], int low, int high)
    {
        int pivot = arr[high];    // pivot
        int i = (low - 1);  // Index of smaller element

        for (int j = low; j <= high- 1; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;    // increment index of smaller element
                if(arr[j]!=pivot) {
                    swaps.add(new Swap(i, j));
                    Label dummy = l[i];
                    l[i] = l[j];
                    l[j] = dummy;
                    int d = a[i];
                    a[i] = a[j];
                    a[j] = d;
                }
            }
        }

//        if(i+1!=high) {
            swaps.add(new Swap(i + 1, high));
            Label dummy = l[i + 1];
            l[i + 1] = l[high];
            l[high] = dummy;
            int d = a[i + 1];
            a[i + 1] = a[high];
            a[high] = d;
//        }
        return (i + 1);
    }

    void quickSort(int arr[], int low, int high)
    {
        if (low < high)
        {
        /* pi is partitioning index, arr[p] is now
           at right place */
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }



}
