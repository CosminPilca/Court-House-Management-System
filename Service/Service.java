package com.courthouse.service;

import com.courthouse.model.*;
import com.courthouse.repository.*;

import java.util.List;

public class CourtService {
    private IRepository<Client> clientRepository;
    private IRepository<Lawyer> lawyerRepository;
    private IRepository<Case> caseRepository;
    private IRepository<LawyerAssignment> assignmentRepository;

    public CourtService(
            IRepository<Client> clientRepository,
            IRepository<Lawyer> lawyerRepository,
            IRepository<Case> caseRepository,
            IRepository<LawyerAssignment> assignmentRepository){
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
        this.caseRepository = caseRepository;
        this.assignmentRepository = assignmentRepository;
    }

}
    )
}