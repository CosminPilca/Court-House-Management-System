package Model;

/**
 * Represents a judge in the Court House Management System.
 */
public class Judge {
    private String judgeID;
    private String name;
    private String specialty;

    public Judge(String judgeID, String name, String specialty) {
        this.judgeID = judgeID;
        this.name = name;
        this.specialty = specialty;
    }

    public String getJudgeID() {
        return judgeID;
    }

    public void setJudgeID(String judgeID) {
        this.judgeID = judgeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
