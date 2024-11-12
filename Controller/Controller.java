package com.courthouse.controller;

import com.courthouse.service.CourtService;
import com.courthouse.model.Client;

import java.util.Scanner;

public class CourtController {
    private CourtService service;

    public CourtController(CourtService service) {
        this.service = service;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourt Management System");
            System.out.println("1. Add Client");
            System.out.println("2. Add Lawyer");
            System.out.println("3. Add Case");
            System.out.println("4. Assign Lawyer to Case");
            System.out.println("5. View All Clients");
            System.out.println("6. View All Cases");
            System.out.println("7. Exit");
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
                    viewAllClients();
                    break;

                case 6:
                    viewAllCases();
                    break;

                case 7:
                    System.out.println("Exiting");
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }


