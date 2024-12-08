package Service;

import Controller.Controller;
import Model.Case;
import Model.Client;
import Model.Judge;
import Model.Lawyer;
import Model.LawyerAssignment;
import Repository.IRepository;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private final IRepository<Client> clientRepository;
    private final IRepository<Lawyer> lawyerRepository;
    private final IRepository<Case> caseRepository;
    private final IRepository<LawyerAssignment> assignmentRepository;
    private final IRepository<Judge> judgeRepository;

    public Service(
            IRepository<Client> clientRepository,
            IRepository<Lawyer> lawyerRepository,
            IRepository<Case> caseRepository,
            IRepository<LawyerAssignment> assignmentRepository,
            IRepository<Judge> judgeRepository) {
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
        this.caseRepository = caseRepository;
        this.assignmentRepository = assignmentRepository;
        this.judgeRepository = judgeRepository;
    }

    public void addClient(String clientId, String name, String address) {
        Client client = new Client(clientId, name, address);
        if (clientRepository.read(client.getClientID()) != null) {
            throw new IllegalArgumentException("Client with ID " + client.getClientID() + " already exists.");
        }
        clientRepository.create(client);
    }

    public List<String> getAllClients() {
        return clientRepository.getAll().stream()
            .map(client -> client.getClientID() + " - " + client.getName() + " - " + client.getAddress())
            .collect(Collectors.toList());
    }

    public void addLawyer(String lawyerId, String name, String firmName) {
        Lawyer lawyer = new Lawyer(lawyerId, name, firmName);
        if (lawyerRepository.read(lawyer.getLawyerID()) != null) {
            throw new IllegalArgumentException("Lawyer with ID " + lawyer.getLawyerID() + " already exists.");
        }
        lawyerRepository.create(lawyer);
    }
    public List<String> getAllLawyers() {
        return lawyerRepository.getAll().stream()
            .map(lawyer -> lawyer.getLawyerID() + " - " + lawyer.getName() + " - " + lawyer.getFirmName())
            .collect(Collectors.toList());
    }

    public void addCase(String caseId, String caseStatus, String clientId) {
        Case caseObj = new Case(caseId, caseStatus, clientId);
        if (caseRepository.read(caseObj.getCaseID()) != null) {
            throw new IllegalArgumentException("Case with ID " + caseObj.getCaseID() + " already exists.");
    }
        caseRepository.create(caseObj);
    }
    public List<String> getAllCases() {
        return caseRepository.getAll().stream()
            .map(caseObj -> caseObj.getCaseID() + " - " + caseObj.getCaseStatus())
            .collect(Collectors.toList()); }
    public void assignLawyerToCase(String lawyerId, String caseId) {
        Lawyer lawyer = lawyerRepository.read(lawyerId);
        Case caseObj = caseRepository.read(caseId);

        if (lawyer == null) {
        throw new IllegalArgumentException("Lawyer with ID " + lawyerId + " does not exist.");
        }
        if (caseObj == null) {
        throw new IllegalArgumentException("Case with ID " + caseId + " does not exist.");
        }
        LawyerAssignment assignment = new LawyerAssignment(lawyerId, caseId);
        assignmentRepository.create(assignment);
    }

    public void assignLawyerToOpenCasesForClient(String clientId, String lawyerId) {
        Client client = clientRepository.read(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Client with ID " + clientId + " does not exist.");
        }

        Lawyer lawyer = lawyerRepository.read(lawyerId);
        if (lawyer == null) {
            throw new IllegalArgumentException("Lawyer with ID " + lawyerId + " does not exist.");
        }

        List<Case> openCasesForClient = caseRepository.getAll().stream()
            .filter(caseObj -> "Open".equalsIgnoreCase(caseObj.getCaseStatus())
                    && caseObj.getClientId().equals(clientId))
            .collect(Collectors.toList());

        for (Case caseObj : openCasesForClient) {
            LawyerAssignment assignment = new LawyerAssignment(lawyerId, caseObj.getCaseID());
            assignmentRepository.create(assignment);
        }
    }

    public List<String> filterCasesByStatus(String status) {
        return caseRepository.getAll().stream()
            .filter(caseObj -> caseObj.getCaseStatus().equalsIgnoreCase(status))
            .map(caseObj -> caseObj.getCaseID() + " - " + caseObj.getCaseStatus() + " - Client ID: " + caseObj.getClientId())
            .collect(Collectors.toList());
    }

    public List<String> sortLawyersByName() {
        return lawyerRepository.getAll().stream()
            .sorted((l1, l2) -> l1.getName().compareToIgnoreCase(l2.getName()))
            .map(lawyer -> lawyer.getLawyerID() + " - " + lawyer.getName() + " - Firm: " + lawyer.getFirmName())
            .collect(Collectors.toList());
    }

    public void addJudge(String judgeId, String name, String specialty) {
        Judge judge = new Judge(judgeId, name, specialty);
        judgeRepository.create(judge);
    }

    public List<String> getAllJudges() {
        return judgeRepository.getAll().stream()
            .map(judge -> judge.getJudgeID() + " - " + judge.getName() + " - Specialty: " + judge.getSpecialty())
            .collect(Collectors.toList());
    }

    public List<String> filterJudgesBySpecialty(String specialty) {
        return judgeRepository.getAll().stream()
            .filter(judge -> judge.getSpecialty().equalsIgnoreCase(specialty))
            .map(judge -> judge.getJudgeID() + " - " + judge.getName() + " - Specialty: " + judge.getSpecialty())
            .collect(Collectors.toList());
    }

}
