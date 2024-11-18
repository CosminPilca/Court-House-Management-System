package Service;

import Model.Case;
import Model.Client;
import Model.Lawyer;
import Model.LawyerAssignment;
import Repository.IRepository;

import java.util.List;

/**
 * Service class that handles the business logic for the Court House Management System.
 * Provides methods to manage clients, lawyers, cases, and their relationships.
 */

public class Service {
    private final IRepository<Client> clientRepository;
    private final IRepository<Lawyer> lawyerRepository;
    private final IRepository<Case> caseRepository;
    private final IRepository<LawyerAssignment> assignmentRepository;

    /**
     * Constructor to initialize the service with required repositories.
     *
     * @param clientRepository   Repository to manage Client data.
     * @param lawyerRepository   Repository to manage Lawyer data.
     * @param caseRepository     Repository to manage Case data.
     * @param assignmentRepository Repository to manage LawyerAssignment data.
     */

    public Service(
            IRepository<Client> clientRepository,
            IRepository<Lawyer> lawyerRepository,
            IRepository<Case> caseRepository,
            IRepository<LawyerAssignment> assignmentRepository
    ){
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
        this.caseRepository = caseRepository;
        this.assignmentRepository = assignmentRepository;
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

}