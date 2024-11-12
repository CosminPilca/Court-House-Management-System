package com.courthouse.ui;

import com.courthouse.controller.CourtController;
import com.courthouse.model.Client;
import com.courthouse.model.Lawyer;
import com.courthouse.model.Case;
import com.courthouse.model.LawyerAssignment;
import com.courthouse.repository.InMemoryRepository;
import com.courthouse.repository.IRepository;
import com.courthouse.service.CourtService;

public class ConsoleApp {
    public static void main(String[] args) {
        IRepository<Client> clientRepo = new InMemoryRepository<>(Client::getClientID);
        IRepository<Lawyer> lawyerRepo = new InMemoryRepository<>(Lawyer::getLawyerID);
        IRepository<Case> caseRepo = new InMemoryRepository<>(Case::getCaseID);
        IRepository<LawyerAssignment> assignmentRepo = new InMemoryRepository<>(
                assignment -> assignment.getLawyerID() + "-" + assignment.getCaseID()

                CourtService service = new CourtService(clientRepo, lawyerRepo, caseRepo, assignmentRepo);

        CourtController controller = new CourtController(service);

        controller.start();
    }
}