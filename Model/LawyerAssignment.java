package Model;

/**
 * Represents an assignment of a lawyer to a case.
 */
public class LawyerAssignment {
    private String caseID;
    private String lawyerID;

    public LawyerAssignment(String caseID, String lawyerID) {
        this.lawyerID = lawyerID;
        this.caseID = caseID;
    }

    public String getLawyerID() {
        return lawyerID;
    }

    public void setLawyerID(String lawyerID) {
        this.lawyerID = lawyerID;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }
}
