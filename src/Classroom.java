import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Classroom implements Serializable {
    private final Scanner scan = new Scanner(System.in);
    ArrayList<Student> students;
    private String paralelka;
    private File info;


    //
    public Classroom(String paralelka) {
        this.paralelka = paralelka;
        this.info = new File(paralelka + ".bin");
        this.students = readStudentsFile();
    }

    public ArrayList<Student> readStudentsFile() {
        try {
            if (this.info.createNewFile()) {
                return new ArrayList<Student>();
            } else if (this.info.length() != 0) {
                try {
                    ObjectInputStream is = new ObjectInputStream(new FileInputStream(this.info.getName()));
                    ArrayList<Student> students = (ArrayList<Student>) is.readObject();
                    is.close();
                    return students;
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("The program will not work properly");
                    return new ArrayList<Student>();
                }
            } else {
                return new ArrayList<Student>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The program will not work properly");
            return new ArrayList<Student>();
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void printAllStudents() {
        if (this.students.isEmpty()) {
            System.out.println("There are no students!");
            return;
        }
        for (Student s : this.students) {
            System.out.println("First name: " + s.getFirstName());
            System.out.println("Last name: " + s.getLastName());
            System.out.println("ClassNumber: " + s.getClassNumber());
            for (Grade g : s.grades) {
                System.out.println("Subject: " + g.getSubject() + " - Grade: " + g.getGrade());
            }
            System.out.println();
        }
    }

    public int checkValidGrade(Integer grade) {
        while (true) {
            if (grade >= 2 && grade <= 6) {
                return grade;
            }
            System.out.print("Please enter valid student grade (2 - 6): ");
            grade = Integer.parseInt(scan.nextLine());
        }
    }

    public boolean checkValidSubject(String classNum, String subject) {
        if (students.isEmpty()) {
            return false;
        }
        for (Student student : students) {
            if (student.getClassNumber().equals(classNum)) {
                for (Grade g : student.grades) {
                    if (g.getSubject().equals(subject)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public boolean checkValidClassNumber(String classNum) {
        if (students.isEmpty()) {
            return false;
        }
        for (Student student : students) {
            if (classNum.equals(student.getClassNumber())) {
                return true;
            }
        }
        return false;
    }

    public void removeGrade(){
        if(this.students.isEmpty()){
            System.out.println("There are no students");
            return;
        }
        System.out.println("Enter ClassNumber: ");
        String classNum = scan.nextLine();
        if(checkValidClassNumber(classNum)){
            System.out.println("Enter Subject: ");
            String subject = scan.nextLine();
            if(checkValidSubject(classNum, subject)){
                System.out.println("What grade do you want to remove: ");
                int chosengrade = checkValidGrade(Integer.parseInt(scan.nextLine()));
                for (Student student : students) {
                    if(student.getClassNumber().equals(classNum)) {
                        for(Grade g : student.grades){
                            if(g.getSubject().equals(subject)){
                                if(g.getGrade().size()==1 && g.getGrade().contains(chosengrade)){
                                    student.grades.remove(g);
                                }else {
                                    g.getGrade().remove(g.getGrade().indexOf(chosengrade));
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public void addGrade() {
        if (students.isEmpty()) {
            System.out.println("There are no students!");
            return;
        }
        System.out.print("Enter ClassNumber:");
        String classNum = scan.nextLine();
        if (checkValidClassNumber(classNum)) {
            System.out.print("Enter Subject:");
            String subject = scan.nextLine();
            System.out.print("Enter Grade:");
            int grade = checkValidGrade(Integer.parseInt(scan.nextLine()));
            for (Student student : students) {
                if (student.getClassNumber().equals(classNum)) {
                    for (Grade g : student.grades) {
                        if (g.getSubject().equals(subject)) {
                            g.getGrade().add(grade);
                            return;
                        }
                    }
                    ArrayList<Integer> gradelist = new ArrayList<Integer>();
                    gradelist.add(grade);
                    student.grades.add(new Grade(subject, gradelist));
                }
            }
        } else {
            System.out.println("There is no student with that ClassNumber!");
        }
    }

    public void addStudents() {
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
            while (true) {
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

    public void removeStudents(){
        System.out.println("Enter the class number of the student you want to remove?");
        String classNum = scan.nextLine();
        if(checkValidClassNumber(classNum)){
            for (Student student : students) {
                if(student.getClassNumber().equals(classNum)){
                    students.remove(student);
                    return;
                }
            }
        }
    }

    public void endProgram() {
        try {
            ObjectOutputStream writeStream = new ObjectOutputStream(new FileOutputStream(this.info.getName()));
            writeStream.writeObject(this.students);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}