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
            System.out.println("Court Management System");
            System.out.println("1. Add Client");
            System.out.println("2. Assign Lawyer to Case");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
