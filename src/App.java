import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Classroom classroom = new Classroom("181");
        System.out.println("============================== e-Journal ==============================");
        System.out.println(
                "{Add students}(1)                                   {Print students}(4)\n" +
                        "{Add grade}(2)                                      {End program}(5)\n" +
                        "{Remove grade}(3)                                   {Remove student(6)}");
        String choice = scan.nextLine();
        while(!choice.toLowerCase().equals("5")) {
            switch (choice.toLowerCase()) {
                case "1" -> classroom.addStudents();
                case "2" -> classroom.addGrade();
                case "3" -> classroom.removeGrade();
                case "4" -> classroom.printAllStudents();
                case "6" -> classroom.removeStudents();
            }
            System.out.println();
            System.out.println(
                    "{Add students}(1)                                   {Print students}(4)\n" +
                            "{Add grade}(2)                                      {End program}(5)\n" +
                            "{Remove grade}(3)                                   {Remove student(6)}");
            choice = scan.nextLine();
        }
        classroom.endProgram();
    }
}