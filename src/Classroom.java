import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Classroom implements Serializable {
    private final Scanner scan = new Scanner(System.in);
    ArrayList<Student> students;
    private String paralelka;
    private File info;

    public Classroom(String paralelka) throws IOException {
        this.paralelka = paralelka;
        this.info = new File(paralelka+".txt");
        if(this.info.createNewFile()){
            this.students = new ArrayList<Student>();
        }else if(this.info.length()!=0){
            try{
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(this.info.getName()));
                this.students = (ArrayList<Student>) is.readObject();
                is.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            this.students = new ArrayList<Student>();
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void printAllStudents(){
        if(this.students.isEmpty()){
            System.out.println("There are no students!");
            return;
        }
        for (Student s:this.students) {
            System.out.println("First name: "+s.getFirstName());
            System.out.println("Last name: "+s.getLastName());
            System.out.println("ClassNumber: "+s.getClassNumber());
            for(Grade g:s.grades){
                System.out.println("Subject: "+g.getSubject()+" - Grade: "+g.getGrade());
            }
            System.out.println();
        }
    }

    public String checkValidGrade(String grade) {
        int scoreToInt = Integer.parseInt(grade);
        while (true) {
            if(scoreToInt >= 2 && scoreToInt <= 6) {
                return String.valueOf(scoreToInt);
            }
            System.out.print("Please enter valid student grade (2 - 6): ");
            scoreToInt = Integer.parseInt(scan.nextLine());
        }
    }

    public boolean checkValidClassNumber(String classNum) {
        if(students.isEmpty()) {
            return false;
        }
        for(Student student : students) {
            if(classNum.equals(student.getClassNumber())) {
                return true;
            }
        }
        return false;
    }

    public void addGrade(){
        if(students.isEmpty()){
            System.out.println("There are no students!");
            return;
        }
        System.out.print("Enter ClassNumber:");
        String classNum = scan.nextLine();
        if(checkValidClassNumber(classNum)) {
            System.out.print("Enter Subject:");
            String subject = scan.nextLine();
            System.out.print("Enter Grade:");
            String grade = scan.nextLine();
            grade = checkValidGrade(grade);
            for (Student student : students) {
                if(student.getClassNumber().equals(classNum)) {
                    student.grades.add(new Grade(subject, grade));
                }
            }
        }else{
            System.out.println("There is no student with that ClassNumber!");
        }
    }

    public void addStudents(){
        System.out.print("How many students do you want to add: ");
        int count = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.printf("\nStudent <%d>: \n", i + 1);
            System.out.print("First name: ");
            String firstName = scan.nextLine();
            System.out.print("Last name: ");
            String lastName = scan.nextLine();
            System.out.print("ClassNumber: ");
            String classNumber = scan.nextLine();
            while(true) {
                if (!checkValidClassNumber(classNumber)) {
                    break;
                } else {
                    System.out.println("This number already exists!");
                    System.out.print("ClassNumber: ");
                    classNumber = scan.nextLine();
                }
            }
            Student newStudent = new Student(firstName, lastName, classNumber);
            this.students.add(newStudent);
        }
    }

    public void endProgram() {
        try{
            ObjectOutputStream writeStream = new ObjectOutputStream(new FileOutputStream(this.info.getName()));
            writeStream.writeObject(this.students);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}