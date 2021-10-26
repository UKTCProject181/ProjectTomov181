import java.util.ArrayList;
import java.util.Scanner;

public class Utilities {

    private static ArrayList<Student> students = new ArrayList<Student>();

    public static void createStudents(int numberOfStudentsToCreate){
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numberOfStudentsToCreate ; i++){
            //get new student data
            System.out.print("Enter student's first name: ");
            String newStudentFirstName = scanner.nextLine();
            System.out.print("Enter student's last name: ");
            String newStudentLastName = scanner.nextLine();
            System.out.print("Enter student's course number: ");
            String newStudentCourseNumber = scanner.nextLine();
            System.out.println();

            //create student with data
            Student newStudent = new Student(newStudentFirstName, newStudentLastName,newStudentCourseNumber);

            //push new student to global class variable
            students.add(newStudent);

        }
    }

    public static float checkValidScores(float score) {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;

        //skip while if score is valid
        if(score >= 2.00f && score <= 6.00f) {
            return score;
        }
            while (!check && !(score >= 2.00f && score <= 6.00f)) {
                System.out.print("Please insert valid student score (2.00 - 6.00): ");
                score = scanner.nextFloat();
                if (score >= 2.00f && score <= 6.00f) {
                    check = true;
                }
            }
        return score;
    }

    public static void addStudentScores(){
        Scanner scanner = new Scanner(System.in);
        //looping students and their scores
        for (Student student : students) {
            System.out.println("Enter scores for student: " + student.getFirstName() + " " + student.getLastName() + " " + student.getCourseNumber());
            try {
                System.out.print("Enter Math Score: ");
                float mathScore = scanner.nextFloat();
                mathScore = checkValidScores(mathScore); //validating score
                System.out.print("Enter Physics Score: ");
                float physicsScore = scanner.nextFloat();
                physicsScore = checkValidScores(physicsScore); //validating score
                System.out.print("Enter Programming Score: ");
                float programmingScore = scanner.nextFloat();
                programmingScore = checkValidScores(programmingScore); //validating score
                System.out.println();
                student.setStudentScores(mathScore, physicsScore, programmingScore);
            } catch(Exception e) {
                System.out.println("Error accused while inserting " + student.getFirstName() + "'s grades!");
                student.setStudentScores(0.00f, 0.00f, 0.00f);
            }
        }
    }


    public static void printStudentInformation(){
        for (Student student: students) {
            System.out.println(student.getAllProperties());
        }
    }


}
