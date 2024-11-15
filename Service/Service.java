package com.courthouse.service;

import com.courthouse.model.Case;
import com.courthouse.model.Client;
import com.courthouse.model.Lawyer;
import com.courthouse.model.LawyerAssignment;
import com.courthouse.repository.IRepository;

import java.util.List;

public class CourtService {
    private final IRepository<Client> clientRepository;
    private final IRepository<Lawyer> lawyerRepository;
    private final IRepository<Case> caseRepository;
    private final IRepository<LawyerAssignment> assignmentRepository;

    public CourtService(
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

    public void addClient(Client client) {
        if (clientRepository.read(client.getClientID()) != null) {
            throw new IllegalArgumentException("Client with ID " + client.getClientID() + " already exists.");
        }

        clientRepository.create(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    public void addLawyer(Lawyer lawyer) {
        if (lawyerRepository.read(lawyer.getLawyerID()) != null) {
            throw new IllegalArgumentException("Lawyer with ID " + lawyer.getLawyerID() + " already exists.");
        }
        lawyerRepository.create(lawyer);
    }

    public List<Lawyer> getAllLawyers() {
        return lawyerRepository.getAll();
    }

    public void addCase(Case caseObj) {
        if (caseRepository.read(caseObj.getCaseID()) != null) {
            throw new IllegalArgumentException("Case with ID " + caseObj.getCaseID() + " already exists.");
        }
        caseRepository.create(caseObj);
    }

    public List<Case> getAllCases() {
        return caseRepository.getAll();
    }

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

    public List<LawyerAssignment> getAllAssignments() {
        return assignmentRepository.getAll();
    }

}