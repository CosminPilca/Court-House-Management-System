package Model;

/**
 * Represents an assignment of a lawyer to a case.
 */
public class LawyerAssignment {
    private String lawyerID;
    private String caseID;

    public LawyerAssignment(String lawyerID, String caseID) {
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
