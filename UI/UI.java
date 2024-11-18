package UI;

import Controller.Controller;
import Model.Client;
import Model.Lawyer;
import Model.Case;
import Model.LawyerAssignment;
import Repository.InMemoryRepository;
import Repository.IRepository;
import Service.Service;

public class UI {
    public static void main(String[] args) {
        // Initialize InMemoryRepository
        IRepository<Client> clientRepo = new InMemoryRepository<>(Client::getClientID);
        IRepository<Lawyer> lawyerRepo = new InMemoryRepository<>(Lawyer::getLawyerID);
        IRepository<Case> caseRepo = new InMemoryRepository<>(Case::getCaseID);
        IRepository<LawyerAssignment> assignmentRepo = new InMemoryRepository<>(
                assignment -> assignment.getLawyerID() + "-" + assignment.getCaseID()
        );

        // Initialize service and controller
        Service service = new Service(clientRepo, lawyerRepo, caseRepo, assignmentRepo);
        Controller controller = new Controller(Service);

        // Start the application
        controller.start();
    }
}
