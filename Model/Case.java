package Model;

/**
 * Represents a case in the Court House Management System.
 */
public class Case {
    private String caseID;
    private String caseStatus;
    private String clientId;

    public Case(){}

    public Case(String caseID, String caseStatus, String clientId) {
        this.caseID = caseID;
        this.caseStatus = caseStatus;
        this.clientId = clientId;
    }

    public String getCaseID() {
        return this.caseID;
    }

    public String getClientID() {
        return clientId;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getCaseStatus() {
        return this.caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getClientId() {
        return this.clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}