package UI;

import Controller.Controller;
import Exceptions.BusinessLogicException;
import Repository.FileRepository;
import Model.Client;
import Model.Case;
import Model.Judge;
import Model.Lawyer;
import Model.LawyerAssignment;
import Service.Service;
import Repository.IRepository;
import Exceptions.EntityNotFoundException;
import Exceptions.DatabaseException;
import java.util.Scanner;

/**
 * UI class serves as the user interface for the Courthouse Management System
 * provides a menu for adding, updating, deleting and viewing entities
 */
public class UI {
    public static void main(String[] args) {
        String clientFilePath = "clients.csv";
        String lawyerFilePath = "lawyers.csv";
        String caseFilePath = "cases.csv";
        String assignmentFilePath = "assignments.csv";
        String judgeFilePath = "judges.csv";

        IRepository<Client> clientRepo = new FileRepository<>(
                clientFilePath,
                Client::getClientID,
                line -> {
                    String[] parts = line.split(",");
                    return new Client(parts[0], parts[1], parts[2]);
                },
                client -> client.getClientID() + "," + client.getGivenName() + "," + client.getAddress()
        );

        IRepository<Lawyer> lawyerRepo = new FileRepository<>(
                lawyerFilePath,
                Lawyer::getLawyerID,
                line -> {
                    String[] parts = line.split(",");
                    return new Lawyer(parts[0], parts[1], parts[2]);
                },
                lawyer -> lawyer.getLawyerID() + "," + lawyer.getGivenName() + "," + lawyer.getFirmName()
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

        IRepository<Judge> judgeRepo = new FileRepository<>(
                judgeFilePath,
                Judge::getJudgeID,
                line -> {
                    String[] parts = line.split(",");
                    return new Judge(parts[0], parts[1], parts[2]);
                },
                judge -> judge.getJudgeID() + "," + judge.getGivenName() + "," + judge.getSpecialty()
        );

        // initialize service and controller
        Service Service = new Service(clientRepo, lawyerRepo, caseRepo, assignmentRepo, judgeRepo);
        Controller Controller = new Controller(Service);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourt Management System");
            System.out.println("1. Add Case");
            System.out.println("2. Add Client");
            System.out.println("3. Add Lawyer");
            System.out.println("4. Add Judge");
            System.out.println("5. View All Cases");
            System.out.println("6. View All Clients");
            System.out.println("7. View All Lawyers");
            System.out.println("8. View All Judges");
            System.out.println("9. Update Case");
            System.out.println("10. Update Client");
            System.out.println("11. Update Lawyer");
            System.out.println("12. Update Judge");
            System.out.println("13. Delete Case");
            System.out.println("14. Delete Client");
            System.out.println("15. Delete Lawyer");
            System.out.println("16. Delete Judge");
            System.out.println("17. Assign Lawyer to Case");
            System.out.println("18. Assign Lawyer to Client Case");
            System.out.println("19. Filter Cases by Status");
            System.out.println("20. Sort Lawyers by Name");
            System.out.println("21. Filter Judges by Specialty");
            System.out.println("22. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter case ID: ");
                        String caseId = scanner.nextLine();
                        System.out.print("Enter case status: ");
                        String caseStatus = scanner.nextLine();
                        System.out.print("Enter client ID for the case: ");
                        String caseClientId = scanner.nextLine();
                        Controller.addCase(caseId, caseStatus, caseClientId);
                    } catch (EntityNotFoundException | DatabaseException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Enter client ID: ");
                        String clientId = scanner.nextLine();
                        System.out.print("Enter client name: ");
                        String clientName = scanner.nextLine();
                        System.out.print("Enter client address: ");
                        String clientAddress = scanner.nextLine();
                        Controller.addClient(clientId, clientName, clientAddress);
                    } catch (EntityNotFoundException | DatabaseException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Enter lawyer ID: ");
                        String lawyerId = scanner.nextLine();
                        System.out.print("Enter lawyer name: ");
                        String lawyerName = scanner.nextLine();
                        System.out.print("Enter lawyer firm name: ");
                        String firmName = scanner.nextLine();
                        Controller.addLawyer(lawyerId, lawyerName, firmName);
                    } catch (EntityNotFoundException | DatabaseException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Enter judge ID: ");
                        String judgeId = scanner.nextLine();
                        System.out.print("Enter judge name: ");
                        String judgeName = scanner.nextLine();
                        System.out.print("Enter judge specialty: ");
                        String specialty = scanner.nextLine();
                        Controller.addJudge(judgeId, judgeName, specialty);
                    } catch (EntityNotFoundException | DatabaseException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("All Cases:");
                    Controller.getAllCases().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("All Clients:");
                    Controller.getAllClients().forEach(System.out::println);
                    break;
                case 7:
                    System.out.println("All Lawyers:");
                    Controller.getAllLawyers().forEach(System.out::println);
                    break;
                case 8:
                    System.out.println("All Judges:");
                    Controller.getAllJudges().forEach(System.out::println);
                    break;
                case 9:
                    System.out.println("Enter case ID to update:");
                    String caseToUpdate = scanner.nextLine();
                    System.out.print("Enter new case status:");
                    String newCaseStatus = scanner.nextLine();
                    System.out.print("Enter new client ID for the case:");
                    String newCaseClientId = scanner.nextLine();
                    Controller.updateCase(caseToUpdate, newCaseStatus, newCaseClientId);
                    System.out.println("Case updated successfully.");
                    break;
                case 10:
                    System.out.println("Enter client ID to update:");
                    String clientToUpdate = scanner.nextLine();
                    System.out.print("Enter new client name:");
                    String newClientName = scanner.nextLine();
                    System.out.print("Enter new client address:");
                    String newClientAddress = scanner.nextLine();
                    Controller.updateClient(clientToUpdate, newClientName, newClientAddress);
                    System.out.println("Client updated successfully.");
                    break;
                case 11:
                    System.out.println("Enter lawyer ID to update:");
                    String lawyerToUpdate = scanner.nextLine();
                    System.out.print("Enter new lawyer name:");
                    String newLawyerName = scanner.nextLine();
                    System.out.print("Enter new firm name:");
                    String newFirmName = scanner.nextLine();
                    Controller.updateLawyer(lawyerToUpdate, newLawyerName, newFirmName);
                    System.out.println("Lawyer updated successfully.");
                    break;
                case 12:
                    System.out.println("Enter judge ID to update:");
                    String judgeToUpdate = scanner.nextLine();
                    System.out.print("Enter new judge name:");
                    String newJudgeName = scanner.nextLine();
                    System.out.print("Enter new judge specialty:");
                    String newSpecialty = scanner.nextLine();
                    Controller.updateJudge(judgeToUpdate, newJudgeName, newSpecialty);
                    System.out.println("Judge updated successfully.");
                    break;
                case 13:
                    System.out.println("Enter case ID to delete:");
                    String caseToDelete = scanner.nextLine();
                    Controller.deleteCase(caseToDelete);
                    System.out.println("Case deleted successfully.");
                    break;
                case 14:
                    System.out.println("Enter client ID to delete:");
                    String clientToDelete = scanner.nextLine();
                    Controller.deleteClient(clientToDelete);
                    System.out.println("Client deleted successfully.");
                    break;
                case 15:
                    System.out.println("Enter lawyer ID to delete:");
                    String lawyerToDelete = scanner.nextLine();
                    Controller.deleteLawyer(lawyerToDelete);
                    System.out.println("Lawyer deleted successfully.");
                    break;
                case 16:
                    System.out.println("Enter judge ID to delete:");
                    String judgeToDelete = scanner.nextLine();
                    Controller.deleteJudge(judgeToDelete);
                    System.out.println("Judge deleted successfully.");
                    break;
                case 17:
                    try {
                        System.out.print("Enter lawyer ID: ");
                        String assignLawyerId = scanner.nextLine();
                        System.out.print("Enter case ID: ");
                        String assignCaseId = scanner.nextLine();
                        Controller.assignLawyerToCase(assignLawyerId, assignCaseId);
                    } catch (EntityNotFoundException | BusinessLogicException | DatabaseException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 18:
                    try {
                        System.out.print("Enter client ID: ");
                        String clientToAssign = scanner.nextLine();
                        System.out.print("Enter lawyer ID: ");
                        String lawyerToAssign = scanner.nextLine();
                        Controller.assignLawyerToClientCases(clientToAssign, lawyerToAssign);
                    } catch (EntityNotFoundException | DatabaseException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 19:
                    try {
                        System.out.print("Enter case status to filter by: ");
                        String status = scanner.nextLine();
                        System.out.println("Cases with status '" + status + "':");
                        Controller.filterCasesByStatus(status).forEach(System.out::println);
                    } catch (EntityNotFoundException | DatabaseException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 20:
                    try {
                        System.out.println("All Lawyers (sorted by name):");
                        Controller.sortLawyersByName().forEach(System.out::println);
                    } catch (EntityNotFoundException | DatabaseException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 21:
                    try {
                        System.out.print("Enter judge specialty to filter by: ");
                        String judgeSpecialty = scanner.nextLine();
                        System.out.println("Judges with specialty '" + judgeSpecialty + "':");
                        Controller.filterJudgesBySpecialty(judgeSpecialty).forEach(System.out::println);
                    } catch (EntityNotFoundException | DatabaseException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 22:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
