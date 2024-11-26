package UI;

import Controller.Controller;
import Model.Client;
import Model.Lawyer;
import Model.Case;
import Model.LawyerAssignment;
import Repository.InMemoryRepository;
import Repository.IRepository;
import Service.Service;
import Repository.FileRepository;


public class UI {
    public static void main(String[] args) {
       //IRepository<Client> clientRepo = new InMemoryRepository<>(Client::getClientID);
        //IRepository<Lawyer> lawyerRepo = new InMemoryRepository<>(Lawyer::getLawyerID);
        //IRepository<Case> caseRepo = new InMemoryRepository<>(Case::getCaseID);
        //IRepository<LawyerAssignment> assignmentRepo = new InMemoryRepository<>(
//                assignment -> assignment.getLawyerID() + "-" + assignment.getCaseID()
  //      );

        String clientFilePath = "clients.csv";
        String lawyerFilePath = "lawyers.csv";
        String caseFilePath = "cases.csv";
        String assignmentFilePath = "assignments.csv";

        IRepository<Client> clientRepo = new FileRepository<>(
                clientFilePath,
                Client::getClientID,
                line -> {
                    String[] parts = line.split(",");
                    return new Client(parts[0], parts[1], parts[2]);
                },
                client -> client.getClientID() + "," + client.getName() + "," + client.getAddress()
        );

        IRepository<Lawyer> lawyerRepo = new FileRepository<>(
                lawyerFilePath,
                Lawyer::getLawyerID,
                line -> {
                    String[] parts = line.split(",");
                    return new Lawyer(parts[0], parts[1], parts[2]);
                },
                lawyer -> lawyer.getLawyerID() + "," + lawyer.getName() + "," + lawyer.getFirmName()
        );

        IRepository<Case> caseRepo = new FileRepository<>(
                caseFilePath,
                Case::getCaseID,
                line -> {
                    String[] parts = line.split(",");
                    return new Case(parts[0], parts[1], parts[2]); // Assuming Case constructor: Case(id, status, clientId)
                },
                caseObj -> caseObj.getCaseID() + "," + caseObj.getCaseStatus() + "," + caseObj.getClientId()
        );

        IRepository<LawyerAssignment> assignmentRepo = new FileRepository<>(
                assignmentFilePath,
                assignment -> assignment.getLawyerID() + "-" + assignment.getCaseID(),
                line -> {
                    String[] parts = line.split(",");
                    return new LawyerAssignment(parts[0], parts[1]);
                },
                assignment -> assignment.getLawyerID() + "," + assignment.getCaseID()
        );

        Service service = new Service(clientRepo, lawyerRepo, caseRepo, assignmentRepo);
        Controller controller = new Controller(service);

        controller.start();
    }
}
