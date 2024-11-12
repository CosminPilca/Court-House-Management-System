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
