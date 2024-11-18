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
        IRepository<Client> clientRepo = new InMemoryRepository<>(Client::getClientID);
        IRepository<Lawyer> lawyerRepo = new InMemoryRepository<>(Lawyer::getLawyerID);
        IRepository<Case> caseRepo = new InMemoryRepository<>(Case::getCaseID);
        IRepository<LawyerAssignment> assignmentRepo = new InMemoryRepository<>(
                assignment -> assignment.getLawyerID() + "-" + assignment.getCaseID()
        );

        Service service = new Service(clientRepo, lawyerRepo, caseRepo, assignmentRepo);
        Controller controller = new Controller(service);

        controller.start();
    }
}
