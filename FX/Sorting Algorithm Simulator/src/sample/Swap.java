package sample;


import javafx.scene.Node;
import javafx.scene.control.Label;

class Swap {

    private final int index1;
    private final int index2;

    public Swap(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
    }

    public void init(AnimationElements animation1, AnimationElements animation2, Label[] sortNodes) {
        // initialize both positions
        Label n1 = sortNodes[index1];
        Label n2 = sortNodes[index2];
        animation1.init(n1, n2);
        animation2.init(n2, n1);

        // swap order to be correct for the next swap
        sortNodes[index2] = n1;
        sortNodes[index1] = n2;
    }
}