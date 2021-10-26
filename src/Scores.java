public class Scores {
    private float math;
    private float physics;
    private float programming;

    public String getAverageScore(){
        return String.format("%.2f", (this.math + this.programming + this.physics) / 3); //return #.## string
    }

    public String getAllScores(){
        return "{ Math: " + this.math + ", Physics: " + this.physics + ", Programming: " + this.programming + " }";
    }


    public Scores(float MathScore ,float PhysicsScore,float ProgrammingScore ){
        this.math = MathScore;
        this.physics = PhysicsScore;
        this.programming = ProgrammingScore;
//FFFF
    }


}