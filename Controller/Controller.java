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

