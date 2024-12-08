package Controller;

import Service.Service;
import java.util.List;

public class Controller {
    private final Service Service;

    public Controller(Service mainService) {
        this.Service = mainService;
    }

    //call Case methods
    public void addCase(String caseId, String caseStatus, String clientId) {
        Service.addCase(caseId, caseStatus, clientId);
    }

    public void updateCase(String caseId, String caseStatus, String clientId) {
        Service.updateCase(caseId, caseStatus, clientId);
    }

    public void deleteCase(String caseId, String clientId) {
        Service.deleteCase(caseId);
    }

    public List<String> getAllCases() {
        return Service.getAllCases();
    }

    //call Client methods
    public void addClient(String clientId, String name, String address) {
        Service.addClient(clientId, name, address);
    }

    public void updateClient(String clientId, String name, String address) {
        Service.updateClient(clientId, name, address);
    }

    public void deleteClient(String clientId, String name) {
        Service.deleteClient(clientId);
    }

    public List<String> getAllClients() {
        return Service.getAllClients();
    }

    //call Lawyer methods
    public void addLawyer(String lawyerId, String name, String firmName) {
        Service.addLawyer(lawyerId, name, firmName);
    }

    public void updateLawyer(String lawyerId, String name, String firmName) {
        Service.updateLawyer(lawyerId, name, firmName);
    }

    public void deleteLawyer(String lawyerId, String name) {
        Service.deleteLawyer(lawyerId);
    }

    public List<String> getAllLawyers() {
        return Service.getAllLawyers();
    }

    //call Judge methods
    public void addJudge(String judgeId, String name, String specialty) {
        Service.addJudge(judgeId, name, specialty);
    }

    public void updateJudge(String judgeId, String name, String specialty) {
        Service.updateJudge(judgeId, name, specialty);
    }

    public void deleteJudge(String judgeId, String name) {
        Service.deleteJudge(judgeId);
    }

    public List<String> getAllJudges() {
        return Service.getAllJudges();
    }

    //call other methods
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
