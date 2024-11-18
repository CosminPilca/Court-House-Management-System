package Controller;

import Model.Case;
import Model.Lawyer;
import Service.Service;
import Model.Client;

import java.util.Scanner;

public class Controller {
    private Service Service;

    public Controller(Service service) {
        this.Service = service;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourt Management System");
            System.out.println("1. Add Client");
            System.out.println("2. Add Lawyer");
            System.out.println("3. Add Case");
            System.out.println("4. Assign Lawyer to Case");
            System.out.println("5. Assign Lawyer to Client Case");
            System.out.println("6. View All Clients");
            System.out.println("7. View All Cases");
            System.out.println("8. Exit");
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
                    addClient(scanner);
                    break;

                case 2:
                    addLawyer(scanner);
                    break;

                case 3:
                    addCase(scanner);
                    break;

                case 4:
                    assignLawyerToCase(scanner);
                    break;

                case 5:
                    assignLawyerToClientCases();
                    break;

                case 6:
                    viewAllClients();
                    break;

                case 7:
                    viewAllCases();
                    break;

                case 8:
                    System.out.println("Exiting");
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }


    private void addClient(Scanner scanner) {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        System.out.print("Enter client address: ");
        String address = scanner.nextLine();

        Client client = new Client(clientId, name, address);
        Service.addClient(client);
        System.out.println("Client added successfully.");
    }

    private void addLawyer(Scanner scanner) {
        System.out.print("Enter lawyer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter lawyer ID: ");
        String lawyerId = scanner.nextLine();
        System.out.print("Enter lawyer firm name: ");
        String firmName = scanner.nextLine();

        Lawyer lawyer = new Lawyer(lawyerId, name, firmName);
        Service.addLawyer(lawyer);
        System.out.println("Lawyer added successfully.");
    }

    private void addCase(Scanner scanner) {
        System.out.print("Enter case ID: ");
        String caseId = scanner.nextLine();
        System.out.print("Enter case status: ");
        String caseStatus = scanner.nextLine();
        System.out.print("Enter case ClientId: ");
        String clientId = scanner.nextLine();

        Case caseObj = new Case (caseId, caseStatus, clientId);
        Service.addCase(caseObj);
        System.out.println("Case added successfully.");
    }

    private void assignLawyerToCase(Scanner scanner) {
        System.out.print("Enter lawyer ID: ");
        String lawyerId = scanner.nextLine();
        System.out.print("Enter case ID: ");
        String caseId = scanner.nextLine();

        try {
            Service.assignLawyerToCase(lawyerId, caseId);
            System.out.println("Lawyer assigned to case successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllClients() {
        System.out.println("All Clients:");
        Service.getAllClients().forEach(client ->
                System.out.println(client.getClientID() + " - " + client.getName() + " - " + client.getAddress()));
    }

    private void viewAllCases() {
        System.out.println("All Cases:");
        Service.getAllCases().forEach(caseObj ->
                System.out.println(caseObj.getCaseID() + " - " + caseObj.getCaseStatus()));
    }


/**
 * Allows the user to assign a lawyer to all open cases for a specific client.
 */
public void assignLawyerToClientCases() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter Client ID: ");
    String clientId = scanner.nextLine();

    System.out.print("Enter Lawyer ID: ");
    String lawyerId = scanner.nextLine();

    try {
        Service.assignLawyerToOpenCasesForClient(clientId, lawyerId);
        System.out.println("Lawyer successfully assigned to open cases.");
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
    }

