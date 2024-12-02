package Service;

import Model.Case;
import Model.Client;
import Model.Lawyer;
import Model.Judge;
import Model.LawyerAssignment;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class that handles the business logic for the Court House Management System.
 * Provides methods to manage clients, lawyers, cases, and their relationships.
 */

public class Service {
    private final IRepository<Client> clientRepository;
    private final IRepository<Lawyer> lawyerRepository;
    private final IRepository<Case> caseRepository;
    private final IRepository<LawyerAssignment> assignmentRepository;
    private final IRepository<Judge> judgeRepository;
    /**
     * Constructor to initialize the service with required repositories.
     *
     * @param clientRepository     Repository to manage Client data.
     * @param lawyerRepository     Repository to manage Lawyer data.
     * @param caseRepository       Repository to manage Case data.
     * @param assignmentRepository Repository to manage LawyerAssignment data.
     */

    public Service(
            IRepository<Client> clientRepository,
            IRepository<Lawyer> lawyerRepository,
            IRepository<Case> caseRepository,
            IRepository<LawyerAssignment> assignmentRepository,
            IRepository<Judge> judgeRepository
    ) {
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
        this.caseRepository = caseRepository;
        this.assignmentRepository = assignmentRepository;
        this.judgeRepository = judgeRepository;
    }

    /**
     * Adds a new client to the system.
     *
     * @param client The client object to be added.
     * @throws IllegalArgumentException if a client with the same ID already exists.
     */

    public void addClient(Client client) {
        if (clientRepository.read(client.getClientID()) != null) {
            throw new IllegalArgumentException("Client with ID " + client.getClientID() + " already exists.");
        }

        clientRepository.create(client);
    }


    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    /**
     * Adds a new lawyer to the system.
     *
     * @param lawyer The lawyer object to be added.
     * @throws IllegalArgumentException if a lawyer with the same ID already exists.
     */

    public void addLawyer(Lawyer lawyer) {
        if (lawyerRepository.read(lawyer.getLawyerID()) != null) {
            throw new IllegalArgumentException("Lawyer with ID " + lawyer.getLawyerID() + " already exists.");
        }
        lawyerRepository.create(lawyer);
    }

    /**
     * Retrieves all lawyers in the system.
     *
     * @return A list of all lawyers.
     */


    public List<Lawyer> getAllLawyers() {
        return lawyerRepository.getAll();
    }

    /**
     * Adds a new case to the system.
     *
     * @param caseObj The case object to be added.
     * @throws IllegalArgumentException if a case with the same ID already exists.
     */

    public void addCase(Case caseObj) {
        if (caseRepository.read(caseObj.getCaseID()) != null) {
            throw new IllegalArgumentException("Case with ID " + caseObj.getCaseID() + " already exists.");
        }
        caseRepository.create(caseObj);
    }

    /**
     * Retrieves all cases in the system.
     *
     * @return A list of all cases.
     */

    public List<Case> getAllCases() {
        return caseRepository.getAll();
    }

    /**
     * Assigns a lawyer to a case by creating a relationship in the system.
     *
     * @param lawyerId The ID of the lawyer to be assigned.
     * @param caseId   The ID of the case to which the lawyer is assigned.
     * @throws IllegalArgumentException if the lawyer or case does not exist.
     */

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

    /**
     * Retrieves all lawyer-case assignments in the system.
     *
     * @return A list of all lawyer-case assignments.
     */

    public List<LawyerAssignment> getAllAssignments() {
        return assignmentRepository.getAll();
    }


    /**
     * Assigns a lawyer to all open cases for a specific client.
     *
     * @param clientId The ID of the client whose cases need lawyer assignment.
     * @param lawyerId The ID of the lawyer to be assigned.
     * @throws IllegalArgumentException if the client or lawyer does not exist.
     */
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

        System.out.println("Lawyer " + lawyerId + " has been assigned to all open cases for client " + clientId);
    }
    public void addJudge(Judge judge) {
        judgeRepository.create(judge);
    }

    public List<Judge> getAllJudges() {
        return judgeRepository.getAll();
    }

    public Judge getJudgeByID(String judgeID) {
        return judgeRepository.read(judgeID);
    }

    public List<Judge> getJudgesBySpecialty(String specialty) {
        // Assuming judgeRepository has a method to get judges by specialty (like in your initial repository code)
        return judgeRepository.getAll().stream()
                .filter(judge -> judge.getSpecialty().equalsIgnoreCase(specialty))
                .collect(Collectors.toList());
    }
}