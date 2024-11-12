package com.courthouse.service;

import com.courthouse.model.*;
import com.courthouse.repository.*;

import java.util.List;

public class CourtService {
    private IRepository<Client> clientRepository;
    private IRepository<Lawyer> lawyerRepository;
    private IRepository<Case> caseRepository;
    private IRepository<LawyerAssignment> assignmentRepository;

    public CourtService(
            IRepository<Client> clientRepository,
            IRepository<Lawyer> lawyerRepository,
            IRepository<Case> caseRepository,
            IRepository<LawyerAssignment> assignmentRepository){
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
        this.caseRepository = caseRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public void addClient(Client client) {
        clientRepository.create(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    public void assignLawyerToCase(String lawyerId, String caseId) {
        LawyerAssignment assignment = new LawyerAssignment(lawyerId, caseId);
        assignmentRepository.create(assignment);
    }
}