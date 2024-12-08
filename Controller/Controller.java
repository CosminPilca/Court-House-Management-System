package Controller;

import Exceptions.ValidationException;
import Service.Service;
import java.util.List;

/**
 * Controller class handles user interactions and calls the service layer
 */
public class Controller {
    private final Service Service;

    public Controller(Service Service) {
        this.Service = Service;
    }

    /**
     * add, update, delete and getAll case method calls
     *
     * @param clientId the client associated with the case
     */
    public void addCase(String caseId, String caseStatus, String clientId) {
        if (caseId == null || caseId.isEmpty()) {
            throw new ValidationException("Case ID cannot be null or empty.");
        }
        Service.addCase(caseId, caseStatus, clientId);
    }

    public void updateCase(String caseId, String caseStatus, String clientId) {
        if (caseId == null || caseId.isEmpty()) {
            throw new ValidationException("Case ID cannot be null or empty.");
        }
        Service.updateCase(caseId, caseStatus, clientId);
    }

    public void deleteCase(String caseId) {
        if (caseId == null || caseId.isEmpty()) {
            throw new ValidationException("Case ID cannot be null or empty.");
        }
        Service.deleteCase(caseId);
    }

    public List<String> getAllCases() {
        return Service.getAllCases();
    }

    /**
     * add, update, delete and getAll client method calls
     */
    public void addClient(String clientId, String name, String address) {
        if (clientId == null || clientId.isEmpty()) {
            throw new ValidationException("Client ID cannot be null or empty.");
        }
        Service.addClient(clientId, name, address);
    }

    public void updateClient(String clientId, String name, String address) {
        if (clientId == null || clientId.isEmpty()) {
            throw new ValidationException("Client ID cannot be null or empty.");
        }
        Service.updateClient(clientId, name, address);
    }

    public void deleteClient(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            throw new ValidationException("Client ID cannot be null or empty.");
        }
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
        if (lawyerId == null || lawyerId.isEmpty()) {
            throw new ValidationException("Lawyer ID cannot be null or empty.");
        }
        Service.addLawyer(lawyerId, name, firmName);
    }

    public void updateLawyer(String lawyerId, String name, String firmName) {
        if (lawyerId == null || lawyerId.isEmpty()) {
            throw new ValidationException("Lawyer ID cannot be null or empty.");
        }
        Service.updateLawyer(lawyerId, name, firmName);
    }

    public void deleteLawyer(String lawyerId) {
        if (lawyerId == null || lawyerId.isEmpty()) {
            throw new ValidationException("Lawyer ID cannot be null or empty.");
        }
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
        if (judgeId == null || judgeId.isEmpty()) {
            throw new ValidationException("Judge ID cannot be null or empty.");
        }
        Service.addJudge(judgeId, name, specialty);
    }

    public void updateJudge(String judgeId, String name, String specialty) {
        if (judgeId == null || judgeId.isEmpty()) {
            throw new ValidationException("Judge ID cannot be null or empty.");
        }
        Service.updateJudge(judgeId, name, specialty);
    }

    public void deleteJudge(String judgeId) {
        if (judgeId == null || judgeId.isEmpty()) {
            throw new ValidationException("Judge ID cannot be null or empty.");
        }
        Service.deleteJudge(judgeId);
    }

    public List<String> getAllJudges() {
        return Service.getAllJudges();
    }

    /**
     * assigns a lawyer to a case
     */
    public void assignLawyerToCase(String lawyerId, String caseId) {
        if (lawyerId == null || lawyerId.isEmpty() || caseId == null || caseId.isEmpty()) {
            throw new ValidationException("Lawyer ID and Case ID cannot be null or empty.");
        }
        Service.assignLawyerToCase(lawyerId, caseId);
    }

    /**
     * assigns a lawyer to all open cases for a client
     */
    public void assignLawyerToClientCases(String clientId, String lawyerId) {
        if (clientId == null || clientId.isEmpty() || lawyerId == null || lawyerId.isEmpty()) {
            throw new ValidationException("Client ID and Lawyer ID cannot be null or empty.");
        }
        Service.assignLawyerToOpenCasesForClient(clientId, lawyerId);
    }

    /**
     * filters cases by status
     *
     * @return a list of filtered cases
     */
    public List<String> filterCasesByStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new ValidationException("Case status cannot be null or empty.");
        }
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
        if (specialty == null || specialty.isEmpty()) {
            throw new ValidationException("Specialty cannot be null or empty.");
        }
        return Service.filterJudgesBySpecialty(specialty);
    }
}
