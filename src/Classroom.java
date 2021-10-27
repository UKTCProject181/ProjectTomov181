import java.util.ArrayList;
import java.util.Scanner;

public class Classroom {
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
                System.out.println("Subject: "+g.getSubject()+" - Grade: "+g.getMark());
            }
            System.out.println();
        }
    }

    public String checkValidMark(String mark) { //validation - String mark
        Scanner scan = new Scanner(System.in);
        int scoreToInt = Integer.parseInt(mark);
        boolean check = false;
        if(scoreToInt >= 2 && scoreToInt <= 6) {
            return String.valueOf(scoreToInt);
        }
        while (!check) {
            System.out.print("Please insert valid student mark (2 - 6): ");
            scoreToInt = Integer.parseInt(scan.nextLine());
            if (scoreToInt >= 2 && scoreToInt <= 6) {
                check = true;
            }
        }
        return String.valueOf(scoreToInt);
    }

    public void addGrade(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter ClassNumber:");
        String classNum = scan.nextLine();
        System.out.print("Enter Subject:");
        String subject = scan.nextLine();
        System.out.print("Enter Mark:");
        String mark = scan.nextLine();
        mark = checkValidMark(mark);
        for (Student student : students) {
            if(student.getClassNumber().equals(classNum)){
                student.grades.add(new Grade(subject, mark));
            }
        }
    }

    public String checkValidClassNumber(String classNum) { //validation - String classNumber
        Scanner scan = new Scanner(System.in);
        boolean check = false;
        String classNumMethod = classNum;
        int counter = 0;
        while(!check) {
            if(students.isEmpty()) {
                return classNumMethod;
            }
            for (Student student : students) {
                counter++;
                if (classNumMethod.equals(student.getClassNumber())) {
                    System.out.println("Class number already exists!");
                    System.out.print("Enter new one: ");
                    classNumMethod = scan.nextLine();
                    counter = 0;
                }
                if(counter == students.size()) {
                    check = true;
                    break;
                }
            }
            counter = 0;
        }
        return classNumMethod;
    }

    public void addStudents(){
        Scanner scan = new Scanner(System.in);
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
            classNumber = checkValidClassNumber(classNumber);
            this.students.add(new Student(firstName, lastName, classNumber));
        }
    }
}