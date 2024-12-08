package Service;

import Controller.Controller;
import Model.Case;
import Model.Client;
import Model.Judge;
import Model.Lawyer;
import Model.LawyerAssignment;
import Repository.IRepository;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private final IRepository<Client> clientRepository;
    private final IRepository<Lawyer> lawyerRepository;
    private final IRepository<Case> caseRepository;
    private final IRepository<LawyerAssignment> assignmentRepository;
    private final IRepository<Judge> judgeRepository;

    public Service(
            IRepository<Client> clientRepository,
            IRepository<Lawyer> lawyerRepository,
            IRepository<Case> caseRepository,
            IRepository<LawyerAssignment> assignmentRepository,
            IRepository<Judge> judgeRepository) {
        this.clientRepository = clientRepository;
        this.lawyerRepository = lawyerRepository;
        this.caseRepository = caseRepository;
        this.assignmentRepository = assignmentRepository;
        this.judgeRepository = judgeRepository;
    }

}
