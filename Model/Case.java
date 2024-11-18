package Model;

/**
 * Represents a case in the Court House Management System.
 */
public class Case {
    private String caseID;
    private String caseStatus;

    public Case(String caseID, String caseStatus) {
        this.caseID = caseID;
        this.caseStatus = caseStatus;
    }

    public String getCaseID() {
        return caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }
}
