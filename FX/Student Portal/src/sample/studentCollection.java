package sample;

import java.util.ArrayList;

public class studentCollection {
    ArrayList<Student>lists = new ArrayList<>();

    public void additem(Student q){
        lists.add(q);
    }
    public Student getitem(int i){
        return lists.get(i);
    }
    public int getSize(){
        return lists.size();
    }
}
