package Controller;

import Service.Service;
import java.util.List;

/**
 * Controller class handles user interactions and calls the service layer
 */
public class Controller {
    private final Service Service;

    public Controller(Service mainService) {
        this.Service = mainService;
    }

    /**
     * add, update, delete and getAll case method calls
     *
     * @param clientId the client associated with the case
     */
    public void addCase(String caseId, String caseStatus, String clientId) {
        Service.addCase(caseId, caseStatus, clientId);
    }

    public void updateCase(String caseId, String caseStatus, String clientId) {
        Service.updateCase(caseId, caseStatus, clientId);
    }

    public void deleteCase(String caseId) {
        Service.deleteCase(caseId);
    }

    public List<String> getAllCases() {
        return Service.getAllCases();
    }

    /**
     * add, update, delete and getAll client method calls
     */
    public void addClient(String clientId, String name, String address) {
        Service.addClient(clientId, name, address);
    }

    public void updateClient(String clientId, String name, String address) {
        Service.updateClient(clientId, name, address);
    }

    public void deleteClient(String clientId) {
        Service.deleteClient(clientId);
    }

    public List<String> getAllClients() {
        return Service.getAllClients();
    }

    /**
     * add, update, delete and getAll lawyer method calls
     *
     * @param firmName the firm where the lawyer works
     */
    public void addLawyer(String lawyerId, String name, String firmName) {
        Service.addLawyer(lawyerId, name, firmName);
    }

    public void updateLawyer(String lawyerId, String name, String firmName) {
        Service.updateLawyer(lawyerId, name, firmName);
    }

    public void deleteLawyer(String lawyerId) {
        Service.deleteLawyer(lawyerId);
    }

    public List<String> getAllLawyers() {
        return Service.getAllLawyers();
    }

    /**
     * add, update, delete and getAll judge method calls
     *
     * @param specialty civil/penal/etc
     */
    public void addJudge(String judgeId, String name, String specialty) {
        Service.addJudge(judgeId, name, specialty);
    }

    public void updateJudge(String judgeId, String name, String specialty) {
        Service.updateJudge(judgeId, name, specialty);
    }

    public void deleteJudge(String judgeId) {
        Service.deleteJudge(judgeId);
    }

    public List<String> getAllJudges() {
        return Service.getAllJudges();
    }

    /**
     * assigns a lawyer to a case
     */
    public void assignLawyerToCase(String lawyerId, String caseId) {
        Service.assignLawyerToCase(lawyerId, caseId);
    }

    /**
     * assigns a lawyer to all open cases for a client
     */
    public void assignLawyerToClientCases(String clientId, String lawyerId) {
        Service.assignLawyerToOpenCasesForClient(clientId, lawyerId);
    }

    /**
     * filters cases by status
     *
     * @return a list of filtered cases
     */
    public List<String> filterCasesByStatus(String status) {
        return Service.filterCasesByStatus(status);
    }

    /**
     * sorts lawyers by name
     *
     * @return a list of sorted lawyers
     */
    public List<String> sortLawyersByName() {
        return Service.sortLawyersByName();
    }

    /**
     * filters judges by specialty
     *
     * @return a list of filtered judges
     */
    public List<String> filterJudgesBySpecialty(String specialty) {
        return Service.filterJudgesBySpecialty(specialty);
    }
}
