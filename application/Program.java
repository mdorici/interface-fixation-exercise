package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract data: ");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.next());
        System.out.print("Contract value: ");
        double contractValue = sc.nextDouble();

        Contract contract = new Contract(number, date, contractValue);

        System.out.print("Enter number of installments: ");
        int installmentsNumber = sc.nextInt();

        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract, installmentsNumber);

        System.out.println("Installments: ");
        for(Installment inst : contract.getInstallment()) {
            System.out.println(inst);
        }

        sc.close();
    }
}
