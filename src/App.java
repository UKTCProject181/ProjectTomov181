import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean endWorkingDay = false;
        System.out.println("How many students would you like to create? :");
        int studentCount = Integer.parseInt(scanner.nextLine());
        Utilities.createStudents(studentCount);
//asd
        //asqwefwqrewq
        System.out.println();
        while(!endWorkingDay) {
            System.out.println("What would you like to do next? ");
            System.out.println("{ 'Insert grades' 'Students information' 'End day' }");
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "insert grades" -> Utilities.addStudentScores();
                case "students information" -> Utilities.printStudentInformation();
                case "end day" -> endWorkingDay = true;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("WORKING DAY FINISHED. GO HOME AND RELAX!");

    }
}
