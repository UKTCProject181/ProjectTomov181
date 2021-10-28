import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Classroom implements Serializable {
    private final Scanner scan = new Scanner(System.in);
    ArrayList<Student> students;

    public Classroom() {
        this.students = new ArrayList<Student>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void printAllStudents(){
        for (Student s:students) {
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
        if(scoreToInt >= 2 && scoreToInt <= 6) {
            return String.valueOf(scoreToInt);
        }
        while (true) {
            System.out.print("Please insert valid student grade (2 - 6): ");
            scoreToInt = Integer.parseInt(scan.nextLine());
            if (scoreToInt >= 2 && scoreToInt <= 6) {
                return String.valueOf(scoreToInt);
            }
        }
    }

    public boolean checkValidClassNumberAddGrade(String classNum) {
        int currentArrayPosition = 0;
        if(students.isEmpty()) {
            System.out.println("There are no students added!");
            return false;
        }
        for(Student student : students) {
            if(classNum.equals(student.getClassNumber())) {
                return true;
            }
        }
        while(true) {
            for (Student student : students) {
                currentArrayPosition++;
                if (!classNum.equals(student.getClassNumber())) {
                    System.out.println("Class number doesn't exists!");
                    return false;
                }
                if(currentArrayPosition == students.size()) {
                    return true;
                }
            }
            currentArrayPosition = 0;
        }
    }

    public void addGrade(){
        System.out.print("Enter ClassNumber:");
        String classNum = scan.nextLine();
        if(checkValidClassNumberAddGrade(classNum)) {
            System.out.print("Enter Subject:");
            String subject = scan.nextLine();
            System.out.print("Enter Grade:");
            String grade = scan.nextLine();
            grade = checkValidGrade(grade);
            for (Student student : students) {
                if(student.getClassNumber().equals(classNum)){
                    student.grades.add(new Grade(subject, grade));
                }
            }
        }
    }

    public String checkValidClassNumberAddStudents(String classNum) {
        int currentArrayPosition = 0;
        while(true) {
            if(students.isEmpty()) {
                return classNum;
            }
            for (Student student : students) {
                currentArrayPosition++;
                if (classNum.equals(student.getClassNumber())) {
                    System.out.println("Class number already exists!");
                    System.out.print("Enter new one: ");
                    classNum = scan.nextLine();
                    currentArrayPosition = 0;
                }
                if(currentArrayPosition == students.size()) {
                    return classNum;
                }
            }
            currentArrayPosition = 0;
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
            classNumber = checkValidClassNumberAddStudents(classNumber);
            this.students.add(new Student(firstName, lastName, classNumber));
        }
    }

    public void endProgram() {
        try{
            FileOutputStream writeData = new FileOutputStream("output.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(this.students);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startProgram() {
        try{
            FileInputStream readData = new FileInputStream("output.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            this.students = (ArrayList<Student>) readStream.readObject();
            readStream.close();

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}