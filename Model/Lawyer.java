package Model;

/**
 * Represents a lawyer in the Court House Management System.
 */
public class Lawyer extends Person{
    private String LawyerID;
    private String firmName;

    public Lawyer(String lawyerID, String name, String firmName) {
        super(name);
        this.LawyerID = lawyerID;
        this.firmName = firmName;
    }

    public String getLawyerID() {
        return LawyerID;
    }

    public void setLawyerID(String lawyerID) {
        this.LawyerID = lawyerID;
    }


    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    /**
     * Overrides the abstract method to display lawyer details.
     */
    @Override
    public String getDetails() {
        return "LawyerID: " + LawyerID + ", Name: " + getName() + ", Firm: " + firmName;
    }
}


