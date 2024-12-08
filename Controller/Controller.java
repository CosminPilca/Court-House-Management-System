package Controller;

import Service.Service;
import java.util.List;

public class Controller {
    private final Service Service;

    public Controller(Service mainService) {
        this.Service = mainService;
    }

    public void addCase(String caseId, String caseStatus, String clientId) {
        Service.addCase(caseId, caseStatus, clientId);
    }

    public void addClient(String clientId, String name, String address) {
        Service.addClient(clientId, name, address);
    }

    public void addLawyer(String lawyerId, String name, String firmName) {
        Service.addLawyer(lawyerId, name, firmName);
    }

    public void addJudge(String judgeId, String name, String specialty) {
        Service.addJudge(judgeId, name, specialty);
    }

    public List<String> getAllCases() {
        return Service.getAllCases();
    }

    public List<String> getAllClients() {
        return Service.getAllClients();
    }

    public List<String> getAllLawyers() {
        return Service.getAllLawyers();
    }

    public List<String> getAllJudges() {
        return Service.getAllJudges();
    }

    public void assignLawyerToCase(String lawyerId, String caseId) {
        Service.assignLawyerToCase(lawyerId, caseId);
    }

    public void assignLawyerToClientCases(String clientId, String lawyerId) {
        Service.assignLawyerToOpenCasesForClient(clientId, lawyerId);
    }

    public List<String> filterCasesByStatus(String status) {
        return Service.filterCasesByStatus(status);
    }

    public List<String> sortLawyersByName() {
        return Service.sortLawyersByName();
    }

    public List<String> filterJudgesBySpecialty(String specialty) {
        return Service.filterJudgesBySpecialty(specialty);
    }
}
