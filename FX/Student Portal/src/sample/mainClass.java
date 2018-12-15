package sample;

import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;


public class mainClass extends Application  {

    Stage window;
    TableView<Student> table_inst4;
    TableView<Student> table_inst2;
    Scene scene1,scene2,scene3,scene4,scene5,scene6,scene7,scene8;
    int k=5;
    int m=0;
    int n=k;
    int m2=0;
    int n2=k;
    ArrayList<Student> st1;
    ArrayList<Student> st2;
    String view=null;
    TextField t21,t22,t23,t24;
    Student S5=null;
    String rollnumber1;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("kiosk");

        //***************************************************************************************

        //                                SCENE 1


        Label l1 = new Label("      Welcome To The Portal !");

        ComboBox c1 = new ComboBox();
        c1.setValue("Which view do you want?");
        c1.getItems().add("Student");
        c1.getItems().add("Instructor");
        c1.getItems().add("Summary");
        c1.getSelectionModel().selectedItemProperty().addListener((options,oldvalue,newvalue)->{
            view = newvalue.toString();
            if (view.equals("Student")){
                window.setScene(scene4);

            }
            else if(view.equals("Instructor")){
                window.setScene(scene2);
            }
            else{
                window.setScene(scene6);
            }
        });




        GridPane gp1 = new GridPane();
        gp1.add(l1,0,0,1,1);
        gp1.add(c1,0,3,1,1);
        gp1.setVgap(20);
        gp1.setHgap(60);
        gp1.setPadding(new Insets(40));
        scene1 = new Scene(gp1,300,250);










        //************************************************************************************



        //                               SCENE 2 INSTRUCTOR




        //roll column
        TableColumn<Student, String > rollcolumn = new TableColumn<>("Roll No.");
        rollcolumn.setMinWidth(200);
        rollcolumn.setCellValueFactory(new PropertyValueFactory<>("roll"));

        //marks column
        TableColumn<Student, Integer > markscolumn = new TableColumn<>("Marks");
        markscolumn.setMinWidth(180);
        markscolumn.setCellValueFactory(new PropertyValueFactory<>("marks"));

        //student input


        table_inst2 = new TableView<>();
        table_inst2.getColumns().addAll(rollcolumn,markscolumn);

