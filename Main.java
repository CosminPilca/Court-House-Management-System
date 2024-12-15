import UI.*;
import Controller.*;
import Model.*;
import Repository.*;
import Service.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * The Main class serves as the entry point for the application, initializing
 * the repositories, services, controller, and user interface components.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===== Enter the number correlated with the desired saving method =====");
        System.out.println("1. InMemoryRepository");
        System.out.println("2. FileRepository");
        System.out.println("3. DatabaseRepository");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (true) {
            try {
                System.out.print("Your choice (1/2/3): ");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        IRepository<Case> caseRepository;
        IRepository<Lawyer> lawyerRepository;
        IRepository<Client> clientRepository;
        IRepository<Judge> judgeRepository;
        IRepository<LawyerAssignment> lawyerAssignmentRepository;

        switch (choice) {
            case 1 -> {
                caseRepository = new InMemoryRepository<>(Case::getCaseID);
                lawyerRepository = new InMemoryRepository<>(Lawyer::getLawyerID);
                clientRepository = new InMemoryRepository<>(Client::getClientID);
                judgeRepository = new InMemoryRepository<>(Judge::getJudgeID);
                lawyerAssignmentRepository = new InMemoryRepository<>(assignment -> assignment.getLawyerID() + "-" + assignment.getCaseID());
            }
            case 2 -> {
                caseRepository = new FileRepository<>(
                        "./cases.csv",
                        Case::getCaseID,
                        line -> {
                            String[] parts = line.split(",");
                            return new Case(parts[0], parts[1], parts[2]);
                        },
                        caseObj -> caseObj.getCaseID() + "," + caseObj.getCaseStatus() + "," + caseObj.getClientId()
                );
                lawyerRepository = new FileRepository<>(
                        "./lawyers.csv",
                        Lawyer::getLawyerID,
                        line -> {
                            String[] parts = line.split(",");
                            return new Lawyer(parts[0], parts[1], parts[2]);
                        },
                        lawyer -> lawyer.getLawyerID() + "," + lawyer.getGivenName() + "," + lawyer.getFirmName()
                );
                clientRepository =  new FileRepository<>(
                        "./clients.csv",
                        Client::getClientID,
                        line -> {
                            String[] parts = line.split(",");
                            return new Client(parts[0], parts[1], parts[2]);
                        },
                        client -> client.getClientID() + "," + client.getGivenName() + "," + client.getAddress()
                );

                judgeRepository = new FileRepository<>(
                        "./judges.csv",
                        Judge::getJudgeID,
                        line -> {
                            String[] parts = line.split(",");
                            return new Judge(parts[0], parts[1], parts[2]);
                        },
                        judge -> judge.getJudgeID() + "," + judge.getGivenName() + "," + judge.getSpecialty()
                );
                lawyerAssignmentRepository = new FileRepository<>(
                        "./assignments.csv",
                        assignment -> assignment.getLawyerID() + "-" + assignment.getCaseID(),
                        line -> {
                            String[] parts = line.split(",");
                            return new LawyerAssignment(parts[0], parts[1]);
                        },
                        assignment -> assignment.getLawyerID() + "," + assignment.getCaseID()
                );

            }
            case 3 -> {
                caseRepository = new DBRepository<>(Case.class);
                lawyerRepository = new DBRepository<>(Lawyer.class);
                clientRepository = new DBRepository<>(Client.class);
                judgeRepository = new DBRepository<>(Judge.class);
                lawyerAssignmentRepository = new DBRepository<>(LawyerAssignment.class);
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
        Service service = new Service(clientRepository, lawyerRepository, caseRepository, lawyerAssignmentRepository, judgeRepository);
        Controller controller = new Controller(service);
        UI ui = new UI(controller);
        ui.run();
    }
}