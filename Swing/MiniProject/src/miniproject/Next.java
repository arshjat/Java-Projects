/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;
public class Next implements Runnable{
    private String name;
    private Sort s;
    Next(String name,Sort s){
        this.name=name;
        this.s=s;
    }
    public void run(){
        if(name.equals("Selection")){

            while(((SelectionSort)s).getComplete()==0)
            System.out.println("");
//            System.out.println("hh");

        }
    }
}