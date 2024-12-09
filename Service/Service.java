package Service;

import Model.Case;
import Model.Client;
import Model.Judge;
import Model.Lawyer;
import Model.LawyerAssignment;
import Repository.IRepository;
import Exceptions.EntityNotFoundException;
import Exceptions.DatabaseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class manages operations and calls repository functions
 */
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

    /**
     * add, update, delete and getAll case method implementations
     */
    public void addCase(String caseId, String caseStatus, String clientId) {
        Case caseObj = new Case(caseId, caseStatus, clientId);
        if (caseRepository.read(caseObj.getCaseID()) != null) {
            throw new IllegalArgumentException("Case with ID " + caseObj.getCaseID() + " already exists.");
        }
        caseRepository.create(caseObj);
    }

    public void updateCase(String caseId, String caseStatus, String clientId) {
        Case caseObj = caseRepository.read(caseId);
        if (caseObj != null) {
            caseObj.setCaseStatus(caseStatus);
            caseObj.setClientId(clientId);
            caseRepository.update(caseObj);
        } else {
            throw new IllegalArgumentException("Case with ID " + caseId + " does not exist.");
        }
    }

    public void deleteCase(String caseId) {
        caseRepository.delete(caseId);
    }

    public List<String> getAllCases() {
        return caseRepository.getAll().stream()
                .map(caseObj -> caseObj.getCaseID() + " - " + caseObj.getCaseStatus())
                .collect(Collectors.toList());
    }

    /**
     * add, update, delete and getAll client method implementations
     */
    public void addClient(String clientId, String name, String address) {
        Client client = new Client(clientId, name, address);
        if (clientRepository.read(client.getClientID()) != null) {
            throw new IllegalArgumentException("Client with ID " + client.getClientID() + " already exists.");
        }
        clientRepository.create(client);
    }

    public void updateClient(String clientId, String name, String address) {
        Client client = clientRepository.read(clientId);
        if (client != null) {
            client.setGivenName(name);
            client.setAddress(address);
            clientRepository.update(client);
        } else {
            throw new IllegalArgumentException("Client with ID " + clientId + " does not exist.");
        }
    }

    public void deleteClient(String clientId) {
        clientRepository.delete(clientId);
    }

    public List<String> getAllClients() {
        return clientRepository.getAll().stream()
                .map(client -> client.getClientID() + " - " + client.getGivenName() + " - " + client.getAddress())
                .collect(Collectors.toList());
    }

    /**
     * add, update, delete and getAll lawyer method implementations
     */
    public void addLawyer(String lawyerId, String name, String firmName) {
        Lawyer lawyer = new Lawyer(lawyerId, name, firmName);
        if (lawyerRepository.read(lawyer.getLawyerID()) != null) {
            throw new IllegalArgumentException("Lawyer with ID " + lawyer.getLawyerID() + " already exists.");
        }
        lawyerRepository.create(lawyer);

    }

    public void updateLawyer(String lawyerId, String name, String firmName) {
        Lawyer lawyer = lawyerRepository.read(lawyerId);
        if (lawyer != null) {
            lawyer.setGivenName(name);
            lawyer.setFirmName(firmName);
            lawyerRepository.update(lawyer);
        } else {
            throw new IllegalArgumentException("Lawyer with ID " + lawyerId + " does not exist.");
        }
    }

    public void deleteLawyer(String lawyerId) {
        lawyerRepository.delete(lawyerId);
    }

    public List<String> getAllLawyers() {
        return lawyerRepository.getAll().stream()
                .map(lawyer -> lawyer.getLawyerID() + " - " + lawyer.getGivenName() + " - " + lawyer.getFirmName())
                .collect(Collectors.toList());
    }

    /**
     * add, delete, update and getAll judge method implementations
     */
    public void addJudge(String judgeId, String name, String specialty) {
        Judge judge = new Judge(judgeId, name, specialty);
        if (judgeRepository.read(judge.getJudgeID()) != null) {
            throw new IllegalArgumentException("Judge with ID " + judge.getJudgeID() + " already exists.");
        }
        judgeRepository.create(judge);
    }

    public void updateJudge(String judgeId, String name, String specialty) {
        Judge judge = judgeRepository.read(judgeId);
        if (judge != null) {
            judge.setGivenName(name);
            judge.setSpecialty(specialty);
            judgeRepository.update(judge);
        } else {
            throw new IllegalArgumentException("Judge with ID " + judgeId + " does not exist.");
        }
    }

    public void deleteJudge(String judgeId) {
        judgeRepository.delete(judgeId);
    }

    public List<String> getAllJudges() {
        return judgeRepository.getAll().stream()
                .map(judge -> judge.getJudgeID() + " - " + judge.getGivenName() + " - Specialty: " + judge.getSpecialty())
                .collect(Collectors.toList());
    }

    /**
     * assigns a lawyer to a case
     */
    public void assignLawyerToCase(String lawyerId, String caseId) {
        try {
            Lawyer lawyer = lawyerRepository.read(lawyerId);
            if (lawyer == null) {
                throw new EntityNotFoundException("Lawyer with ID " + lawyerId + " does not exist.");
            }

            Case caseObj = caseRepository.read(caseId);
            if (caseObj == null) {
                throw new EntityNotFoundException("Case with ID " + caseId + " does not exist.");
            }

            LawyerAssignment assignment = new LawyerAssignment(lawyerId, caseId);
            assignmentRepository.create(assignment);
        } catch (Exception e) {
            throw new DatabaseException("Failed to assign lawyer to case. Lawyer ID: " + lawyerId + ", Case ID: " + caseId, e);
        }
    }

    /**
     * assigns a lawyer to all open cases for a client
     */
    public void assignLawyerToOpenCasesForClient(String clientId, String lawyerId) {
        try {
            Client client = clientRepository.read(clientId);
            if (client == null) {
                throw new EntityNotFoundException("Client with ID " + clientId + " does not exist.");
            }

            Lawyer lawyer = lawyerRepository.read(lawyerId);
            if (lawyer == null) {
                throw new EntityNotFoundException("Lawyer with ID " + lawyerId + " does not exist.");
            }

            List<Case> openCasesForClient = caseRepository.getAll().stream()
                    .filter(caseObj -> "Open".equalsIgnoreCase(caseObj.getCaseStatus())
                            && caseObj.getClientId().equals(clientId))
                    .collect(Collectors.toList());

            for (Case caseObj : openCasesForClient) {
                LawyerAssignment assignment = new LawyerAssignment(lawyerId, caseObj.getCaseID());
                assignmentRepository.create(assignment);
            }
        } catch (Exception e) {
            throw new DatabaseException("Failed to assign lawyer to open cases for client ID: " + clientId, e);
        }
    }

    /**
     * filters cases by status
     *
     * @return a list of filtered cases by status
     */
    public List<String> filterCasesByStatus(String status) {
        try {
            return caseRepository.getAll().stream()
                    .filter(caseObj -> caseObj.getCaseStatus().equalsIgnoreCase(status))
                    .map(caseObj -> caseObj.getCaseID() + " - " + caseObj.getCaseStatus() + " - Client ID: " + caseObj.getClientId())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Failed to filter cases by status: " + status, e);
        }
    }

    /**
     * sorts lawyers by name
     *
     * @return a list of sorted lawyers
     */
    public List<String> sortLawyersByName() {
        try {
            return lawyerRepository.getAll().stream()
                    .sorted((l1, l2) -> l1.getGivenName().compareToIgnoreCase(l2.getGivenName()))
                    .map(lawyer -> lawyer.getLawyerID() + " - " + lawyer.getGivenName() + " - Firm: " + lawyer.getFirmName())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Failed to sort lawyers by name.", e);
        }
    }

    /**
     * filters judges by specialty
     *
     * @return a list of filtered judges by specialty
     */
    public List<String> filterJudgesBySpecialty(String specialty) {
        try {
            return judgeRepository.getAll().stream()
                    .filter(judge -> judge.getSpecialty().equalsIgnoreCase(specialty))
                    .map(judge -> judge.getJudgeID() + " - " + judge.getGivenName() + " - Specialty: " + judge.getSpecialty())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Failed to filter judges by specialty: " + specialty, e);
        }
    }

}
