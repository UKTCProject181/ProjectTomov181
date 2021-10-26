public class Student {

    private String FirstName;
    private String LastName;
    private String CourseNumber;
    private Scores StudentScores;

    public Student(String Name, String LastName , String CourseNumber){
        this.FirstName = Name;
        this.LastName = LastName;
        this.CourseNumber = CourseNumber;
        this.StudentScores = new Scores(0.00f, 0.00f, 0.00f); //implemented at 70th row in Utilities.java
    }

    public String getFirstName() {
        return this.FirstName;
    }
    public String getLastName(){
        return this.LastName;
    }
    public String getCourseNumber(){
        return this.CourseNumber;
    }

    public void setStudentScores(float MathScore, float PhysicsScore, float ProgrammingScore){
        this.StudentScores = new Scores(MathScore, PhysicsScore, ProgrammingScore);
    }

    public String getAllProperties(){
        return "{First name: " + this.FirstName + ", Last name: " + this.LastName + ", Course Number: " + this.CourseNumber + " , " +this.StudentScores.getAllScores() + ", Average Score: " + this.StudentScores.getAverageScore() +" }";
    }


}
