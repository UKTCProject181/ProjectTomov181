import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Classroom classroom = new Classroom();
        System.out.println("e-Journal");
        System.out.println("{Add students}, {Add grades}, {Print students}, {End program}");
        String choice = scan.nextLine();
        while(!choice.toLowerCase().equals("end program")) {
            switch (choice.toLowerCase()) {
                case "add students" -> {
                    classroom.addStudents();
                }
                case "print students" -> {
                    classroom.printAllStudents();
                }

                case "add grades" -> {
                    classroom.addGrade();
                }

            }
            System.out.println();
            System.out.println("{Add students}, {Add grades}, {Print students}, {End program}");
            choice = scan.nextLine();
        }
    }
}
