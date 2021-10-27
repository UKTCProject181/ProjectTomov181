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

    public void addGrade(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter ClassNumber:");
        String classNum = scan.nextLine();
        System.out.println("Enter Subject:");
        String subject = scan.nextLine();
        System.out.println("Enter Mark:");
        String mark = scan.nextLine();
        for (Student student : students) {
            if(student.getClassNumber().equals(classNum)){
                student.grades.add(new Grade(subject, mark));
            }
        }
    }

    public void addStudents(){
        Scanner scan = new Scanner(System.in);
        System.out.printf("How many students do you want to add: ");
        int count = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.println("First name: ");
            String firstName = scan.nextLine();
            System.out.println("Last name: ");
            String lastName = scan.nextLine();
            System.out.println("ClassNumber: ");
            String classNumber = scan.nextLine();
            this.students.add(new Student(firstName, lastName, classNumber));
        }
    }
}