        Button b25 = new Button(" Add ");
        b25.setOnAction(e-> {
            try {
                addButtonClicked();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        b25.setVisible(false);
        b25.setManaged(false);
        b25.managedProperty().bind(b25.visibleProperty());
        Button b26 = new Button("    Delete    ");
        b26.setOnAction(e-> deleteButtonClicked());
        b26.setVisible(false);
        b26.setManaged(false);
        b26.managedProperty().bind(b26.visibleProperty());
        t22 = new TextField();
        t22.setPromptText("      Enter a roll no.");
        t22.setManaged(false);
        t23 = new TextField();
        t23.setPromptText("     Enter Marks");
        t23.setManaged(false);

        t24 = new TextField();
        t24.setPromptText("                    Enter new marks");
        t24.setManaged(false);

        Button b28 = new Button("  Set Marks ");
        b28.setVisible(false);
        b28.setManaged(false);
        b28.managedProperty().bind(b28.visibleProperty());
        b28.setOnAction(e-> {
            try {
                setMarksClicked();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        t21 = new TextField();
        t21.setPromptText("Enter the roll no.   ");
        Button b22 = new Button("Search");
        b22.setOnAction(e -> {
            String rollnumber=t21.getText();
            Student S3=null;
            for(Student i:st1) if(i.getRoll().equals(rollnumber)) S3 = i;

            GridPane gp3 = new GridPane();
            Label l31 = new Label("Student Details");
            l31.setMinWidth(250);
            Label l32 = new Label("Roll No.");
            Label l33 = new Label(rollnumber);
            Label l34 = new Label("Marks");
            Label l35 = new Label(S3.toString());

            gp3.add(l31,0,0,4,1);
            gp3.add(l32,0,1,2,1);
            gp3.add(l33,2,1,2,1);
            gp3.add(l34,0,2,2,1);
            gp3.add(l35,2,2,2,1);
            gp3.setHgap(16);
            gp3.setVgap(10);
            gp3.setPadding(new Insets(25));
            gp3.setMaxWidth(500);
            scene3 = new Scene(gp3);
            window.setScene(scene3);
        });
        Button b23 = new Button("<- previous");
        b23.setOnAction(e -> {
            m-=5;
            n-=5;
            ObservableList<Student> data = getStudentsInst();

            ObservableList<Student> data1 =FXCollections.observableArrayList();
            for(int i=n;i<m;i++){
                data1.add(data.get(i));
            }
            table_inst2.setItems(data1);
        });
        Button b24 = new Button("next ->");
        b24.setOnAction(e -> {
            m+=5;
            n+=5;
            ObservableList<Student> data = getStudentsInst();
            ObservableList<Student> data1 =FXCollections.observableArrayList();
            for(int i=n;i<m;i++){
                data1.add(data.get(i)) ;
            }
            table_inst2.setItems(data1);
        });
        Button b21 = new Button("Edit");
        Button b27 = new Button("Save");
        b27.setManaged(false);
        b27.setOnAction(e->{
            b25.setVisible(false);
            b26.setVisible(false);
            b21.setManaged(true);
            b21.setVisible(true);
            b27.setManaged(false);
            b27.setVisible(false);
            t22.setManaged(false);
            t22.setVisible(false);
            t23.setManaged(false);
            t23.setVisible(false);
            b28.setVisible(false);
            t24.setManaged(false);
            t24.setVisible(false);
        });


        b21.setOnAction(e -> {
            b25.setVisible(true);
            b26.setVisible(true);
            b21.setManaged(false);
            b21.setVisible(false);
            b27.setManaged(true);
            t22.setManaged(true);
            t22.setVisible(true);
            t23.setManaged(true);
            t23.setVisible(true);
            b28.setVisible(true);
            t24.setManaged(true);
            t24.setVisible(true);
            b27.setManaged(true);
            b27.setVisible(true);
            window.setMinWidth(580);

        });
        ObservableList<Student> data = getStudentsInst();
        ObservableList<Student> data1 =FXCollections.observableArrayList();
        for(int i=m;i<n;i++){
            data1.add(data.get(i));

        }
        table_inst2.setItems(data1);


        GridPane gp2 = new GridPane();
        gp2.add(table_inst2,0,0,6,6);
        gp2.add(b23,0,7,1,1);
        gp2.add(b21,1,7,1,1);
        gp2.add(b27,1,7,1,1);
        gp2.add(t21,2,7,2,1);
        gp2.add(b22,4,7,1,1);
        gp2.add(b24,5,7,1,1);
        gp2.add(t22,2,9,1,1);
        gp2.add(t23,4,9,2,1);
        gp2.add(b25,1,9,1,1);
        gp2.add(b26,0,9,1,1);
        gp2.add(b28,0,8,1,1);
        gp2.add(t24,1,8,4,1);


        gp2.setHgap(16);
        gp2.setVgap(10);
        gp2.setPadding(new Insets(25));
        gp2.setMaxWidth(500);
        scene2 = new Scene(gp2);


        //*************************************************************************************************


        //                                  SCENE 4 CHILD PORTAL

        //roll column
        TableColumn<Student, String > roll1column = new TableColumn<>("Roll No.");
        roll1column.setMinWidth(200);
        roll1column.setCellValueFactory(new PropertyValueFactory<>("roll"));

        //q1 marks column
        TableColumn<Student, Integer > q1column = new TableColumn<>("Q1");
        q1column.setMinWidth(180);
        q1column.setCellValueFactory(new PropertyValueFactory<>("q1"));

        //q2 marks column
        TableColumn<Student, Integer > q2column = new TableColumn<>("Q2");
        q2column.setMinWidth(180);
        q2column.setCellValueFactory(new PropertyValueFactory<>("q2"));

        table_inst4 = new TableView<>();
        n=0;
        m=5;

        table_inst4.getColumns().addAll(roll1column,q1column,q2column);


        TextField t41 = new TextField();
        t41.setPromptText("Enter a roll no.");
        Button b42 = new Button("Search");
        b42.setOnAction(e -> {
            rollnumber1 = t41.getText();
            for(Student i:st2) if (i.getRoll().equals(rollnumber1)) S5=i;

            GridPane gp5 = new GridPane();
            Label l51 = new Label("Student Details");
            l51.setMinWidth(250);
            Label l52 = new Label("Roll No.");
            Label l53 = new Label(rollnumber1);
            Label l54 = new Label("Ques 1");
            if (S5 != null) {
                Label l55 = new Label(S5.getq1MarksString());
                gp5.add(l55,2,2,2,1);
            }
            Label l56 = new Label("Ques 2");
            if (S5 != null) {
                Label l57 = new Label(S5.getq2MarksString());
                gp5.add(l57,2,3,2,1);
            }

            gp5.add(l51,0,0,4,1);
            gp5.add(l52,0,1,2,1);
            gp5.add(l53,2,1,2,1);
            gp5.add(l54,0,2,2,1);
            gp5.add(l56,0,3,2,1);

            gp5.setHgap(16);
            gp5.setVgap(10);
            gp5.setPadding(new Insets(25));
            gp5.setMaxWidth(500);
            scene5 = new Scene(gp5);
            window.setScene(scene5);
        });
        Button b53 = new Button("<- previous");
        b53.setOnAction(e->{
            m2-=5;
            n2-=5;
            ObservableList<Student> data2 = getStudentsStu();
            ObservableList<Student> data3 =FXCollections.observableArrayList();
            for(int i=m2;i<n2;i++){
                data3.add(data2.get(i)) ;
            }
            table_inst4.setItems(data3);
        });
        Button b54 = new Button("next ->");
        b54.setOnAction(e->{
            m2+=5;
            n2+=5;
            ObservableList<Student> data2 = getStudentsStu();
            ObservableList<Student> data3 =FXCollections.observableArrayList();
            for(int i=m2;i<n2;i++){
                data3.add(data2.get(i)) ;
            }
            table_inst4.setItems(data3);
        });
        ObservableList<Student> data2 = getStudentsStu();
        ObservableList<Student> data3 =FXCollections.observableArrayList();
        for(int i=m2;i<n2;i++){
            data3.add(data2.get(i)) ;
        }
        table_inst4.setItems(data3);

        GridPane gp4 = new GridPane();
        gp4.add(table_inst4,0,0,6,6);
        gp4.add(b53,0,7,1,1);
        gp4.add(t41,1,7,3,1);
        gp4.add(b42,4,7,1,1);
        gp4.add(b54,5,7,1,1);
        gp4.setHgap(16);
        gp4.setVgap(10);
        gp4.setPadding(new Insets(25));
        gp4.setMaxWidth(500);
        scene4 = new Scene(gp4);

        //************************************************************************************************
        //                                  SCENE 6 SUMMARY

        ComboBox<String> c6 = new ComboBox<>();
        GridPane gp6 = new GridPane();
        c6.setValue("Staticstical Inference");
        c6.getItems().add("Mean");
        c6.getItems().add("Median");
        c6.getItems().add("Minimum");
        c6.getItems().add("Maximum");
        c6.getSelectionModel().selectedItemProperty().addListener((options,oldvalue,newvalue)-> {
                    view = newvalue.toString();
                    if(view.equals("Mean")){
                        GridPane gp7 = new GridPane();

                        float str1=getMeanq1();
                        float str2=getMeanq2();
                        Label l71 = new Label("Question 1 : " + str1);
                        Label l72 = new Label("Question 2 : " + str2);
                        Label dummy = new Label(" ");
                        gp7.add(l71,1,1,1,1);
                        gp7.add(l72,1,2,1,1);
                        gp7.setVgap(20);
                        gp7.setHgap(40);
                        gp7.setPadding(new Insets(40));

                        scene7 = new Scene(gp7);
                        window.setScene(scene7);
                    }
                    else if(view.equals("Median")){
                        GridPane gp7 = new GridPane();
//
                        float str1=getMedianq1();
                        float str2=getMedianq2();
                        Label l71 = new Label("Question 1 : " + str1);
                        Label l72 = new Label("Question 2 : " + str2);
                        Label dummy = new Label(" ");
                        gp7.add(l71,1,1,1,1);
                        gp7.add(l72,1,2,1,1);
                        gp7.setVgap(20);
                        gp7.setHgap(40);
                        gp7.setPadding(new Insets(40));

                        scene7 = new Scene(gp7);
                        window.setScene(scene7);
                    }
                    else if(view.equals("Minimum")){
                        GridPane gp7 = new GridPane();

                        int str1=getMinimumq1();
                        int str2=getMinimumq2();
                        Label l71 = new Label("Question 1 : " + str1);
                        Label l72 = new Label("Question 2 : " + str2);
                        Label dummy = new Label(" ");
                        gp7.add(l71,1,1,1,1);
                        gp7.add(l72,1,2,1,1);
                        gp7.setVgap(20);
                        gp7.setHgap(40);
                        gp7.setPadding(new Insets(40));

                        scene7 = new Scene(gp7);
                        window.setScene(scene7);
                    }
                    else{
                        GridPane gp7 = new GridPane();

                        int str1=getMaximumq1();
                        int str2=getMaximumq2();
                        Label l71 = new Label("Question 1 : " + str1);
                        Label l72 = new Label("Question 2 : " + str2);
                        Label dummy = new Label(" ");
                        gp7.add(l71,1,1,1,1);
                        gp7.add(l72,1,2,1,1);
                        gp7.setVgap(20);
                        gp7.setHgap(40);
                        gp7.setPadding(new Insets(40));

                        scene7 = new Scene(gp7);
                        window.setScene(scene7);
                    }
        });



        gp6.add(c6,1,0,2,1);

        scene6 = new Scene(gp6);

        //***************************************************************************************************
        window.setScene(scene1);
        window.show();


    }

    public void up(int n,int m,int k){
        n+=k;
        m+=k;
    }

    public void down(int n,int m,int k){
        n-=k;
        m-=k;
    }


    //main
    public static void main(String[] args) throws FileNotFoundException {

        launch(args);

    }

    //get the students
    public ObservableList<Student> getStudentsInst()  {

        Scanner input = null;
        Scanner input2;

        ObservableList<Student> students = FXCollections.observableArrayList();
        //READING PROVIDED FILE
        st1 = new ArrayList<>();

        try {
            input = new Scanner(new File("src/provided.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(input.hasNext()){
            input2 = new Scanner(input.nextLine());
            input2.useDelimiter(",");
            int index=0;
            String roll=null;
            int marks=0;
            while(input2.hasNext()){
                if(index==0){
                    roll = input2.next();
                    index++;
                }
                else{
                    marks=input2.nextInt();
                    Student S  = new Student(roll,marks);
                    index=0;
                    st1.add(S);
                }
            }
        }
//        students.add(new Student("iit2017048",48));
//        students.add(new Student("iit2017047",47));

        for(int i=0;i<st1.size();i++){
            if(st1.get(i)!=null)students.add(st1.get(i));
        }

        return students;
    }

    public ObservableList<Student> getStudentsStu(){

        ObservableList<Student>students = FXCollections.observableArrayList();
        st2 = new ArrayList<>();
        //READING GENERARED FILE
        Scanner input = null;
        try {
            input = new Scanner(new File("src/generated.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(input.hasNext()){
            Scanner input2 = new Scanner(input.nextLine());
            input2.useDelimiter(",");
            int index=0;
            String roll=null;
            int marks1=0;
            while(input2.hasNext()){
                if(index==0) {
                    roll = input2.next();
                    index++;
                }
                else if (index==1){
                    marks1 = input2.nextInt();
                    index++;
                }
                else{
                    int marks2 = input2.nextInt();
                    Student S = new Student(roll);
                    S.setQ1(marks1);
                    S.setQ2(marks2);
                    index=0;
                    st2.add(S);
                }
            }
        }
        for(int i=0;i< st2.size();i++){
            if(st2.get(i)!=null) {
                students.add(st2.get(i));
            }
        }

        return students;
    }

    public void addButtonClicked() throws IOException {
        Student S = new Student();
        S.setRoll(t22.getText());
        S.setMarks(Integer.parseInt(t23.getText()));
        table_inst2.getItems().add(S);
        st1.add(S);
        t22.clear();
        t23.clear();
        updateCSV();
    }

    public void deleteButtonClicked(){
        ObservableList<Student>studentsSelected,allstudents;
        allstudents = table_inst2.getItems();
        studentsSelected = table_inst2.getSelectionModel().getSelectedItems();
        studentsSelected.forEach(allstudents::remove);
        st1.remove(studentsSelected);

    }

    public void setMarksClicked() throws FileNotFoundException {

        Student S = table_inst2.getSelectionModel().getSelectedItem();
        ObservableList<Student>all;
        all = table_inst2.getItems();
        all.remove(S);
        S.setMarks(Integer.parseInt(t24.getText()));
        table_inst2.getItems().add(S);

        t24.clear();
    }

    public void updateCSV() throws IOException {
        FileWriter fw = new FileWriter("/home/arsh/IdeaProjects/javaFXdemo/src/provided.csv");
        for(Student i:st1){
            String s = i.getRoll() + "," + i.getMarks();
            for(int j=0;j<s.length();j++){
                fw.write(s.charAt(j));
            }
            fw.write('\n');
        }
        fw.close();

    }

    public float getMeanq1(){
        getStudentsStu();
        int sum=0;
        for(Student j:st2) sum+=j.getQ1();
        float d = (float)sum/st2.size();
        return d;
    }

    public float getMeanq2(){

            getStudentsStu();

        int sum=0;
        for(Student j:st2) sum+=j.getQ2();
        float d = (float)sum/st2.size();
        return d;

    }

    public float getMedianq1(){
        getStudentsStu();
        int arr[] = new int[1000];
        int counter=0;
        for(Student j:st2) arr[counter++]=j.getQ1();
        Arrays.sort(arr);
        System.out.println(st2.get(0));
        System.out.println(arr[0] + arr[1]);
        System.out.println(arr[(st2.size()-1)/2]);
        if(st2.size()%2!=0) return arr[(st2.size()-1)/2];
        else return (arr[(st2.size()-2)/2] + arr[st2.size()/2])/2;
    }

    public float getMedianq2(){
        getStudentsStu();
        int arr[] = new int[1000];
        int counter=0;
        for(Student j:st2) arr[counter++]=j.getQ2();
        Arrays.sort(arr);
        if(st2.size()%2!=0) return arr[(st2.size()-1)/2];
        else return (arr[(st2.size()-2)/2] + arr[st2.size()/2])/2;
    }

    public int getMinimumq1(){
        getStudentsStu();
        int dummy=99999;
        for(Student j:st2) if(j.getQ1() < dummy) dummy = j.getQ1();
        return dummy;
    }
    public int getMinimumq2(){
        getStudentsStu();
        int dummy=99999;
        for(Student j:st2) if(j.getQ2() < dummy) dummy = j.getQ2();
        return dummy;
    }

    public int getMaximumq1(){
        getStudentsStu();
        int dummy=-1;
        for(Student j:st2) if(j.getQ1() > dummy) dummy = j.getQ1();
        return dummy;
    }
    public int getMaximumq2(){
        getStudentsStu();
        int dummy=-1;
        for(Student j:st2) if(j.getQ2() > dummy) dummy = j.getQ2();
        return dummy;
    }




}
