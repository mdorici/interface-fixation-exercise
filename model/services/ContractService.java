package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService {
    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {
        double monthlyValue = contract.getTotalValue()/months;
        for(int i = 1; i <= months; i++) {
            Date date = addMonths(contract.getDate(), i);
            double installmentInterest = monthlyValue + onlinePaymentService.interest(monthlyValue, i);
            double installmentAmount = installmentInterest + onlinePaymentService.paymentFee(installmentInterest);
            contract.addInstallment(new Installment(date, installmentAmount));
        }
    }

    private Date addMonths (Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime();
    }
}
