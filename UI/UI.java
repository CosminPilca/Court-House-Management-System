package UI;

import Controller.Controller;
import Repository.FileRepository;
import Model.Client;
import Model.Case;
import Model.Judge;
import Model.Lawyer;
import Model.LawyerAssignment;
import Service.Service;
import Repository.IRepository;
import java.util.Scanner;

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

        IRepository<Judge> judgeRepo = new FileRepository<>(
                judgeFilePath,
                Judge::getJudgeID,
                line -> {
                    String[] parts = line.split(",");
                    return new Judge(parts[0], parts[1], parts[2]);
                },
                judge -> judge.getJudgeID() + "," + judge.getName() + "," + judge.getSpecialty()
        );
        Service Service = new Service(clientRepo, lawyerRepo, caseRepo, assignmentRepo, judgeRepo);
        Controller Controller = new Controller(Service);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourt Management System");
            System.out.println("1. Add Client");
            System.out.println("2. Add Lawyer");
            System.out.println("3. Add Case");
            System.out.println("4. Add Judge");
            System.out.println("5. Assign Lawyer to Case");
            System.out.println("6. Assign Lawyer to Client Case");
            System.out.println("7. View All Clients");
            System.out.println("8. View All Cases");
            System.out.println("9. View All Judges");
            System.out.println("10. Filter Cases by Status");
            System.out.println("11. Sort Lawyers by Name");
            System.out.println("12. Filter Judges by Specialty");
            System.out.println("13. Exit");
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
                    System.out.print("Enter client ID: ");
                    String clientId = scanner.nextLine();
                    System.out.print("Enter client name: ");
                    String clientName = scanner.nextLine();
                    System.out.print("Enter client address: ");
                    String clientAddress = scanner.nextLine();
                    Controller.addClient(clientId, clientName, clientAddress);
                    break;
                case 2:
                    System.out.print("Enter lawyer ID: ");
                    String lawyerId = scanner.nextLine();
                    System.out.print("Enter lawyer name: ");
                    String lawyerName = scanner.nextLine();
                    System.out.print("Enter lawyer firm name: ");
                    String firmName = scanner.nextLine();
                    Controller.addLawyer(lawyerId, lawyerName, firmName);
                    break;
                case 3:
                    System.out.print("Enter case ID: ");
                    String caseId = scanner.nextLine();
                    System.out.print("Enter case status: ");
                    String caseStatus = scanner.nextLine();
                    System.out.print("Enter client ID for the case: ");
                    String caseClientId = scanner.nextLine();
                    Controller.addCase(caseId, caseStatus, caseClientId);
                    break;
                case 4:
                    System.out.print("Enter judge ID: ");
                    String judgeId = scanner.nextLine();
                    System.out.print("Enter judge name: ");
                    String judgeName = scanner.nextLine();
                    System.out.print("Enter judge specialty: ");
                    String specialty = scanner.nextLine();
                    Controller.addJudge(judgeId, judgeName, specialty);
                    break;
                case 5:
                    System.out.print("Enter lawyer ID: ");
                    String assignLawyerId = scanner.nextLine();
                    System.out.print("Enter case ID: ");
                    String assignCaseId = scanner.nextLine();
                    Controller.assignLawyerToCase(assignLawyerId, assignCaseId);
                    break;
                case 6:
                    System.out.print("Enter client ID: ");
                    String clientToAssign = scanner.nextLine();
                    System.out.print("Enter lawyer ID: ");
                    String lawyerToAssign = scanner.nextLine();
                    Controller.assignLawyerToClientCases(clientToAssign, lawyerToAssign);
                    break;
                case 7:
                    System.out.println("All Clients:");
                    Controller.getAllClients().forEach(System.out::println);
                    break;
                case 8:
                    System.out.println("All Cases:");
                    Controller.getAllCases().forEach(System.out::println);
                    break;
                case 9:
                    System.out.println("All Judges:");
                    Controller.getAllJudges().forEach(System.out::println);
                    break;
                case 10:
                    System.out.print("Enter case status to filter by: ");
                    String status = scanner.nextLine();
                    System.out.println("Cases with status '" + status + "':");
                    Controller.filterCasesByStatus(status).forEach(System.out::println);
                    break;
                case 11:
                    System.out.println("All Lawyers (sorted by name):");
                    Controller.sortLawyersByName().forEach(System.out::println);
                    break;
                case 12:
                    System.out.print("Enter judge specialty to filter by: ");
                    String judgeSpecialty = scanner.nextLine();
                    System.out.println("Judges with specialty '" + judgeSpecialty + "':");
                    Controller.filterJudgesBySpecialty(judgeSpecialty).forEach(System.out::println);
                    break;
                case 13:
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
