import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        Classroom classroom = new Classroom("181");
        System.out.println("============================== e-Journal ==============================");
        System.out.println("{Add students}(1)                                   {Print students}(3)\n" +
                           "{Add grade}(2)                                      {End program}(4)");
        String choice = scan.nextLine();
        while(!choice.toLowerCase().equals("4")) {
            switch (choice.toLowerCase()) {
                case "1" -> classroom.addStudents();
                case "2" -> classroom.addGrade();
                case "3" -> classroom.printAllStudents();
            }
            System.out.println();
            System.out.println("{Add students}(1)                                   {Print students}(3)\n" +
                               "{Add grade}(2)                                      {End program}(4)");
            choice = scan.nextLine();
        }
        classroom.endProgram();
    }
}