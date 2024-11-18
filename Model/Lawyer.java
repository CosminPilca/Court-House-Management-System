package Model;

/**
 * Represents a lawyer in the Court House Management System.
 */
public class Lawyer {
    private String LawyerID;
    private String name;
    private String firmName;

    public Lawyer(String lawyerID, String name, String firmName) {
        this.LawyerID = lawyerID;
        this.name = name;
        this.firmName = firmName;
    }

    public String getLawyerID() {
        return LawyerID;
    }

    public void setLawyerID(String lawyerID) {
        this.LawyerID = lawyerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }
}