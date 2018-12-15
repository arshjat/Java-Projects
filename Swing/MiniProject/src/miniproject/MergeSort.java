/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.Timer;
import miniproject.Sort;

/**
 *
 * @author komal
 */

class MergeSort extends Sort {
    
    private int array[],flag=0,DELAY=20;
    private ArrayList<JButton> jb;
    private Timer timerL=null,timer=null,timerMerge=null;
    private Timer timerR=null;
    int Left,middle,right,f=0;
    
    MergeSort(ArrayList<Integer> numbers,ArrayList<JButton> buttons){
                super((ArrayList<Integer>)numbers.clone(), (ArrayList<JButton>)buttons.clone());
                jb=(ArrayList<JButton>)buttons.clone();
           for(JButton j:jb){
                  j.setBackground(Color.CYAN);
               
            }
        timerMerge=new Timer(30,new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!getTimerUp().isRunning() && !getTimer().isRunning()&&!getTimerDn().isRunning())
                        { 
                          BreakInHalves(Left,right);
                     
                        System.out.println("Hello"+" "+Left+" "+middle);
                        }
                    }
            
        });              
    }
    
    void BreakInHalves(int L,int R){
       
        int m,i;
        if(L<R)
        {
            m=L+(R-L)/2;
         
            
      
            BreakInHalves(L,m);
            BreakInHalves(m+1,R);
          
            
            if(!getTimerUp().isRunning() && !getTimer().isRunning()&&!getTimerDn().isRunning()){
                Left=L;
                middle=m;
                right=R;
                Merge(L,m,R);
                timerMerge.start();
            }
        }
        
        
    }
    
    Timer getMSTimer(){
        return this.timerL;
    }
    
    void Merge(int l,int M,int r){
        int LeftHalf=M-l+1;
        int RightHalf=r-M;
        
        int LArray[]=new int[LeftHalf];
        int RArray[]=new int[RightHalf];
        
        for(int i=l;i<LeftHalf+l;i++){
            LArray[i-l]=this.getNumarray().get(i);
        }
        for(int i=M+1;i<RightHalf+M+1;i++){
            RArray[i-M-1]=this.getNumarray().get(i);
        }
        
        
        int x=0,y=0,merged=l;
        int j,value;
        while(x<LeftHalf && y<RightHalf){
            if(LArray[x]<RArray[y]){
              
                value=LArray[x];
                System.out.println(this.getButtonArr().get(merged).getText()+" "+value);
                j=this.search(value);
              
                if(value!=Integer.parseInt(this.getButtonArr().get(merged).getText()))
                {swap(this.getButtonArr().get(merged), this.getButtonArr().get(j));
                  this.getNumarray().set(merged,LArray[x]);
                    JButton bb = this.getButtonArr().get(j);
                    this.getButtonArr().set(j, this.getButtonArr().get(j+1));
                    this.getButtonArr().set(j+1, bb);

                    int a = this.getNumarray().get(j);
                    this.getNumarray().set(j, this.getNumarray().get(j+1));
                    this.getNumarray().set(j+1, a);
                    flag=1;
                    //
                   
                x++;
                break;
                }
                
            }
            else{
                value=RArray[y];
                System.out.println(this.getButtonArr().get(merged).getText()+" "+value);
                j=this.search(value);
                if(value!=Integer.parseInt(this.getButtonArr().get(merged).getText()))
                {swap(this.getButtonArr().get(merged), this.getButtonArr().get(j));
                  this.getNumarray().set(merged,LArray[x]);
                    JButton bb = this.getButtonArr().get(j);
                    this.getButtonArr().set(j, this.getButtonArr().get(j+1));
                    this.getButtonArr().set(j+1, bb);

                    int a = this.getNumarray().get(j);
                    this.getNumarray().set(j, this.getNumarray().get(j+1));
                    this.getNumarray().set(j+1, a);
                    flag=1;
                this.getNumarray().set(merged,RArray[y]);
                y++;
                break;
                }
            }
            merged++;
        }
        
        while(x<LeftHalf)
        {
//             value=LArray[x];
//                System.out.println(this.getButtonArr().get(merged).getText()+" "+value);
//                j=this.search(value);
//              {swap(this.getButtonArr().get(merged), this.getButtonArr().get(j));
//                  this.getNumarray().set(merged,LArray[x]);
//                    JButton bb = this.getButtonArr().get(j);
//                    this.getButtonArr().set(j, this.getButtonArr().get(j+1));
//                    this.getButtonArr().set(j+1, bb);
//
//                    int a = this.getNumarray().get(j);
//                    this.getNumarray().set(j, this.getNumarray().get(j+1));
//                    this.getNumarray().set(j+1, a);
//                    flag=1;
//                    //
                  this.getNumarray().set(merged,LArray[x]);  
                 x++;
                 merged++;
//
//                break;
//                }
              
   
        }
        
        while(y<RightHalf)
        {
            this.getNumarray().set(merged,RArray[y]);
            y++;
            merged++;
        }
        
    }
    
    void print(){
        for(int a:array)
           System.out.println(a);
    }
    
        public void sort()  {
        JButton B1 = null, B2 = null;
        int sI=0,sJ=0;

        for(int i=0;i<getNumarray().size()-1;i++){
            for(int j=0;j<getNumarray().size()-i-1;j++){
                if(this.getNumarray().get(j)>this.getNumarray().get(j+1)){
                    swap(this.getButtonArr().get(j), this.getButtonArr().get(j+1));
                    JButton bb = this.getButtonArr().get(j);
                    this.getButtonArr().set(j, this.getButtonArr().get(j+1));
                    this.getButtonArr().set(j+1, bb);

                    int a = this.getNumarray().get(j);
                    this.getNumarray().set(j, this.getNumarray().get(j+1));
                    this.getNumarray().set(j+1, a);
                    flag=1;
                    sI=i;
                    sJ=j;
                    break;

                }

            }
            if(flag==1)break;
        }

        timer = new Timer(DELAY, new MergeSort.MSListener(sI,sJ));
        timer.start();



    }
    
     Timer getBSTimer(){
        return this.timer;
    }
    private class MSListener implements ActionListener {

        private JButton B1;
        private JButton B2;
        private int k=1,m=0;

        MSListener(int i,int j){
            if(j==getNumarray().size()-i-2){
                m=i+1;
                k=0;
            }
            else{
                m=i;
                k=j+1;
            }
        }
        public void actionPerformed(ActionEvent event) {

            if (getTimer() != null && getTimerUp() != null && getTimerDn() != null) {
                if (!getTimer().isRunning() && !getTimerUp().isRunning() && !getTimerDn().isRunning()) {

                    for (int i = 0; i < getNumarray().size()-1; i++) {

                        for (int j = k; j < getNumarray().size()-i-1; j++) {

                            if (getNumarray().get(j) > getNumarray().get(j+1)) {

                                for (JButton jb : getButtonArr()) {
                                    if (Integer.parseInt(jb.getText()) == getNumarray().get(j)) {
                                        B1 = jb;
                                    }
                                    if (Integer.parseInt(jb.getText()) == getNumarray().get(j+1)) {
                                        B2 = jb;
                                    }
                                }


                                JButton b = getButtonArr().get(j);
                                getButtonArr().set(j, getButtonArr().get(j+1));
                                getButtonArr().set(j+1, b);


                                int aa = getNumarray().get(j);
                                getNumarray().set(j, getNumarray().get(j+1));
                                getNumarray().set(j+1, aa);

                                swap(B1, B2);

                                // System.out.println(B1.getText() + " " + B2.getText());
                                //  System.out.println(k + " " + m);
                                // timer.stop();
                            }

                            /*if (k == getNumarray().size() - 1) {
                                m++;
                                k = m;
                            } else {

                                k++;
                            }*/


                            if (m == getNumarray().size() - 1) {
                                timer.stop();
                            }
                            if (getTimer().isRunning() || getTimerUp().isRunning() || getTimerDn().isRunning()) break;
                        }

                        if (getTimer().isRunning() || getTimerUp().isRunning() || getTimerDn().isRunning()) break;
                        k=0;
                    }
                }


            }
        }
    }
    
}

