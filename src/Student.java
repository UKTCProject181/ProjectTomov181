public class Student {

    private String firstName;
    private String lastName;
    private String courseNumber;
    private Scores studentScores;

    public Student(String Name, String LastName , String CourseNumber){
        this.firstName = Name;
        this.lastName = LastName;
        this.courseNumber = CourseNumber;
        this.studentScores = new Scores(0.00f, 0.00f, 0.00f); //implemented at 70th row in Utilities.java
    }

    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getCourseNumber(){
        return this.courseNumber;
    }

    public void setStudentScores(float MathScore, float PhysicsScore, float ProgrammingScore){
        this.studentScores = new Scores(MathScore, PhysicsScore, ProgrammingScore);
    }

    public String getAllProperties(){
        return "{First name: " + this.firstName + ", Last name: " + this.lastName + ", Course Number: " + this.courseNumber + " , " +this.studentScores.getAllScores() + ", Average Score: " + this.studentScores.getAverageScore() +" }";
    }


}
