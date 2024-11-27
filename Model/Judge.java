package Model;

/**
 * Represents a judge in the Court House Management System.
 */
public class Judge extends Person {
    private String judgeID;
    private String specialty;

    public Judge(String judgeID, String name, String specialty) {
        super(name);
        this.judgeID = judgeID;
        this.specialty = specialty;
    }

    public String getJudgeID() {
        return judgeID;
    }

    public void setJudgeID(String judgeID) {
        this.judgeID = judgeID;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * Overrides the abstract method to display judge details.
     */
    @Override
    public String getDetails() {
        return "Judge ID: " + judgeID + ", Name: " + getName() + ", Specialty: " + specialty;
    }
}