class partitionMovementLeft implements ActionListener{

    JButton button=null;
    int x,y;
    private Timer timerLeft=null,timerDown=null;
    
    partitionMovementLeft(JButton b){
        this.button=b;
        x=button.getLocation().x-2;
        y=button.getLocation().y+2;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
       
        timerLeft=new Timer(100,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setLocation(button.getLocation().x-1,button.getLocation().y);
                
                if(button.getLocation().x==x){
                    timerLeft.stop();
                    timerDown.start();
                }
               
            }
            
            
            
        });
        
        timerDown=new Timer(100,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setLocation(button.getLocation().x,button.getLocation().y+1);
                if(button.getLocation().y==y){
                    timerDown.stop();
                }
               
            }
            
        });
        
        timerLeft.start();
    }
    
    
    
}

class partitionMovementRight implements ActionListener{

    JButton button=null;
    private Timer timerRight=null,timerDown=null;
    
    partitionMovementRight(JButton b){
        this.button=b;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        int x=button.getLocation().x+2;
        int y=button.getLocation().y-2;
        timerRight=new Timer(20,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setLocation(button.getLocation().x+1,button.getLocation().y);
                
                if(button.getLocation().x==x){
                    timerRight.stop();
                    timerDown.start();
                }
               
            }
                        
        });
        
        timerDown=new Timer(20,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setLocation(button.getLocation().x,button.getLocation().y-1);
                if(button.getLocation().y==y){
                    timerDown.stop();
                }
               
            }
            
        });
        
        timerRight.start();
    }
    
}